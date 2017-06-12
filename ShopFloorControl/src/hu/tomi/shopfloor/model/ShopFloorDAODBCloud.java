package hu.tomi.shopfloor.model;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import hu.tomi.shopfloor.model.bean.Location;
import hu.tomi.shopfloor.model.bean.LocationData;
import hu.tomi.shopfloor.model.bean.Manufacturer;
import hu.tomi.shopfloor.model.bean.Product;
import hu.tomi.shopfloor.model.bean.Shelf;
import hu.tomi.shopfloor.model.bean.ShelfData;
import hu.tomi.shopfloor.model.bean.Storage;
import hu.tomi.shopfloor.model.bean.WareHouse;
import hu.tomi.shopfloor.model.bean._Package;

public class ShopFloorDAODBCloud implements ShopFloorDAO {

    List<WareHouse> warehouses = new ArrayList<WareHouse>();
    List<String> warehousenamesStrings = new ArrayList<String>();
    List<Storage> storages = new ArrayList<Storage>();
    List<String> storagenameesStrings = new ArrayList<String>();
    List<Shelf> shelves = new ArrayList<Shelf>();
    List<ShelfData> shelfData = new ArrayList<ShelfData>();
    List<Product> products = new ArrayList<Product>();
    List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
    List<Location> locations = new ArrayList<Location>();
    List<LocationData> locationDataList = new ArrayList<LocationData>();
    List<_Package> packages = new ArrayList<_Package>();


    private static final String DATABASE = "jdbc:postgresql://horton.elephantsql.com:5432/bzmdrebs";

    private static final String USER = "bzmdrebs";

    private static final String PASSWORD = "57mdioyXo6FeAq5kTZXDq8DUzlpumxDD";

    private static final String SQL_SELECT_WAREHOUSES = "SELECT * FROM warehouse";

    private static final String SQL_SELECT_WAREHOUSENAMES = "SELECT name FROM warehouse";

    private static final String SQL_INSERT_WAREHOUSE =
            "INSERT INTO warehouse " +
                    "(name, numberofstorageshelves) " +
                    "VALUES (?, ?)";

    private static final String SQL_SELECT_STORAGES = "SELECT " +
            "s.id AS StorageID, " +
            "s.numberofshelves AS NumberOfShelves, " +
            "s.height AS height, " +
            "s.width AS width, " +
            "s.depth AS depth, " +
            "wh.name AS warehousename, " +
            "s.loadcapacity AS loadcapacity " +
            "FROM storage AS s " +
            "JOIN warehouse AS wh ON wh.id = s.warehouseid " +
            "ORDER BY wh.id, s.id;";

    private static final String SQL_SELECT_STORAGENAMES = "SELECT " +
            "wh.name || ' ' || st.id AS storagename " +
            "FROM warehouse AS wh " +
            "JOIN storage AS st ON st.warehouseid = wh.id " +
            "ORDER BY wh.ID";

    private static final String SQL_INSERT_STORAGE = "SELECT addStorage(?,?,?,?,?,?)";

    private static final String SQL_SELECT_SHELVES =
            "SELECT " +
                    "sh.id AS \"ID\", " +
                    "'Shelf ' || sh.id AS \"Shelf ID\", " +
                    "wh.name || ' ' || st.id || '. Storage' AS \"Storage Name\", " +
                    "st.id AS \"Storage ID\", " +
                    "sh.height AS \"Height\", " +
                    "sh.width AS \"Width\", " +
                    "sh.depth AS \"Depth\", " +
                    "sh.loadcapacity AS \"Load Capacity\" " +
                    "FROM shelf AS sh " +
                    "JOIN storage AS st ON st.id = sh.storageid " +
                    "JOIN warehouse AS wh ON wh.id = st.warehouseid " +
                    "ORDER BY sh.ID";

