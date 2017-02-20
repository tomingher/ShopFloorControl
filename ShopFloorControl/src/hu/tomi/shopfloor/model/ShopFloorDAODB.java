package hu.tomi.shopfloor.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import hu.tomi.shopfloor.model.bean.Location;
import hu.tomi.shopfloor.model.bean.Manufacturer;
import hu.tomi.shopfloor.model.bean.Product;
import hu.tomi.shopfloor.model.bean.Shelf;
import hu.tomi.shopfloor.model.bean.Storage;
import hu.tomi.shopfloor.model.bean.WareHouse;
import hu.tomi.shopfloor.model.bean._Package;

public class ShopFloorDAODB implements ShopFloorDAO {
	
	List<WareHouse> warehouses = new ArrayList<WareHouse>();
	List<Storage>	storages = new ArrayList<Storage>();
	List<Shelf>		shelves = new ArrayList<Shelf>();
	List<Product>	products = new ArrayList<Product>();
	List<Manufacturer>	manufacturers = new ArrayList<Manufacturer>();
	List<Location>	locations = new ArrayList<Location>();
	List<_Package> packages = new ArrayList<_Package>();	
	
	private static final String DATABASE = "jdbc:postgresql://192.168.1.111:5432/postgres";
	
	private static final String SQL_SELECT_WAREHOUSES = "SELECT * FROM warehouse";
	
	private static final String SQL_INSERT_WAREHOUSE =
	        "INSERT INTO warehouse " +
	        "(name, numberofstorageshelves) " +
	        "VALUES (?, ?)";
	
	private static final String SQL_SELECT_STORAGES = "SELECT * FROM storage";
	
	private static final String SQL_INSERT_STORAGE =
	        "INSERT INTO storage " +
	        "(NumberOfShelves, Height, Width, Depth, WarehouseID, LoadCapacity) " +
	        "VALUES (?, ?, ?, ?, ?, ?)";
	
	private static final String SQL_SELECT_SHELVES = "SELECT * FROM shelf";
	
	private static final String SQL_INSERT_SHELF =
	        "INSERT INTO shelf " +
	        "(StorageID, Height, Width, Depth, LoadCapacity) " +
	        "VALUES (?, ?, ?, ?, ?)";
	
	private static final String SQL_SELECT_LOCATIONS = "SELECT * FROM Location";
	
	private static final String SQL_INSERT_LOCATION =
	        "INSERT INTO location " +
	        "(ShelfID, Height, Width, Depth) " +
	        "VALUES (?, ?, ?, ?)";
	
	private static final String SQL_SELECT_PACKAGES = "SELECT * FROM package";
	
	private static final String SQL_INSERT_PACKAGE =
	        "INSERT INTO package " +
	        "(locationid, height, width, depth, weight) " +
	        "VALUES (?, ?, ?, ?, ?)";
	
	private static final String SQL_SELECT_PRODUCTS = "SELECT * FROM product";
	
	private static final String SQL_INSERT_PRODUCT =
	        "INSERT INTO product " +
	        "(manufacturerid, weight, quantity, description) " +
	        "VALUES (?, ?, ?, ?)";
	
	private static final String SQL_SELECT_MANUFACTURERS = "SELECT * FROM manufacturer";
	
	private static final String SQL_INSERT_MANUFACTURER =
	        "INSERT INTO manufacturer " +
	        "(name description) " +
	        "VALUES (?, ?)";
	
	public ShopFloorDAODB() {
        try {            
            Class.forName("org.postgresql.JDBC");
        } catch (ClassNotFoundException e) {
            System.out.println("Failed to load PostgreSQL JDBC driver.");
            e.printStackTrace();
        }
    }
	

