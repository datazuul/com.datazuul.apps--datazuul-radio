package com.datazuul.apps.jradio;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

//VS4E -- DO NOT REMOVE THIS LINE!
public class AddRadioStationDialog extends JDialog {
	private String radioStationName = null;
	private String streamUrl = null;

	private static final long serialVersionUID = 1L;
	private JLabel radioStationNameLbl;
	private JTextField radioStationNameTxt;
	private JButton okBtn;
	private JLabel streamUrlLbl;
	private JTextField streamUrlTxt;
	private JButton cancelBtn;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";

	public AddRadioStationDialog() {
		initComponents();
	}

	public AddRadioStationDialog(Frame parent) {
		super(parent);
		initComponents();
	}

	public AddRadioStationDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public AddRadioStationDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddRadioStationDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public AddRadioStationDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public AddRadioStationDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public AddRadioStationDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public AddRadioStationDialog(Dialog parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddRadioStationDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public AddRadioStationDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public AddRadioStationDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public AddRadioStationDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public AddRadioStationDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public AddRadioStationDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public AddRadioStationDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	private void initComponents() {
		setTitle("Add a new radio station");
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(new Color(223, 223, 223));
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getRadioStationNameLbl(), new Constraints(new Leading(12, 12, 12),
				new Leading(12, 12, 12)));
		add(getRadioStationNameTxt(), new Constraints(new Leading(167, 390, 12,
				12), new Leading(10, 12, 12)));
		add(getStreamUrlLbl(), new Constraints(new Leading(12, 146, 10, 10),
				new Leading(35, 12, 12)));
		add(getStreamUrlTxt(), new Constraints(new Bilateral(167, 12, 4),
				new Leading(33, 12, 12)));
		add(getOkBtn(), new Constraints(new Leading(214, 10, 10), new Leading(
				68, 12, 12)));
		add(getCancelBtn(), new Constraints(new Leading(276, 10, 10),
				new Leading(68, 12, 12)));
		setSize(569, 107);
	}

	private JButton getCancelBtn() {
		if (cancelBtn == null) {
			cancelBtn = new JButton();
			cancelBtn.setText("Cancel");
			cancelBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					cancelBtnActionActionPerformed(event);
				}
			});
		}
		return cancelBtn;
	}

	private JTextField getStreamUrlTxt() {
		if (streamUrlTxt == null) {
			streamUrlTxt = new JTextField();
		}
		return streamUrlTxt;
	}

	private JLabel getStreamUrlLbl() {
		if (streamUrlLbl == null) {
			streamUrlLbl = new JLabel();
			streamUrlLbl.setText("Stream URL (MP3):");
		}
		return streamUrlLbl;
	}

	private JButton getOkBtn() {
		if (okBtn == null) {
			okBtn = new JButton();
			okBtn.setText("Ok");
			okBtn.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent event) {
					okBtnActionActionPerformed(event);
				}
			});
		}
		return okBtn;
	}

	private JTextField getRadioStationNameTxt() {
		if (radioStationNameTxt == null) {
			radioStationNameTxt = new JTextField();
		}
		return radioStationNameTxt;
	}

	private JLabel getRadioStationNameLbl() {
		if (radioStationNameLbl == null) {
			radioStationNameLbl = new JLabel();
			radioStationNameLbl.setText("Radio station name:");
		}
		return radioStationNameLbl;
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
				AddRadioStationDialog dialog = new AddRadioStationDialog();
				dialog.setDefaultCloseOperation(AddRadioStationDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("AddRadioStationDialog");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

	private void okBtnActionActionPerformed(ActionEvent event) {
		setRadioStationName(getRadioStationNameTxt().getText());
		setStreamUrl(getStreamUrlTxt().getText());
		clearAndHide();
	}

	/** This method clears the dialog and hides it. */
	public void clearAndHide() {
		radioStationNameTxt.setText(null);
		streamUrlTxt.setText(null);
		setVisible(false);
	}

	private void cancelBtnActionActionPerformed(ActionEvent event) {
		setRadioStationName(null);
		setStreamUrl(null);
		clearAndHide();
	}

	/**
	 * @param radioStationName
	 *            the radioStationName to set
	 */
	public void setRadioStationName(String radioStationName) {
		this.radioStationName = radioStationName;
	}

	/**
	 * @return the radioStationName
	 */
	public String getRadioStationName() {
		return radioStationName;
	}

	/**
	 * @param streamUrl
	 *            the streamUrl to set
	 */
	public void setStreamUrl(String streamUrl) {
		this.streamUrl = streamUrl;
	}

	/**
	 * @return the streamUrl
	 */
	public String getStreamUrl() {
		return streamUrl;
	}

}