    private static final String SQL_SELECT_SHELFDATA =
            "SELECT wh.name AS \"Warehouse name\" " +
                    ",'Storage ' || st.id AS \"Storage name\" " +
                    ",'Shelf ' || sh.id AS \"Shelf\" " +
                    ",sh.width AS \"Shelf width\" " +
                    ",'Location' || lo.id AS \"Location\" " +
                    "FROM warehouse AS wh " +
                    "JOIN storage AS st ON st.warehouseid = wh.id " +
                    "JOIN shelf AS sh ON sh.storageid = st.id " +
                    "LEFT JOIN location AS lo ON lo.shelfid = sh.id " +
                    "ORDER BY wh.id, st.id, sh.id";

    private static final String SQL_INSERT_SHELF = "{call addshelf(?,?,?,?,?) }";

    private static final String SQL_SELECT_LOCATIONS = "SELECT * FROM Location";

    private static final String SQL_INSERT_LOCATION =
            "INSERT INTO location " +
                    "(ShelfID, Height, Width, Depth) " +
                    "VALUES (?, ?, ?, ?)";

    private static final String SQL_SELECT_LOCATIONDATA = "SELECT wh.name AS \"Warehouse name\"\n" +
            " ,'Storage ' || st.id AS \"Storage name\"\n" +
            " ,'Shelf ' || sh.id AS \"Shelf\"\n" +
            " ,sh.width AS \"Shelf width\"\n" +
            " ,coalesce('Location' || lo.id, 'Empty') AS \"Location\"\n" +
            " ,coalesce(cast(lo.width AS VARCHAR(50)), 'Empty') AS \"Location width\"\n" +
            " ,sh.width - coalesce(sum(lo.width), 0) AS \"Free width\"\n" +
            " FROM warehouse AS wh\n" +
            " JOIN storage AS st ON st.warehouseid = wh.id\n" +
            " JOIN shelf AS sh ON sh.storageid = st.id\n" +
            " LEFT JOIN location AS lo ON lo.shelfid = sh.id\n" +
            " LEFT JOIN location AS lo2 ON lo2.shelfid = sh.id\n" +
            " GROUP BY wh.name, st.id, sh.id, sh.width, lo.id, wh.id\n" +
            " ORDER BY wh.id, st.id, sh.id";

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

    public ShopFloorDAODBCloud() {

    }


    @Override
    public boolean addWareHouse(WareHouse warehouse) {

        boolean everythingisok = false;

        Connection conn = null;
        PreparedStatement query = null;

        try {

            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

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
        } finally {

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
                    System.out.println("Connection closed successfully");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.createStatement();

            ResultSet rs = query.executeQuery(SQL_SELECT_WAREHOUSES);

            while (rs.next()) {
                WareHouse warehouse = new WareHouse();
                warehouse.setId(rs.getInt("id"));
                warehouse.setName(rs.getString("name"));
                warehouse.setNumberOfStorageShelves(rs.getInt("numberOfStorageShelves"));

                warehouses.add(warehouse);

            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve the list of warehouses.");
            e.printStackTrace();
        } finally {
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
                    System.out.println("Connection closed successfully!");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying warehouses.");
                e.printStackTrace();
            }
        }
        return warehouses;
    }

    @Override
    public List<String> getWareHouseNames() {

        Connection conn = null;
        Statement query = null;

        warehousenamesStrings.clear();

        try {
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.createStatement();

            ResultSet rs = query.executeQuery(SQL_SELECT_WAREHOUSENAMES);

            while (rs.next()) {

                warehousenamesStrings.add(rs.getString("name"));

            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve the list of warehouses.");
            e.printStackTrace();
        } finally {
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
                    System.out.println("Connection closed successfully!");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying warehouses.");
                e.printStackTrace();
            }
        }
        return warehousenamesStrings;
    }


