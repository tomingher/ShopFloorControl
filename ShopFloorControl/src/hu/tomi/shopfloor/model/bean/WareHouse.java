package hu.tomi.shopfloor.model.bean;

public class WareHouse {

	private int id;
	private String name;
	private int numberOfStorageShelves;
	
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the numberOfStorageShelves
	 */
	public int getNumberOfStorageShelves() {
		return numberOfStorageShelves;
	}
	/**
	 * @param numberOfStorageShelves the numberOfStorageShelves to set
	 */
	public void setNumberOfStorageShelves(int numberOfStorageShelves) {
		this.numberOfStorageShelves = numberOfStorageShelves;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WareHouse [id=" + id + ", name=" + name + ", numberOfStorageShelves=" + numberOfStorageShelves + "]";
	}	

}
