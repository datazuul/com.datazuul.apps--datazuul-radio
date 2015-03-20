package com.datazuul.apps.jradio;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioFormat.Encoding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

import javazoom.spi.PropertiesContainer;

import org.tritonus.share.sampled.TAudioFormat;
import org.tritonus.share.sampled.file.TAudioFileFormat;

public class RadioPlayerThread extends Thread {
	private static final int BUFFER_SIZE = 4096; // 1024 * 4
	
	volatile boolean running = false; // Flag

	private URL source = null;

	public void stopIt() {
		running = false;
	}

	public RadioPlayerThread(String src) {
		// File source = new File("/home/ralf/MUSIC/song30.mp3");
		// src = "http://gffstream.ic.llnwd.net/stream/gffstream_w11b";
		try {
			source = new URL(src);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void rawplay(AudioFormat targetFormat, AudioInputStream din)
			throws IOException, LineUnavailableException {
		byte[] data = new byte[BUFFER_SIZE];
		SourceDataLine line = getLine(targetFormat);
		if (line != null) {
			// Start
			line.start();

			int nBytesRead = 0, nBytesWritten = 0;
			while (running && nBytesRead != -1) {
				try {
					nBytesRead = din.read(data, 0, data.length);
					String text = new String(data).toString();
					// // if (text.contains("ID3")) {
					// System.out.println(text);
					// // }
					if (nBytesRead != -1) {
						nBytesWritten = line.write(data, 0, nBytesRead);
					}
				} catch (Exception e) {
					System.err.print("... buffer implementation needed ...");
				}
			}
			// Stop
			line.drain(); // play until buffer is empty...
			line.stop();
			line.close();
			din.close();
		}
	}

	private SourceDataLine getLine(AudioFormat audioFormat)
			throws LineUnavailableException {
		SourceDataLine res = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class,
				audioFormat);
		res = (SourceDataLine) AudioSystem.getLine(info);
		res.open(audioFormat);
		return res;
	}

	// /**
	// * @param args
	// */
	// public static void main(String[] args) {
	// if (args.length != 1) {
	// System.err.println("An URL to the MP3 stream is expected.");
	// System.exit(1);
	// }
	//
	// try {
	// new Radio(args[0]);
	// } catch (Throwable t) {
	// System.err.println("Exception occurred in main():");
	// t.printStackTrace();
	// System.exit(1);
	// }
	//
	// }

	@Override
	public void run() {
		running = true;

		AudioInputStream in = null;
		try {
			in = AudioSystem.getAudioInputStream(source);			
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// try {
		// AdvancedPlayer advPlayer = new AdvancedPlayer(in);
		// } catch (JavaLayerException e2) {
		// // TODO Auto-generated catch block
		// e2.printStackTrace();
		// }

		AudioFileFormat baseFileFormat = null;
		try {
			baseFileFormat = AudioSystem.getAudioFileFormat(in);
		} catch (UnsupportedAudioFileException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		AudioFormat baseFormat = in.getFormat();
		AudioFormat decodedFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED, baseFormat.getSampleRate(),
				16, baseFormat.getChannels(), baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(), false);

		// "duration" (Long): Playback duration of file, in microseconds
		// "author" (String): Name of the author of the file
		// "title" (String): Title of the file
		// "copyright" (String): Copyright message
		// "comment" (String): Arbitrary text

		// Get meta informations (of file):
		if (baseFileFormat instanceof TAudioFileFormat) {
			Map<String, Object> properties = ((TAudioFileFormat) baseFileFormat)
					.properties();
			String key_author = "author";
			String author = (String) properties.get(key_author);
			// System.out.println("author = " + author);

			String key_duration = "duration";
			Long duration = (Long) properties.get(key_duration);
			// System.out.println("duration = " + duration);

			String key_id3tag = "mp3.id3tag.v2";
			InputStream id3tag = (InputStream) properties.get(key_id3tag);
			// System.out.println("duration = " + duration);
		}
		// Get meta informations (of stream):
		if (baseFormat instanceof TAudioFormat) {
			Map<String, Object> properties = ((TAudioFormat) baseFormat)
					.properties();

			// bitrate in bits per seconds, average bitrate for VBR enabled
			// stream
			String key = "bitrate";
			Integer val = (Integer) properties.get(key);
			System.out.println("bitrate = " + val);

			// number of channels (1=mono, 2=stereo, AudioSystem.NOT_SPECIFIED)
			int channels = baseFormat.getChannels();
			System.out.println("channels = " + channels);

			// encoding
			Encoding encoding = baseFormat.getEncoding();
			System.out.println("encoding = " + encoding);

			// number of frames per second (or AudioSystem.NOT_SPECIFIED)
			float frameRate = baseFormat.getFrameRate();
			System.out.println("frame rate per second = " + frameRate);

			// number of bytes per frame (or AudioSystem.NOT_SPECIFIED)
			int frameSize = baseFormat.getFrameSize();
			System.out.println("bytes per frame = " + frameSize);

			// sample rate per second of the uncompressed audio data
			float sampleRate = baseFormat.getSampleRate();
			System.out.println("sample rate per second = " + sampleRate);

			// the number of bits in each sample, or AudioSystem.NOT_SPECIFIED
			int sampleSizeInBits = baseFormat.getSampleSizeInBits();
			System.out.println("bits in each sample = " + sampleSizeInBits);

			// true if the data is stored in big-endian byte order, false if
			// little-endian
			boolean bigEndian = baseFormat.isBigEndian();
			System.out.println("big-endian byte order = " + bigEndian);
		}

		AudioInputStream din = AudioSystem.getAudioInputStream(decodedFormat,
				in);

		// Get equalizer values
		if (din instanceof PropertiesContainer) {
			Map properties = ((PropertiesContainer) din).properties();
			float[] equalizer = (float[]) properties.get("mp3.equalizer");
			for (int i = 0; i < equalizer.length; i++) {
				float f = equalizer[i];
				// System.out.println("equalizer " + i + " = " + f);
			}
		}

		// Play now.
		try {
			rawplay(decodedFormat, din);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
