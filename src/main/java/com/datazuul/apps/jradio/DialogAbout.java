package com.datazuul.apps.jradio;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

//VS4E -- DO NOT REMOVE THIS LINE!
public class DialogAbout extends JDialog {

	private static final long serialVersionUID = 1L;
	private JLabel iconLabel;
	private JButton okButton;
	private JTextArea textArea;
	private JScrollPane jScrollPane0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public DialogAbout() {
		initComponents();
	}

	public DialogAbout(Frame parent) {
		super(parent);
		initComponents();
	}

	public DialogAbout(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DialogAbout(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DialogAbout(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DialogAbout(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DialogAbout(Dialog parent) {
		super(parent);
		initComponents();
	}

	public DialogAbout(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public DialogAbout(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DialogAbout(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public DialogAbout(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public DialogAbout(Window parent) {
		super(parent);
		initComponents();
	}

	public DialogAbout(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public DialogAbout(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public DialogAbout(Window parent, String title, ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public DialogAbout(Window parent, String title, ModalityType modalityType,
			GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(new Color(223, 223, 223));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJScrollPane0(), new Constraints(new Bilateral(131, 12, 22), new Bilateral(14, 49, 22)));
		add(getIconLabel(), new Constraints(new Leading(19, 46, 259), new Leading(70, 10, 10)));
		add(getOkButton(), new Constraints(new Bilateral(267, 149, 54), new Trailing(12, 77, 118)));
		setSize(470, 240);
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getTextArea());
		}
		return jScrollPane0;
	}

	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setText("J-Radio\nListen to your favorite radio stations!\n\nCopyright 2010 Ralf Eichinger");
		}
		return textArea;
	}

	private JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton();
			okButton.setText("OK");
			okButton.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					okButtonActionActionPerformed(event);
				}
			});
		}
		return okButton;
	}

	private JLabel getIconLabel() {
		if (iconLabel == null) {
			iconLabel = new JLabel();
			URL imageURL = this.getClass()
					.getResource("images/gecko-small.png");
			ImageIcon ii = new ImageIcon(imageURL);
			// Image img = ii.getImage();
			// Image newimg =
			// img.getScaledInstance(speakerLabel.getSize().width,
			// speakerLabel.getSize().height, Image.SCALE_SMOOTH);
			// ii = new ImageIcon(newimg);
			iconLabel.setIcon(ii);
		}
		return iconLabel;
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
				DialogAbout dialog = new DialogAbout();
				dialog.setDefaultCloseOperation(DialogAbout.DISPOSE_ON_CLOSE);
				dialog.setTitle("DialogAbout");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

	private void okButtonActionActionPerformed(ActionEvent event) {
		setVisible(false);
	}

}
