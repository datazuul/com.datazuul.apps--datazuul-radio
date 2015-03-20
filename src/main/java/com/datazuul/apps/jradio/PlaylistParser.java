/* 
 * P2P-Radio - Peer to peer streaming system
 * Project homepage: http://p2p-radio.sourceforge.net/
 * Copyright (C) 2003-2004 Michael Kaufmann <hallo@michael-kaufmann.ch>
 * 
 * ---------------------------------------------------------------------------
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * ---------------------------------------------------------------------------
 */

package com.datazuul.apps.jradio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

/**
 * Parses a Playlist (PLS or M3U) and returns all URLs that are contained in it.
 * 
 * @author Michael Kaufmann
 */
public class PlaylistParser {
	public static final Set PLS_MIME_TYPES;
	public static final Set M3U_MIME_TYPES;

	static {
		PLS_MIME_TYPES = new HashSet();
		M3U_MIME_TYPES = new HashSet();

		// You should use these MIME types:
		PLS_MIME_TYPES.add("audio/x-scpls"); //$NON-NLS-1$
		M3U_MIME_TYPES.add("audio/x-mpegurl"); //$NON-NLS-1$

		// Please don't use these MIME types:
		PLS_MIME_TYPES.add("audio/scpls"); //$NON-NLS-1$
		M3U_MIME_TYPES.add("audio/mpegurl"); //$NON-NLS-1$
		M3U_MIME_TYPES.add("audio/m3u"); //$NON-NLS-1$
		M3U_MIME_TYPES.add("audio/x-m3u"); //$NON-NLS-1$
	}

	private PlaylistParser() {
		// Keine Instanzen
	}

	// Soviele Zeilen darf die Playlist maximal haben
	public static final int MAX_LINES = 100;

	public static boolean isPlaylistMIMEType(String mimeType) {
		String mimeTypeLowerCase = mimeType.toLowerCase();

		return (PLS_MIME_TYPES.contains(mimeTypeLowerCase) || M3U_MIME_TYPES
				.contains(mimeTypeLowerCase));
	}

	/**
	 * Parses the Playlist at location <code>playlistURL</code> and returns all
	 * URLs that are contained in it.
	 */
	public static URL[] parse(URL playlistURL) {
		InputStream inputStream = null;

		try {
			URLConnection connection = playlistURL.openConnection();
			connection.setAllowUserInteraction(false);
			connection.setUseCaches(false);
			connection.connect();

			inputStream = connection.getInputStream();

			String contentType = connection.getContentType();

			boolean couldBePLS;
			boolean couldBeM3U;

			if (contentType == null) {
				couldBePLS = true;
				couldBeM3U = true;
			} else {
				String contentTypeLowerCase = contentType.toLowerCase();

				couldBePLS = PLS_MIME_TYPES.contains(contentTypeLowerCase);
				couldBeM3U = M3U_MIME_TYPES.contains(contentTypeLowerCase);
			}

			if (!couldBePLS && !couldBeM3U) {
				// Das ist keine Playlist, sondern etwas anderes
				return null;
			}

			Vector URLs = new Vector();

			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputStream));

			for (int i = 0; i < MAX_LINES; i++) {
				String line = null;

				try {
					line = reader.readLine();
				} catch (IOException e) {
					// Mit dem Zeilenlesen aufh�ren
					break;
				}

				if (line == null) {
					// Mit dem Zeilenlesen aufh�ren
					break;
				}

				line = line.trim();

				if (couldBePLS) {
					if (line.toLowerCase().startsWith("file")) //$NON-NLS-1$
					{
						int equalSignPos = line.indexOf('=');

						if (equalSignPos != -1) {
							// Ein Gleichheitszeichen ist da

							// Ist das X bei "FileX=..." eine Zahl?
							String shouldBeANumber = line.substring(4,
									equalSignPos);

							boolean isANumber = true;

							try {
								Integer.parseInt(shouldBeANumber);
							} catch (NumberFormatException e) {
								isANumber = false;
							}

							if (isANumber) {
								// Jetzt kann der Rest der Zeile als URL
								// behandelt werden
								String URLString = line.substring(
										equalSignPos + 1, line.length());

								// Nur HTTP-URLs akzeptieren
								if (URLString.toLowerCase().startsWith(
										("http://"))) //$NON-NLS-1$
								{
									try {
										URLs.add(new URL(URLString));

										// Mit der n�chsten Zeile weitermachen
										continue;
									} catch (MalformedURLException e) {
									}
								}
							}
						}
					}
				}

				if (couldBeM3U) {
					if (line.toLowerCase().startsWith("http://")) //$NON-NLS-1$
					{
						try {
							URLs.add(new URL(line));

							// Mit der n�chsten Zeile weitermachen
							continue;
						} catch (MalformedURLException e) {
						}
					}
				}
			}

			// Object-Array in ein URL-Array konvertieren
			Object[] objectArray = URLs.toArray();
			URL[] URLArray = new URL[objectArray.length];

			for (int i = 0; i < URLArray.length; i++) {
				URLArray[i] = (URL) objectArray[i];
			}

			return URLArray;
		} catch (IOException e) {
			return null;
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
				}
			}
		}
	}
}
