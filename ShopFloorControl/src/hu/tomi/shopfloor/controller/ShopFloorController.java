package hu.tomi.shopfloor.controller;

import hu.tomi.shopfloor.model.bean.WareHouse;
import hu.tomi.shopfloor.model.bean.Storage;
import hu.tomi.shopfloor.model.bean.Shelf;
import hu.tomi.shopfloor.model.bean.Location;
import hu.tomi.shopfloor.model.bean._Package;
import hu.tomi.shopfloor.model.bean.Product;
import hu.tomi.shopfloor.model.bean.Manufacturer;
import hu.tomi.shopfloor.model.ShopFloorDAO;
import hu.tomi.shopfloor.model.ShopFloorDAODB;
import hu.tomi.shopfloor.view.ShopFloorGUI;

import java.util.List;

public class ShopFloorController {
	
	private ShopFloorDAO dao = new ShopFloorDAODB();
	
	public void startDesktop() {
		ShopFloorGUI gui = new ShopFloorGUI(this);
				
		gui.startGUI();
	}
	
	public boolean addWareHoue(WareHouse warehouse) {
		return dao.addWareHouse(warehouse);
	}
	
	public List<WareHouse> getWareHouses() {
		return dao.getWareHouses();
	}
	
	public boolean addStorage(Storage storage){
		return dao.addStorage(storage);
	}
	
	public List<Storage> getStorages() {
		return dao.getStorages();
	}
	
	public boolean addShelf(Shelf shelf) {
		return dao.addShelf(shelf);
	}
	
	public List<Shelf> getShelves() {
		return dao.getShelves();
	}
	
	public boolean addLocation(Location location) {
		return dao.addLocation(location);
	}
	
	public List<Location> getLocations(){
		return dao.getLocations();
	}
	
	public boolean addPackage(_Package _package) {
		return dao.addPackage(_package);
	}
	
	public List<_Package> getPackages() {
		return dao.getPackages();
	}	
	
	public boolean addProduct(Product product) {
		return dao.addProduct(product);
	}	
	
	public List<Product> getProducts() {
		return dao.getProducts();
	}
	
	public boolean addManufacturer(Manufacturer manufacturer) {
		return dao.addManufacturer(manufacturer);
	}
	
	public List<Manufacturer> getManufacturers() {
		return dao.getManufacturers();
	}

}
