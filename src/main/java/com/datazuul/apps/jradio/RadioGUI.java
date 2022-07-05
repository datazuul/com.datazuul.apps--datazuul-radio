package com.datazuul.apps.jradio;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class RadioGUI extends JFrame {
	private RadioPlayerThread radioPlayerThread = null;
	private Preferences prefs = null;

	private static final long serialVersionUID = 1L;
	private JList stationList;
	private JScrollPane stationListScrollPane;
	private JLabel speakerLabel;
	private JButton addButton;
	private JMenuItem menuFileQuit;
	private JMenu menuFile;
	private JMenuBar menuBar;
	private JMenuItem menuHelpAbout;
	private JMenu menuHelp;
	private JButton delButton;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";

	public RadioGUI() {
		initPrefs();
		initComponents();
	}

	private void initPrefs() {
		// This will define a node in which the preferences can be stored
		// System.out.println("node=" + this.getClass().getCanonicalName());
		prefs = Preferences.userRoot().node("net/jubuntu/jradio");

		String[] keys = null;
		try {
			keys = prefs.keys();
		} catch (BackingStoreException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (keys.length == 0) {
			prefs.put("Bayern 1 (Niederbayern / Oberpfalz)",
					"https://dispatcher.rndfnk.com/br/br1/nbopf/mp3/mid");
			prefs.put("Bayern 2 (Süd)",
					"https://dispatcher.rndfnk.com/br/br2/sued/mp3/mid");
			prefs.put("Bayern 3",
					"https://dispatcher.rndfnk.com/br/br3/live/mp3/mid");
			prefs.put("Hardradio.com", "http://144.217.29.205:80/");
			prefs.put("Schattenreich",
					"http://stream.laut.fm/schattenreich");
			try {
				prefs.flush();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setLayout(new GroupLayout());
		add(getStationListScrollPane(), new Constraints(new Leading(200, 300,
				10, 10), new Leading(12, 192, 10, 10)));
		add(getSpeakerLabel(), new Constraints(new Leading(12, 176, 10, 10),
				new Leading(11, 193, 12, 12)));
		add(getAddButton(), new Constraints(new Leading(356, 59, 12, 12),
				new Trailing(12, 46, 216)));
		add(getDelButton(), new Constraints(new Leading(284, 60, 12, 12),
				new Trailing(12, 46, 216)));
		setJMenuBar(getJMenuBar0());
		setSize(510, 265);
	}

	private JButton getDelButton() {
		if (delButton == null) {
			delButton = new JButton();
			delButton.setText("-");
			delButton.setToolTipText("Delete selected radio station");
			delButton.setMnemonic('D');
			delButton.setPreferredSize(new Dimension(45, 25));
			delButton.setMaximumSize(new Dimension(45, 25));
			delButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					delButtonActionActionPerformed(event);
				}
			});
		}
		return delButton;
	}

	private JMenu getMenuHelp() {
		if (menuHelp == null) {
			menuHelp = new JMenu();
			menuHelp.setText("Help");
			menuHelp.setOpaque(false);
			menuHelp.add(getMenuHelpAbout());
		}
		return menuHelp;
	}

	private JMenuItem getMenuHelpAbout() {
		if (menuHelpAbout == null) {
			menuHelpAbout = new JMenuItem();
			menuHelpAbout.setText("About");
			menuHelpAbout.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					menuHelpAboutActionActionPerformed(event);
				}
			});
		}
		return menuHelpAbout;
	}

	private JMenuBar getJMenuBar0() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.add(getMenuFile());
			menuBar.add(getMenuHelp());
		}
		return menuBar;
	}

	private JMenu getMenuFile() {
		if (menuFile == null) {
			menuFile = new JMenu();
			menuFile.setText("File");
			menuFile.setOpaque(false);
			menuFile.add(getMenuFileQuit());
		}
		return menuFile;
	}

	private JMenuItem getMenuFileQuit() {
		if (menuFileQuit == null) {
			menuFileQuit = new JMenuItem();
			menuFileQuit.setText("Quit");
			menuFileQuit.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					menuFileQuitActionActionPerformed(event);
				}
			});
		}
		return menuFileQuit;
	}

	public void actionPerformed(ActionEvent e) {
		if ("addStation".equals(e.getActionCommand())) {
			System.out.println("klick");
		} else {
			System.out.println("uups");
		}
	}

	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("+");
			addButton.setToolTipText("Add a radio station");
			addButton.setPreferredSize(new Dimension(60, 25));
			addButton.setMaximumSize(new Dimension(60, 25));
			addButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					addButtonActionActionPerformed(event);
				}
			});
		}
		return addButton;
	}

	private JLabel getSpeakerLabel() {
		if (speakerLabel == null) {
			speakerLabel = new JLabel();
			URL imageURL = this.getClass().getResource("images/speaker-02.png");
			ImageIcon ii = new ImageIcon(imageURL);
			// Image img = ii.getImage();
			// Image newimg =
			// img.getScaledInstance(speakerLabel.getSize().width,
			// speakerLabel.getSize().height, Image.SCALE_SMOOTH);
			// ii = new ImageIcon(newimg);
			speakerLabel.setIcon(ii);
		}
		return speakerLabel;
	}

	private JScrollPane getStationListScrollPane() {
		if (stationListScrollPane == null) {
			stationListScrollPane = new JScrollPane();
			stationListScrollPane.setViewportView(getStationList());
		}
		return stationListScrollPane;
	}

	private JList getStationList() {
		if (stationList == null) {
			stationList = new JList();
			stationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			stationList.setLayoutOrientation(JList.VERTICAL);

			DefaultListModel listModel = new DefaultListModel();
			String[] keys = null;
			try {
				keys = prefs.keys();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (keys != null) {
				for (int i = 0; i < keys.length; i++) {
					String key = keys[i];
					listModel.addElement(new RadioStation(key, prefs.get(key,
							"")));
				}
			}
			stationList.setModel(listModel);
			// stationList.setSelectedIndex(-1);

			stationList.addListSelectionListener(new ListSelectionListener() {

				public void valueChanged(ListSelectionEvent event) {
					ensureEventThread();
					if (event.getValueIsAdjusting() == false) {
						stationListSelectionValueChanged(event);
					}
				}
			});
		}
		return stationList;
	}

	private void ensureEventThread() {
		if (SwingUtilities.isEventDispatchThread()) {
			return;
		}
		throw new RuntimeException("no event thread");
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class. Note: This class is only created so that you can
	 * easily preview the result at runtime. It is not expected to be managed by
	 * the designer. You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				RadioGUI frame = new RadioGUI();
				frame.setDefaultCloseOperation(RadioGUI.EXIT_ON_CLOSE);
				frame.setTitle("JRadio");
				frame.getContentPane().setPreferredSize(frame.getSize());
				// frame.getContentPane().setBackground(new Color(205, 235,
				// 139));
				URL imageURL = this.getClass().getResource(
						"images/radio-icon-36x36.png");
				ImageIcon ii = new ImageIcon(imageURL);
				frame.setIconImage(ii.getImage());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void stationListSelectionValueChanged(ListSelectionEvent event) {
		if (radioPlayerThread != null) {
			radioPlayerThread.stopIt();
		}

		RadioStation station = (RadioStation) stationList.getSelectedValue();
		// stationList.setSelectedIndex(stationList.getSelectedIndex());

		if (station != null) {
			final String url = station.getUrl();
			radioPlayerThread = new RadioPlayerThread(url);
			radioPlayerThread.start();
		}
	}

	private void addButtonActionActionPerformed(ActionEvent event) {
		System.out.println("klick");
		AddRadioStationDialog dlg = new AddRadioStationDialog(this,
				"Add a new radio station", true);
		dlg.pack();
		dlg.setLocationRelativeTo(getParent());
		dlg.setVisible(true);

		String radioStationName = dlg.getRadioStationName();
		String streamUrl = dlg.getStreamUrl();

		if (radioStationName != null && streamUrl != null) {
			if (streamUrl.toLowerCase().endsWith(".m3u")
					|| streamUrl.toLowerCase().endsWith(".pls")) {
				URL playlistUrl = null;
				try {
					playlistUrl = new URL(streamUrl);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return;
				}
				URL[] urls = PlaylistParser.parse(playlistUrl);
				streamUrl = urls[0].toString();
			}
			System.out.println("name=" + radioStationName + ", url="
					+ streamUrl);
			prefs.put(radioStationName, streamUrl);
			try {
				prefs.flush();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DefaultListModel model = (DefaultListModel) getStationList()
					.getModel();
			model.addElement(new RadioStation(radioStationName, streamUrl));

			getStationList().setSelectedIndex(-1);
		}
	}

	private void delButtonActionActionPerformed(ActionEvent event) {
		RadioStation station = (RadioStation) stationList.getSelectedValue();
		if (station != null) {
			int n = JOptionPane.showConfirmDialog(this,
					"Do you really want to remove the radio station \""
							+ station.getLabel() + "\"?", "Confirm Deletion",
					JOptionPane.YES_NO_OPTION);
			if (n == JOptionPane.YES_OPTION) {
				prefs.remove(station.getLabel());
				try {
					prefs.flush();
				} catch (BackingStoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DefaultListModel model = (DefaultListModel) getStationList()
						.getModel();
				model.removeElement(station);

				getStationList().clearSelection();
			} else if (n == JOptionPane.NO_OPTION) {

			} else {

			}
		}
	}

	private void menuFileQuitActionActionPerformed(ActionEvent event) {
		System.exit(0);
	}

	private void menuHelpAboutActionActionPerformed(ActionEvent event) {
		DialogAbout dlg = new DialogAbout();
		dlg.setModal(true);
		// dlg.pack();
		dlg.setLocationRelativeTo(getParent());
		dlg.setVisible(true);
	}
}
