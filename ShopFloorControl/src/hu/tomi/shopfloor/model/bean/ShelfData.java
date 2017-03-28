package hu.tomi.shopfloor.model.bean;

public class ShelfData {
	
	private String warehouseName;
	private String storageName;
	private String shelf;
	private float  shelfWidth;
	private String location;
	
	
	/**
	 * @return the warehouseName
	 */
	public String getWarehouseName() {
		return warehouseName;
	}
	/**
	 * @param warehouseName the warehouseName to set
	 */
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	/**
	 * @return the storageName
	 */
	public String getStorageName() {
		return storageName;
	}
	/**
	 * @param storageName the storageName to set
	 */
	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}
	/**
	 * @return the shelf
	 */
	public String getShelf() {
		return shelf;
	}
	/**
	 * @param shelf the shelf to set
	 */
	public void setShelf(String shelf) {
		this.shelf = shelf;
	}
	/**
	 * @return the shelfWidth
	 */
	public float getShelfWidth() {
		return shelfWidth;
	}
	/**
	 * @param shelfWidth the shelfWidth to set
	 */
	public void setShelfWidth(float shelfWidth) {
		this.shelfWidth = shelfWidth;
	}
	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * @param locationWidth the location to set
	 */
	public void setLocation(String location) {
		this.location = location;		
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ShelfData [warehouseName=" + warehouseName + ", storageName=" + storageName + ", shelf=" + shelf
				+ ", shelfWidth=" + shelfWidth + ", locationWidth=" + location + "]";
	}
	
	

}
