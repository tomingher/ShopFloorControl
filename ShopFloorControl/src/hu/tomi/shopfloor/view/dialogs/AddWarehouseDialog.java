package hu.tomi.shopfloor.view.dialogs;

import hu.tomi.shopfloor.model.bean.WareHouse;
import hu.tomi.shopfloor.view.ShopFloorGUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JSpinner;
import javax.swing.JTextField;

public class AddWarehouseDialog extends JDialog implements ActionListener {		
	
	private static final long serialVersionUID = 3504474467335998745L;
	private ShopFloorGUI gui;
	
	private JTextField warehouseNameTextField = new JTextField(25);
	private JSpinner numberOfStorageShelvesSpinner = new JSpinner();
	private JButton okButton = new JButton("OK");
	private JButton cancelButton = new JButton("Cancel");	
	
	public AddWarehouseDialog(ShopFloorGUI gui, boolean modal) {
		super(gui.getWindow(), modal);
		this.gui = gui;
		
		this.setTitle("Add Warehouse");
		
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
		
		settingPanel.setLayout(new GridLayout(2, 2));
		
		settingPanel.add(new JLabel("Name"));
		settingPanel.add(this.warehouseNameTextField);
		
		settingPanel.add(new JLabel("Number of Storage Shelves"));
		settingPanel.add(this.numberOfStorageShelvesSpinner);		
		
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
			
			if (warehouseNameTextField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(
                        gui.getWindow(),
                        "Warehouse name is mandatory!",
                        "Error!",
                        JOptionPane.ERROR_MESSAGE);
                return;
			}
			
			WareHouse warehouse = new WareHouse();
			
			warehouse.setName(warehouseNameTextField.getText());
			warehouse.setNumberOfStorageShelves((Integer)numberOfStorageShelvesSpinner.getValue());
			
			if (!gui.getController().addWareHoue(warehouse)) {
				JOptionPane.showMessageDialog(
                        gui.getWindow(),
                        "Adding warehouse failed!",
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
