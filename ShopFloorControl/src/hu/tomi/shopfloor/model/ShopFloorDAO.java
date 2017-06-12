package hu.tomi.shopfloor.model;

import hu.tomi.shopfloor.model.bean.*;

import java.util.List;

public interface ShopFloorDAO {
	
	public boolean addWareHouse(WareHouse warehouse);
	
	public List<WareHouse> getWareHouses();
	
	public List<String> getWareHouseNames();
	
	public boolean addStorage(Storage storage);
	
	public List<Storage> getStorages();
	
	public boolean addShelf(Shelf shelf);
	
	public List<Shelf> getShelves();
	
	public List<ShelfData> getShelfData();	
	
	public List<String> getStorageNames();
	
	public boolean addLocation(Location location);
	
	public List<Location> getLocations();

	public List<LocationData> getLocationData();
	
	public boolean addPackage(_Package _package);
	
	public List<_Package> getPackages();
	
	public boolean addProduct(Product product);
	
	public List<Product> getProducts();
	
	public boolean addManufacturer(Manufacturer manufacturer);
	
	public List<Manufacturer> getManufacturers();

}