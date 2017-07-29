package hu.tomi.shopfloor.view.dialogs;

import hu.tomi.shopfloor.model.bean.Shelf;
import hu.tomi.shopfloor.view.ShopFloorGUI;

import java.util.List;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;

public class AddShelfDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = -5942552254566788667L;
	
	private ShopFloorGUI gui;
	private List<String> storageNamesList;
	private String[] storageNamesArray;
	private JComboBox<String> storageNameComboBox;
	private JSpinner heightSpinner = new JSpinner();
	private JSpinner widthSpinner = new JSpinner();
	private JSpinner depthSpinner = new JSpinner();
	private JSpinner loadcapacitySpinner = new JSpinner();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");	

	public AddShelfDialog(ShopFloorGUI gui, boolean modal) {
		super(gui.getWindow(), modal);
		this.gui = gui;
		
		this.setTitle("Add Shelf");
		
		JPanel settingPanel = createSettingPanel();
		JPanel buttonPanel = createButtonpanel();		
		JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel);		
		getContentPane().add(dialogPanel);
		
		pack();
		
		setLocationRelativeTo(gui.getWindow());
		
		setVisible(true);	
		
	}
	
	public AddShelfDialog(ShopFloorGUI gui, boolean modal, String[] storageNames) {
		super(gui.getWindow(), modal);
		this.gui = gui;
		
		this.storageNamesArray = storageNames;
		this.storageNameComboBox = new JComboBox<String>(storageNamesArray);
		
		this.setTitle("Add Shelf");
		
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
		
		settingPanel.setLayout(new GridLayout(5, 2));
				
		settingPanel.add(new JLabel("Storage name:"));
		settingPanel.add(this.storageNameComboBox);
		
		settingPanel.add(new JLabel("Height:"));
		settingPanel.add(this.heightSpinner);

		settingPanel.add(new JLabel("Width:"));
		settingPanel.add(this.widthSpinner);

		settingPanel.add(new JLabel("Depth:"));
		settingPanel.add(this.depthSpinner);
		
		settingPanel.add(new JLabel("Load Capacity"));
		settingPanel.add(this.loadcapacitySpinner);
				
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
			
			if ((Integer) heightSpinner.getValue() == 0) {
				JOptionPane.showMessageDialog(
                        gui.getWindow(),
                        "The height can not be zero!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
			}
			
			if ((Integer) widthSpinner.getValue() == 0) {
				JOptionPane.showMessageDialog(
                        gui.getWindow(),
                        "The width can not be zero!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
			}
			
			if ((Integer) depthSpinner.getValue() == 0) {
				JOptionPane.showMessageDialog(
                        gui.getWindow(),
                        "The depth can not be zero!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
			}
			
			Shelf shelf = new Shelf();
			
			shelf.setStorageName(storageNameComboBox.getSelectedItem().toString());
			shelf.setHeight((Integer) heightSpinner.getValue());
			shelf.setWidth((Integer) widthSpinner.getValue());
			shelf.setDepth((Integer) depthSpinner.getValue());
			shelf.setLoadCApacity((Integer) loadcapacitySpinner.getValue());
			
			if(!gui.getController().addShelf(shelf)){
				JOptionPane.showMessageDialog(
                        gui.getWindow(),
                        "Adding shelf failed!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
			}else{
				setVisible(false);
			}
			
			
			
		}else if(cancelButton == e.getSource()){
			setVisible(false);
		}

	}

}
