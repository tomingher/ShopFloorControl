package hu.tomi.shopfloor.model;

import hu.tomi.shopfloor.model.bean.WareHouse;
import hu.tomi.shopfloor.model.bean.Storage;
import hu.tomi.shopfloor.model.bean.Shelf;
import hu.tomi.shopfloor.model.bean.Location;
import hu.tomi.shopfloor.model.bean._Package;
import hu.tomi.shopfloor.model.bean.Product;
import hu.tomi.shopfloor.model.bean.Manufacturer;

import java.util.List;

public interface ShopFloorDAO {
	
	public boolean addWareHouse(WareHouse warehouse);
	
	public List<WareHouse> getWareHouses();
	
	public boolean addStorage(Storage storage);
	
	public List<Storage> getStorages();
	
	public boolean addShelf(Shelf shelf);
	
	public List<Shelf> getShelves();
	
	public boolean addLocation(Location location);
	
	public List<Location> getLocations();
	
	public boolean addPackage(_Package _package);
	
	public List<_Package> getPackages();
	
	public boolean addProduct(Product product);
	
	public List<Product> getProducts();
	
	public boolean addManufacturer(Manufacturer manufacturer);
	
	public List<Manufacturer> getManufacturers();

}
