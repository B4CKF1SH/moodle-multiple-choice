import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a Moodle feedback form, consisting of multiple items
 * 
 * @author MMC Team
 *
 */
public class Feedback {
	
	//list of Questions
	private List<Item> items = new ArrayList<>();
	
	/**
	 * Removes an item from the Feedback
	 * @param index The index of the Item to remove
	 * @return The removed Item
	 */
	public Item removeItem(int index) {
		return items.remove(index);
	}
	
	/**
	 * Adds an item to the Feeedback
	 * @param q The Item to add
	 */
	public void addItem(Item q) {
		items.add(q);
	}

	/**
	 * Overriding toString() method to create XML
	 */
	@Override
	public String toString() {
		
		//XML header
		String value = String.format("<FEEDBACK VERSION=\"200701\" COMMENT=\"XML-Importfile for mod/feedback\">%n");
		
		//opening Items Tag
		value += String.format("<ITEMS>%n");
		
		//items
		for (Item q : items) {
			value += q.toString();
		}
		
		//closing Items tag
		value += String.format("</ITEMS>%n");
		
		//closing tag
		value += String.format("</FEEDBACK>%n");
		
		return value;
	}
}
