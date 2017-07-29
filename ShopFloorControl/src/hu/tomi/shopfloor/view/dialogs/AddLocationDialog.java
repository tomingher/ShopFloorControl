package hu.tomi.shopfloor.view.dialogs;

import hu.tomi.shopfloor.view.ShopFloorGUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;


public class AddLocationDialog extends JDialog implements ActionListener {
	
	
	private static final long serialVersionUID = -3031338414280015808L;
	
	private ShopFloorGUI gui;

	private String[] storageNamesArray;
	private JComboBox<String> storageNameComboBox;
	private JSpinner widthSpinner = new JSpinner();
	private JSpinner depthSpinner = new JSpinner();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");

	public AddLocationDialog(ShopFloorGUI gui, boolean modal) {
        super(gui.getWindow(), modal);
		this.gui = gui;
		this.setTitle("Add Location");
		
		JPanel settingPanel = createSettingPanel();
		JPanel buttonPanel = createButtonpanel();		
		JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel);		
		getContentPane().add(dialogPanel);
		
		pack();
		
		setLocationRelativeTo(gui.getWindow());
		
		setVisible(true);	
	}
	
	public AddLocationDialog(ShopFloorGUI gui, boolean modal, String[] storageNames) {
        super(gui.getWindow(), modal);
		this.gui = gui;
		this.storageNamesArray = storageNames;
		this.storageNameComboBox = new JComboBox<String>(storageNamesArray);
		this.setTitle("Add Location");
		
		JPanel settingPanel = createSettingPanel();
		JPanel buttonPanel = createButtonpanel();		
		JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel);		
		getContentPane().add(dialogPanel);
		
		pack();
		
		setLocationRelativeTo(gui.getWindow());
		
		setVisible(true);	
	}
	
	
	private JPanel createSettingPanel() {
		JPanel settingPanel = new JPanel();
		
		settingPanel.setLayout(new GridLayout(3, 2));

		settingPanel.add(new JLabel("Storage:"));
		settingPanel.add(this.storageNameComboBox);
		
		settingPanel.add(new JLabel("Width:"));
		settingPanel.add(this.widthSpinner);
		
		settingPanel.add(new JLabel("Depth:"));
		settingPanel.add(this.depthSpinner);
				
		return settingPanel;
	}

	private JPanel createButtonpanel() {
		JPanel buttonPanel = new JPanel();
		
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		buttonPanel.add(okButton);
		okButton.addActionListener(this);
		
		buttonPanel.add(cancelButton);
		cancelButton.addActionListener(this);		
		
		return buttonPanel;
	}
	
	private JPanel createDialogPanel(JPanel settingPanel, JPanel buttonPanel) {
		JPanel dialogPanel = new JPanel();
		
		dialogPanel.setLayout(new BorderLayout());
		
		dialogPanel.add(settingPanel, BorderLayout.CENTER);
		dialogPanel.add(buttonPanel, BorderLayout.SOUTH);		
		
		return dialogPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(okButton == e.getSource()){
			setVisible(false);
		}else if(cancelButton == e.getSource()){
			setVisible(false);
		}
	}
}