package hu.tomi.shopfloor.model.bean;

public class Product {

	private int id;
	private int manufacturerID;
	private float weight;
	private int quantity;
	private String Description;
	
	
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
	 * @return the manufacturerID
	 */
	public int getManufacturerID() {
		return manufacturerID;
	}
	/**
	 * @param manufacturerID the manufacturerID to set
	 */
	public void setManufacturerID(int manufacturerID) {
		this.manufacturerID = manufacturerID;
	}
	/**
	 * @return the weight
	 */
	public float getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(float weight) {
		this.weight = weight;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return Description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		Description = description;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", manufacturerID=" + manufacturerID + ", weight=" + weight + ", quantity="
				+ quantity + ", Description=" + Description + "]";
	}
	
	

}