    @Override
    public boolean addStorage(Storage storage) {
        boolean everythingisok = false;

        Connection conn = null;
        CallableStatement query = null;

        try {
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.prepareCall("{call addStorage( ?,?,?,?,?,? ) }");

            int index = 1;
            query.setInt(index++, storage.getNumberOfShelves());
            query.setFloat(index++, storage.getHeight());
            query.setFloat(index++, storage.getWidth());
            query.setFloat(index++, storage.getDepth());
            query.setString(index++, storage.getWarehouseName());
            query.setFloat(index++, storage.getLoadCApacity());

            boolean procedurewentwell = query.execute();
            query.close();

            if (procedurewentwell == true) {
                everythingisok = true;
                System.out.println("Succesfully added storage.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to add Storage.");
            e.printStackTrace();
        } finally {

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
                    System.out.println("Connection closed succesfully!");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.createStatement();

            ResultSet rs = query.executeQuery(SQL_SELECT_STORAGES);

            while (rs.next()) {
                Storage storage = new Storage();
                storage.setId(rs.getInt("StorageID"));
                storage.setNumberOfShelves(rs.getInt("NumberOfShelves"));
                storage.setHeight(rs.getFloat("Height"));
                storage.setWidth(rs.getFloat("Width"));
                storage.setDepth(rs.getFloat("Depth"));
                storage.setWarehouseName(rs.getString("Warehousename"));
                storage.setLoadCApacity(rs.getFloat("LoadCapacity"));

                storages.add(storage);

            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve the list of Storages");
            e.printStackTrace();
        } finally {

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
                    System.out.println("Connection closed succesfully!");
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
        CallableStatement query = null;

        try {
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.prepareCall(SQL_INSERT_SHELF);
            //query = conn.prepareCall("{call addshelf(?,?,?,?,?) }");
            //SELECT addshelf('Masodik Raktar 5', 2,2,2,2);
            int index = 1;
            query.setString(index++, shelf.getStorageName());
            query.setFloat(index++, shelf.getHeight());
            query.setFloat(index++, shelf.getWidth());
            query.setFloat(index++, shelf.getDepth());
            query.setFloat(index++, shelf.getLoadCApacity());

            boolean procedurewentwell = query.execute();
            query.close();

            if (procedurewentwell == true) {
                everythingisok = true;
                System.out.println("Succesfully added shelf.");
            }

        } catch (SQLException e) {
            System.out.println("Failed to add Shelf.");
            e.printStackTrace();
        } finally {
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
                    System.out.println("Connection closed succesfully!");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.createStatement();

            ResultSet rs = query.executeQuery(SQL_SELECT_SHELVES);

            while (rs.next()) {
                Shelf shelf = new Shelf();

                shelf.setId(rs.getInt("ID"));
                shelf.setShelfID(rs.getString("Shelf ID"));
                shelf.setStorageID(rs.getInt("Storage ID"));
                shelf.setStorageName(rs.getString("Storage Name"));
                shelf.setHeight(rs.getFloat("Height"));
                shelf.setWidth(rs.getFloat("Width"));
                shelf.setDepth(rs.getFloat("Depth"));
                shelf.setLoadCApacity(rs.getFloat("Load Capacity"));

                shelves.add(shelf);

            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve the list of Shelves");
            e.printStackTrace();
        } finally {

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
                    System.out.println("Connection closed succesfully");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying shelves.");
                e.printStackTrace();
            }
        }

        return shelves;
    }

    @Override
    public List<ShelfData> getShelfData() {
        Connection conn = null;
        Statement query = null;

        shelfData.clear();

        try {
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.createStatement();

            ResultSet rs = query.executeQuery(SQL_SELECT_SHELFDATA);

            while (rs.next()) {
                ShelfData shelf = new ShelfData();

                shelf.setWarehouseName(rs.getString("Warehouse name"));
                shelf.setStorageName(rs.getString("Storage name"));
                shelf.setShelf(rs.getString("Shelf"));
                shelf.setShelfWidth(rs.getFloat("Shelf width"));
                shelf.setLocation(rs.getString("Location"));

                shelfData.add(shelf);

            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve the list of Shelf data");
            e.printStackTrace();
        } finally {

            try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying Shelf data.");
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed succesfully");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying Shelf data.");
                e.printStackTrace();
            }
        }

        return shelfData;
    }

    @Override
    public List<String> getStorageNames() {

        Connection conn = null;
        Statement query = null;

        storagenameesStrings.clear();

        try {
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.createStatement();

            ResultSet rs = query.executeQuery(SQL_SELECT_STORAGENAMES);

            while (rs.next()) {

                storagenameesStrings.add(rs.getString("storagename"));

            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve the list of storagenames.");
            e.printStackTrace();
        } finally {
            try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying storagename.");
                e.printStackTrace();
            }

            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed successfully!");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying storagenames.");
                e.printStackTrace();
            }
        }
        return storagenameesStrings;
    }

