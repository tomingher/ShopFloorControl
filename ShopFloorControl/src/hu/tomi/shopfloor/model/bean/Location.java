package hu.tomi.shopfloor.model.bean;

public class Location {

	private int id;
	private int shelfID;
	private float height;
	private float width;
	private float depth;
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
	public int getShelfID() {
		return shelfID;
	}
	/**
	 * @param shelfID the shelfID to set
	 */
	public void setShelfID(int shelfID) {
		this.shelfID = shelfID;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Location [id=" + id + ", shelfID=" + shelfID + ", height=" + height + ", width=" + width + ", depth="
				+ depth + "]";
	}
	
	

}
