package hu.tomi.shopfloor.view.dialogs;

import hu.tomi.shopfloor.model.bean.Storage;
import hu.tomi.shopfloor.model.bean.WareHouse;
import hu.tomi.shopfloor.view.ShopFloorGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.Normalizer.Form;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import javax.swing.JDialog;

public class AddStorageDialog extends JDialog implements ActionListener {	

	private static final long serialVersionUID = 4838692410765106373L;
	
	private ShopFloorGUI gui;	
	private List<String> warehouseNamesList;
	private String[] warehouseNamesArray;	
	private JSpinner numberOfShelvesSpinner = new JSpinner();
	private JSpinner heightSpinner = new JSpinner();
	private JSpinner widthSpinner = new JSpinner();
	private JSpinner depthSpinner = new JSpinner();
	private JSpinner loadcapacitySpinner = new JSpinner();
	private JComboBox<String> warehouseNameComboBox;	
	
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");		
	
	public AddStorageDialog(ShopFloorGUI gui, boolean modal, List<String> warehouseNames) {	
		super(gui.getWindow(), modal);
		this.gui = gui;		
		this.warehouseNamesList = warehouseNames;
				
		this.setTitle("Add Storage");		
		
		JPanel settingPanel = createSettingPanel();
		JPanel buttonPanel = createButtonpanel();		
		JPanel dialogPanel = createDialogPanel(settingPanel, buttonPanel);		
		getContentPane().add(dialogPanel);
		
		pack();
		
		setLocationRelativeTo(gui.getWindow());
		
		setVisible(true);
	}
	
	public AddStorageDialog(ShopFloorGUI gui, boolean modal, String[] warehouseNames) {
		
			super(gui.getWindow(), modal);
			this.gui = gui;		
			
			this.warehouseNamesArray = warehouseNames;			
			this.warehouseNameComboBox = new JComboBox<String>(warehouseNamesArray);
			
			this.setTitle("Add Storage");
			
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
		
		settingPanel.setLayout(new GridLayout(6, 2));
		
		settingPanel.add(new JLabel("Warehouse"));
		settingPanel.add(this.warehouseNameComboBox);		
		
		settingPanel.add(new JLabel("Number of Shelves"));
		settingPanel.add(this.numberOfShelvesSpinner);
		
		settingPanel.add(new JLabel("Height"));
		settingPanel.add(this.heightSpinner);

		settingPanel.add(new JLabel("Width"));
		settingPanel.add(this.widthSpinner);

		settingPanel.add(new JLabel("Depth"));
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
			
			if ((Integer) numberOfShelvesSpinner.getValue() == 0) {
				JOptionPane.showMessageDialog(
                        gui.getWindow(),
                        "NUmber of shelves can not be zero!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
			}
			
			Storage storage = new Storage();
			
			storage.setNumberOfShelves((Integer) numberOfShelvesSpinner.getValue());
			storage.setHeight((Integer) heightSpinner.getValue());
			storage.setWidth((Integer) widthSpinner.getValue());
			storage.setDepth((Integer) depthSpinner.getValue());
			storage.setLoadCApacity((Integer) loadcapacitySpinner.getValue());
			storage.setWarehouseName(warehouseNameComboBox.getSelectedItem().toString());
			
			if(!gui.getController().addStorage(storage)){
				JOptionPane.showMessageDialog(
                        gui.getWindow(),
                        "Adding storage failed!",
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