    @Override
    public boolean addLocation(Location location) {

        boolean everythingisok = false;

        Connection conn = null;
        PreparedStatement query = null;

        try {
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

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

        } finally {
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
                    System.out.println("Connection closed successfully!");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

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
        } finally {

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
                    System.out.println("Connection closed successfully");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying locations.");
                e.printStackTrace();
            }
        }

        return locations;
    }

    @Override
    public List<LocationData> getLocationData() {
        Connection conn = null;
        Statement query = null;

        locationDataList.clear();

        try {
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.createStatement();

            ResultSet rs = query.executeQuery(SQL_SELECT_LOCATIONDATA);

            while (rs.next()) {
                LocationData locationData = new LocationData();

                locationData.setWarehousename(rs.getString("Warehouse name"));
                locationData.setStoragename(rs.getString("Storage name"));
                locationData.setShelf(rs.getString("Shelf"));
                locationData.setShelfwidth(rs.getFloat("Shelf width"));
                locationData.setLocation(rs.getString("Location"));
                locationData.setLocationwidth(rs.getString("Location width"));
                locationData.setFreewidth(rs.getString("Free width"));

                locationDataList.add(locationData);

            }
        } catch (SQLException e) {
            System.out.println("Failed to retrieve the list of Location related data");
            e.printStackTrace();
        } finally {

            try {
                if (query != null) {
                    query.close();
                }
            } catch (SQLException e) {
                System.out.println("Failed to close query when querying location related data.");
                e.printStackTrace();
            }
            try {
                if (conn != null) {
                    conn.close();
                    System.out.println("Connection closed successfully");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying locations related data.");
                e.printStackTrace();
            }
        }

        return locationDataList;
    }

    @Override
    public boolean addPackage(_Package _package) {

        boolean everythingisok = false;

        Connection conn = null;
        PreparedStatement query = null;

        try {
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

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
        } finally {
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
                    System.out.println("Connection closed successfully!");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

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
        } finally {

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
                    System.out.println("Connection closed successfully!");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

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
        } finally {
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
                    System.out.println("Connectin closed successfully");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

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
        } finally {

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
                    System.out.println("Connection closed succesfully!");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

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
        } finally {
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
                    System.out.println("Connection closed successfully");
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
            conn = DriverManager.getConnection(DATABASE, USER, PASSWORD);

            if (conn != null) {
                System.out.println("Connected to the PostgreSQL CLOUD server successfully.");
            }

            query = conn.createStatement();

            ResultSet rs = query.executeQuery(SQL_SELECT_MANUFACTURERS);

            while (rs.next()) {
                Manufacturer manufacturer = new Manufacturer();

                manufacturer.setId(rs.getInt("id"));
                manufacturer.setName(rs.getString("name"));
                manufacturer.setDescription(rs.getString("description"));

                manufacturers.add(manufacturer);
            }

        } catch (SQLException e) {
            System.out.println("Failed to retrieve the list of Manufacturers.");
            e.printStackTrace();
        } finally {

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
                    System.out.println("Connection closed successfully");
                }
            } catch (SQLException e) {
                System.out.println("Failed to close connection when querying manufacturers.");
                e.printStackTrace();
            }
        }

        return manufacturers;
    }


}