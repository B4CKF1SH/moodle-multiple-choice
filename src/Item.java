/**
 * Abstract class to represent a Moodle item
 * 
 * @author MMC Team
 *
 */
public abstract class Item {
	
	private int id;
	
	/**
	 * Simple constructor - does this really need documentation?
	 * @param id The Id of the Item 
	 */
	public Item(int id) {
		this.id = id;
	}

	/**
	 * Simple Getter - does this really need documentation?
	 * @return The Value
	 */
	public int getId() {
		return id;
	}
}
