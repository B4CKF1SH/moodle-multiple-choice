import java.util.ArrayList;
import java.util.List;

public class Feedback {
	
	//list of Questions
	private List<Item> items = new ArrayList<>();
	
	/**
	 * Removes a question from the Quiz
	 * @param index The index of the Question to remove
	 * @return The removed Question
	 */
	public Item removeQuestion(int index) {
		return items.remove(index);
	}
	
	/**
	 * Adds a question to the Quiz
	 * @param q The Question to add
	 */
	public void addQuestion(Item q) {
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
