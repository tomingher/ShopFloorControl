package hu.tomi.shopfloor.model.bean;

public class Shelf {

	private int id;
	private String shelfID;
	private int storageID;
	private String storageName;
	private float height;
	private float width;
	private float depth;
	private float loadCApacity;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	
	/**
	 * @return the shelfID
	 */
	public String getShelfID() {
		return shelfID;
	}
	/**
	 * @param shelfID the shelfID to set
	 */
	public void setShelfID(String shelfID) {
		this.shelfID = shelfID;
	}
	/**
	 * @return the storageID
	 */
	public int getStorageID() {
		return storageID;
	}
	/**
	 * @param storageID the storageID to set
	 */
	public void setStorageID(int storageID) {
		this.storageID = storageID;
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
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	/**
	 * @return the width
	 */
	public float getWidth() {
		return width;
	}
	/**
	 * @param width the width to set
	 */
	public void setWidth(float width) {
		this.width = width;
	}
	/**
	 * @return the depth
	 */
	public float getDepth() {
		return depth;
	}
	/**
	 * @param depth the depth to set
	 */
	public void setDepth(float depth) {
		this.depth = depth;
	}
	/**
	 * @return the loadCApacity
	 */
	public float getLoadCApacity() {
		return loadCApacity;
	}
	/**
	 * @param loadCApacity the loadCApacity to set
	 */
	public void setLoadCApacity(float loadCApacity) {
		this.loadCApacity = loadCApacity;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Shelf [id=" + id + ", storageID=" + storageID + ", height=" + height + ", width=" + width + ", depth="
				+ depth + ", loadCApacity=" + loadCApacity + "]";
	}
	
	

}