	@Override
	public boolean addWareHouse(WareHouse warehouse) {
		
		boolean everythingisok = false;
		
		Connection conn = null;
        PreparedStatement query = null;
        
        try {
        	conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.prepareStatement(SQL_INSERT_WAREHOUSE);
        	
        	 int index = 1;
             query.setString(index++, warehouse.getName());
             query.setInt(index++, warehouse.getNumberOfStorageShelves());
             
             int rowsAffected = query.executeUpdate();
             
             if (rowsAffected == 1) {
            	 everythingisok = true;
             }
             
		} catch (SQLException e) {
	            System.out.println("Failed to add warehouse.");
	            e.printStackTrace();
		}finally {
			
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when adding warehouse.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when inserting warehouse.");
                e.printStackTrace();
            }            
		}        
        
		return everythingisok;
	}

	@Override
	public List<WareHouse> getWareHouses() {
		Connection conn = null;
		Statement query = null;
		
		warehouses.clear();		
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.createStatement();
        	
        	ResultSet rs = query.executeQuery(SQL_SELECT_WAREHOUSES);
        	
        	while (rs.next()) {
				WareHouse warehouse = new WareHouse();
				
				warehouse.setName(rs.getString("name"));
				warehouse.setNumberOfStorageShelves(rs.getInt("numberOfStorageShelves"));
				
				warehouses.add(warehouse);
				
			}
		} catch (SQLException e) {
			System.out.println("Failed to retrieve the list of warehouses.");
            e.printStackTrace();
		}finally {
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying warehouses.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying warehouses.");
                e.printStackTrace();
            }
		}
		return warehouses;
	}

	@Override
	public boolean addStorage(Storage storage) {
		boolean everythingisok = false;
		
		Connection conn = null;
		PreparedStatement query = null;
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.prepareStatement(SQL_INSERT_STORAGE);
        	
        	 int index = 1;
             query.setInt(index++, storage.getNumberOfShelves());
             query.setFloat(index++, storage.getHeight());
             query.setFloat(index++, storage.getWidth());
             query.setFloat(index++, storage.getDepth());
             query.setInt(index++, storage.getWareHouseID());
             query.setFloat(index++, storage.getLoadCApacity());
            
             
             int rowsAffected = query.executeUpdate();
             
             if (rowsAffected == 1) {
            	 everythingisok = true;
             }
			
		} catch (SQLException e) {
			System.out.println("Failed to add Storage.");
            e.printStackTrace();
		}finally {
			
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when adding storage.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when inserting storage.");
                e.printStackTrace();
            }            
		}
		
		return everythingisok;
	}

	@Override
	public List<Storage> getStorages() {
		Connection conn = null;
		Statement query = null;
		
		storages.clear();
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.createStatement();
        	
        	ResultSet rs = query.executeQuery(SQL_SELECT_STORAGES);
        	
        	while (rs.next()) {
				Storage storage = new Storage();
								
				storage.setNumberOfShelves(rs.getInt("NumberOfShelves"));
				storage.setHeight(rs.getFloat("Height"));
				storage.setWidth(rs.getFloat("Width"));
				storage.setDepth(rs.getFloat("Depth"));
				storage.setWareHouseID(rs.getInt("WarehouseID"));
				storage.setLoadCApacity(rs.getFloat("LoadCapacity"));				
				
				storages.add(storage);
				
			}
		} catch (SQLException e) {
			System.out.println("Failed to retrieve the list of Storages");
            e.printStackTrace();
		}finally {
			
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying storages.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying storages.");
                e.printStackTrace();
            }    
            
		}
		
		
		return storages;
	}

	@Override
	public boolean addShelf(Shelf shelf) {
		boolean everythingisok = false;
		
		Connection conn = null;
		PreparedStatement query = null;
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.prepareStatement(SQL_INSERT_SHELF);
        	
        	int index = 1;            
        	query.setInt(index++, shelf.getStorageID());
        	query.setFloat(index++, shelf.getHeight());
        	query.setFloat(index++, shelf.getWidth());
            query.setFloat(index++, shelf.getDepth());            
            query.setFloat(index++, shelf.getLoadCApacity());
        	        	
        	int rowsAffected = query.executeUpdate();
            
            if (rowsAffected == 1) {
           	 everythingisok = true;
            }
			
		} catch (SQLException e) {
			System.out.println("Failed to add Shelf.");
            e.printStackTrace();
		}finally {
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when adding shelf.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when inserting shelf.");
                e.printStackTrace();
            }
		}
		
		return everythingisok;
	}

	@Override
	public List<Shelf> getShelves() {
		Connection conn = null;
		Statement query = null;
		
		shelves.clear();
		
		try {			
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.createStatement();
        	
        	ResultSet rs = query.executeQuery(SQL_SELECT_SHELVES);
        	
        	while (rs.next()) {
				Shelf shelf = new Shelf();
								
				shelf.setStorageID(rs.getInt("StorageID"));
				shelf.setHeight(rs.getFloat("Height"));
				shelf.setWidth(rs.getFloat("Width"));
				shelf.setDepth(rs.getFloat("Depth"));
				shelf.setLoadCApacity(rs.getFloat("LoadCapacity"));				
				
				shelves.add(shelf);						
				
			}			
		} catch (SQLException e) {
			System.out.println("Failed to retrieve the list of Shelves");
            e.printStackTrace();
		}finally {
			
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying shelves.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying shelves.");
                e.printStackTrace();
            }    
            
		}
		
		return shelves;
	}

	@Override
	public boolean addLocation(Location location) {
		
		boolean everythingisok = false;
		
		Connection conn = null;
		PreparedStatement query = null;
	
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.prepareStatement(SQL_INSERT_LOCATION);
        	
        	int index = 1;            
        	query.setInt(index++, location.getShelfID());
        	query.setFloat(index++, location.getHeight());
        	query.setFloat(index++, location.getWidth());
            query.setFloat(index++, location.getDepth());
            
        	int rowsAffected = query.executeUpdate();
            
            if (rowsAffected == 1) {
           	 everythingisok = true;
            }
			
		} catch (SQLException e) {
			System.out.println("Failed to add location.");
            e.printStackTrace();
			
		}finally {
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when adding location.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when inserting location.");
                e.printStackTrace();
            }
		}		
		
		return everythingisok;
	}

	@Override
	public List<Location> getLocations() {
		Connection conn = null;
		Statement query = null;
		
		locations.clear();
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.createStatement();
        	
        	ResultSet rs = query.executeQuery(SQL_SELECT_LOCATIONS);
        	
        	while (rs.next()) {
				Location location = new Location();
				
				location.setShelfID(rs.getInt("shelfid"));
				location.setHeight(rs.getFloat("height"));
				location.setWidth(rs.getFloat("width"));
				location.setDepth(rs.getFloat("depth"));
				
				locations.add(location);						
				
			}
		} catch (SQLException e) {
			System.out.println("Failed to retrieve the list of Locations");
            e.printStackTrace();
		}finally {
			
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying locations.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying locations.");
                e.printStackTrace();
            }    
            
		}
		
		return locations;
	}

	@Override
	public boolean addPackage(_Package _package) {
		
		boolean everythingisok = false;
		
		Connection conn = null;
		PreparedStatement query = null;
	
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.prepareStatement(SQL_INSERT_PACKAGE);
        	
        	int index = 1;            
        	query.setInt(index++, _package.getLocationID());
        	query.setFloat(index++, _package.getHeight());
        	query.setFloat(index++, _package.getWidth());
            query.setFloat(index++, _package.getDepth());
            query.setFloat(index++, _package.getWeight());
            
        	int rowsAffected = query.executeUpdate();
            
            if (rowsAffected == 1) {
           	 everythingisok = true;
            }
		} catch (SQLException e) {
			System.out.println("Failed to add package.");
            e.printStackTrace();
		}finally {
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when adding package.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when inserting package.");
                e.printStackTrace();
            }
		}	
		
		return everythingisok;
	}

	@Override
	public List<_Package> getPackages() {	
		Connection conn = null;
		Statement query = null;
		
		packages.clear();
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.createStatement();
        	
        	ResultSet rs = query.executeQuery(SQL_SELECT_PACKAGES);
        	
        	while (rs.next()) {
        		_Package _package = new _Package();
        		
        		_package.setLocationID(rs.getInt("locationid"));
        		_package.setHeight(rs.getFloat("height"));
        		_package.setWidth(rs.getFloat("width"));
        		_package.setDepth(rs.getFloat("depth"));
        		_package.setDepth(rs.getFloat("weight"));
					
        		packages.add(_package);
        		
			}			
		} catch (SQLException e) {
			System.out.println("Failed to retrieve the list of Packages");
            e.printStackTrace();
		}finally {
			
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying packages.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying packages.");
                e.printStackTrace();
            }    
            
		}
		
		return packages;
	}

	@Override
	public boolean addProduct(Product product) {
		boolean everythingisok = false;
		
		Connection conn = null;
		PreparedStatement query = null;
		
		try {			
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.prepareStatement(SQL_INSERT_PRODUCT);
        	
        	int index = 1;       	
        	query.setInt(index++, product.getManufacturerID());
        	query.setFloat(index++, product.getWeight());
        	query.setInt(index++, product.getQuantity());
        	query.setString(index++, product.getDescription());
        	        	
        	int rowsAffected = query.executeUpdate();
            
            if (rowsAffected == 1) {
           	 everythingisok = true;
            }
			
		} catch (SQLException e) {
			System.out.println("Failed ot add Product.");
			e.printStackTrace();
		}finally {
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when adding product.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when inserting product.");
                e.printStackTrace();
            }
		}	
		
		return everythingisok;
	}

	@Override
	public List<Product> getProducts() {
		Connection conn = null;
		Statement query = null;
		
		products.clear();
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.createStatement();
        	
        	ResultSet rs = query.executeQuery(SQL_SELECT_PRODUCTS);
        	
        	while (rs.next()) {
        		Product product = new Product();
        		
        		product.setManufacturerID(rs.getInt("manufacturerid"));
        		product.setWeight(rs.getFloat("weight"));
        		product.setQuantity(rs.getInt("quantity"));
        		product.setDescription(rs.getString("description"));
					
        		products.add(product);
        		
			}
		} catch (SQLException e) {
			System.out.println("Failed tor retrieve Products");
			e.printStackTrace();
		}finally {
			
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying packages.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying packages.");
                e.printStackTrace();
            }                
		}
		
		return products;
	}

	@Override
	public boolean addManufacturer(Manufacturer manufacturer) {
		boolean everythingisok = false;
		
		Connection conn = null;
		PreparedStatement query = null;
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.prepareStatement(SQL_INSERT_MANUFACTURER);
        	
        	int index = 1;       	
        	query.setString(index++, manufacturer.getName());
        	query.setString(index++, manufacturer.getDescription());
        	        	
        	int rowsAffected = query.executeUpdate();
            
            if (rowsAffected == 1) {
           	 everythingisok = true;
            }
		} catch (SQLException e) {
			System.out.println("Failed to add Manufacturer.");
			e.printStackTrace();
		}finally {
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when adding manufacturer.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when inserting manufacturer.");
                e.printStackTrace();
            }
		}	
		
		return everythingisok;
	}

	@Override
	public List<Manufacturer> getManufacturers() {
		Connection conn = null;
		Statement query = null;
		
		manufacturers.clear();
		
		try {
			conn = DriverManager.getConnection(DATABASE);
        	
        	query = conn.createStatement();
        	
        	ResultSet rs = query.executeQuery(SQL_SELECT_MANUFACTURERS);
        	
        	while (rs.next()) {        		
        		Manufacturer manufacturer = new Manufacturer();        		
        		
        		manufacturer.setDescription(rs.getString("name"));
        		manufacturer.setDescription(rs.getString("description"));
					
        		manufacturers.add(manufacturer);        		
			}
			
		} catch (SQLException e) {
			System.out.println("Failed to retrieve the list of Manufacturers.");
			e.printStackTrace();
		}finally {
			
			try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying manufacturers.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying manufacturers.");
                e.printStackTrace();
            }                
		}
		
		return manufacturers;
	}

}
