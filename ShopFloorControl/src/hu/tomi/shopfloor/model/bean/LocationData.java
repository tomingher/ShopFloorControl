package hu.tomi.shopfloor.model.bean;

/**
 * Created by meszarostamas on 2017. 06. 11..
 */
public class LocationData {

    private String warehousename;
    private String storagename;
    private String shelf;
    private float shelfwidth;
    private String location;
    private String locationwidth;
    private String freewidth;

    public String getWarehousename() {
        return warehousename;
    }

    public void setWarehousename(String warehousename) {
        this.warehousename = warehousename;
    }

    public String getStoragename() {
        return storagename;
    }

    public void setStoragename(String storagename) {
        this.storagename = storagename;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public float getShelfwidth() {
        return shelfwidth;
    }

    public void setShelfwidth(float shelfwidth) {
        this.shelfwidth = shelfwidth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocationwidth() {
        return locationwidth;
    }

    public void setLocationwidth(String locationwidth) {
        this.locationwidth = locationwidth;
    }

    public String getFreewidth() {
        return freewidth;
    }

    public void setFreewidth(String freewidth) {
        this.freewidth = freewidth;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "warehousename='" + warehousename + '\'' +
                ", storagename='" + storagename + '\'' +
                ", shelf='" + shelf + '\'' +
                ", shelfwidth=" + shelfwidth +
                ", location='" + location + '\'' +
                ", locationwidth='" + locationwidth + '\'' +
                ", freewidth='" + freewidth + '\'' +
                '}';
    }
}
