package hu.tomi.shopfloor.model.bean;

public class Storage {
	
	private int id;
	private int numberOfShelves;
	private float height;
	private float width;
	private float depth;
	private String warehouseName;
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
	 * @return the numberOfShelves
	 */
	public int getNumberOfShelves() {
		return numberOfShelves;
	}
	/**
	 * @param numberOfShelves the numberOfShelves to set
	 */
	public void setNumberOfShelves(int numberOfShelves) {
		this.numberOfShelves = numberOfShelves;
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
		return "Storage [id=" + id + ", numberOfShelves=" + numberOfShelves + ", height=" + height + ", width=" + width
				+ ", depth=" + depth + ", wareHouseID=" + warehouseName + ", loadCApacity=" + loadCApacity + "]";
	}
}
