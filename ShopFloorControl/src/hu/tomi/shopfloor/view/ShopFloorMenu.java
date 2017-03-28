package hu.tomi.shopfloor.view;

import hu.tomi.shopfloor.model.bean.WareHouse;
import hu.tomi.shopfloor.model.bean.Storage;
import hu.tomi.shopfloor.model.bean.Shelf;
import hu.tomi.shopfloor.model.bean.ShelfData;
import hu.tomi.shopfloor.model.bean.Location;
import hu.tomi.shopfloor.model.bean._Package;
import hu.tomi.shopfloor.view.dialogs.AddLocationDialog;
import hu.tomi.shopfloor.view.dialogs.AddShelfDialog;
import hu.tomi.shopfloor.view.dialogs.AddStorageDialog;
import hu.tomi.shopfloor.view.dialogs.AddWarehouseDialog;
import hu.tomi.shopfloor.view.tablemodels.ManufacturerTableModel;
import hu.tomi.shopfloor.view.tablemodels.ProductTableModel;
import hu.tomi.shopfloor.view.tablemodels.ShelfDataTableModel;
import hu.tomi.shopfloor.view.tablemodels.StorageTableModel;
import hu.tomi.shopfloor.view.tablemodels.WarehouseTableModel;
import hu.tomi.shopfloor.model.bean.Manufacturer;
import hu.tomi.shopfloor.model.bean.Product;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ShopFloorMenu extends JMenuBar implements ActionListener {

	private static final long serialVersionUID = -7688673856526984555L;
	private ShopFloorGUI gui;

	public ShopFloorMenu(ShopFloorGUI gui) {
		super();
        this.gui = gui;

        createMenuPoint("Warehouse", "Add Warehouse",  "List Warehouses"); // ő a raktár, egyedileg készül
        createMenuPoint("Storage Shelves", "Add Storage", "List Storages"); //Ő a nagy polc, vele van tele a raktár, az ő polcait ltérehozásnál mondom  meg
        createMenuPoint("Shelf", "Add Shelf", "List Shelves");          
        createMenuPoint("Location", "Add Location", "List Locations"); // Polcon belül lokáció
        createMenuPoint("Product", "Create Product", "List Products");//termék
        createMenuPoint("Manufacturer", "Create Manufacturers", "List Manufacturers");//gyártó
        createMenuPoint("Package", "Create Package", "List Packages");
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
		
		String actionCommand = e.getActionCommand();
		
		System.out.println("Debug message:" + actionCommand);
		
		if (actionCommand.equals("Add Warehouse")) {
			
			new AddWarehouseDialog(gui, true);
			
		}else if (actionCommand.equals("Add Storage")) {			
			
			new AddStorageDialog(gui, true, gui.getController().getWarehouseNames());
			
		}else if (actionCommand.equals("List Warehouses")) {
			
            List<WareHouse> warehouses = gui.getController().getWareHouses();

            JTable table = new JTable(new WarehouseTableModel(warehouses));

            JScrollPane container = new JScrollPane(table);

            gui.setContent(container);
            
        }else if (actionCommand.equals("List Storages")) {
            List<Storage> storages = gui.getController().getStorages();

            JTable table = new JTable(new StorageTableModel(storages));

            JScrollPane container = new JScrollPane(table);

            gui.setContent(container);
            
        }else if (actionCommand.equals("List Shelves")) {
            List<ShelfData> shelves = gui.getController().getShelfData();

            JTable table = new JTable(new ShelfDataTableModel(shelves));

            JScrollPane container = new JScrollPane(table);

            gui.setContent(container);
            
        }else if(actionCommand.equals("List Products")){
        	List<Product> products = gui.getController().getProducts();
        	
        	JTable table = new JTable(new ProductTableModel(products));

            JScrollPane container = new JScrollPane(table);

            gui.setContent(container);
        }else if(actionCommand.equals("List Manufacturers")){
        	List<Manufacturer> manufacturers = gui.getController().getManufacturers();
        	
        	JTable table = new JTable(new ManufacturerTableModel(manufacturers));
        	
        	JScrollPane container = new JScrollPane(table);

            gui.setContent(container);
        }else if(actionCommand.equals("Add Shelf")){
        	
        	new AddShelfDialog(gui, true,gui.getController().getStorageNames());
        	
        }else if(actionCommand.equals("Add Location")){
        	
        	new AddLocationDialog(gui, true, gui.getController().getStorageNames());
        	
        }
		
	}
}