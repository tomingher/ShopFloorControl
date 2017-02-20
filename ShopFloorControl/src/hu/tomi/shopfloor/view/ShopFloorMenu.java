package hu.tomi.shopfloor.view;

import hu.tomi.shopfloor.model.bean.WareHouse;
import hu.tomi.shopfloor.model.bean.Storage;
import hu.tomi.shopfloor.model.bean.Shelf;
import hu.tomi.shopfloor.model.bean.Location;
import hu.tomi.shopfloor.model.bean._Package;
import hu.tomi.shopfloor.model.bean.Manufacturer;
import hu.tomi.shopfloor.model.bean.Product;
/*
import hu.tomi.shopfloor.model.view.dialogs.BuyBookDialog;
import hu.alkfejl.bookshop.view.dialogs.AddCustomerDialog;
import hu.alkfejl.bookshop.view.dialogs.SellBookDialog;
import hu.alkfejl.bookshop.view.tablemodels.BookTableModel;
import hu.alkfejl.bookshop.view.tablemodels.CustomerTableModel;
import hu.alkfejl.bookshop.view.tablemodels.PurchaseTableModel;
*/
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShopFloorMenu extends JMenuBar implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7688673856526984555L;
	private ShopFloorGUI gui;

	public ShopFloorMenu(ShopFloorGUI gui) {
		super();
        this.gui = gui;

        createMenuPoint("Warehouse", "Add Warehouse", "Modify Warehouse", "Delete Warehouse");
        createMenuPoint("Storage Shelves", "Add Storage", "Modify Storage", "Delete Storage");
        
	}

	private void createMenuPoint(String menuname, String... subnames) {

        JMenu menu = new JMenu(menuname);

        this.add(menu);

        for (String subname : subnames) {
            JMenuItem menuItem = new JMenuItem(subname);

            menu.add(menuItem);
            
            menuItem.addActionListener(this);
        }		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
