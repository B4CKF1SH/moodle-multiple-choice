
/**
 * Class to represent a Moodle MultiChoice item
 * 
 * @author MMC Team
 *
 */
public class MultiChoice extends Item {
	
	private String[] choices;
	private String description;
	private String label;
	private int dependId;
	private String dependChoice;
	
	/**
	 * MultiChoice constructor, used to instantiate levels of a multi-level feedback
	 * 
	 * @param id The Id of the Item
	 * @param description Description of the feedback Choice (shown)
	 * @param label Label of the feedback Choice (not shown)
	 * @param choices Possible choices
	 * @param dependId The dependency Item's Id
	 * @param dependChoice The depency choice
	 */
	public MultiChoice(int id, String description, String label, String[] choices, int dependId, String dependChoice) {
		super(id);
		this.description = description;
		this.label = label;
		this.choices = choices;
		this.dependId = dependId;
		this.dependChoice = dependChoice;
	}

	/**
	 * Overriding toString() method to create XML
	 */
	@Override
	public String toString() {
		
		String value = "";
		
		value += String.format("<ITEM TYPE=\"multichoice\" REQUIRED=\"1\">%n");
		value += String.format("<ITEMID>%n");
		value += String.format("%s%n", XMLTools.convertHTMLToCDATA(String.valueOf(this.getId())));
		value += String.format("</ITEMID>%n");
		value += String.format("<ITEMTEXT>%n");
		value += String.format("%s%n", XMLTools.convertHTMLToCDATA(this.description));
		value += String.format("</ITEMTEXT>%n");
		value += String.format("<ITEMLABEL>%n");
		value += String.format("%s%n", XMLTools.convertHTMLToCDATA(this.label));
		value += String.format("</ITEMLABEL>%n");
		value += String.format("<PRESENTATION>%n");
		value += String.format("%s%n", XMLTools.convertHTMLToCDATA(String.format("r>>>>>%s", XMLTools.convertChoices(this.choices))));
		value += String.format("</PRESENTATION>%n");
		value += String.format("<OPTIONS>%n");
		value += String.format("<![CDATA[]]>%n");
		value += String.format("</OPTIONS>%n");
		value += String.format("<DEPENDITEM>%n");
		value += String.format("%s%n", XMLTools.convertHTMLToCDATA(String.valueOf(this.dependId)));
		value += String.format("</DEPENDITEM>%n");
		value += String.format("<DEPENDVALUE>%n");
		value += String.format("%s%n", XMLTools.convertHTMLToCDATA(this.dependChoice));
		value += String.format("</DEPENDVALUE>%n");
		value += String.format("</ITEM>%n");
		

		return value;
	}

	/**
	 * Simple Getter - does this really need documentation?
	 * @return The Value
	 */
	public String[] getChoices() {
		return choices;
	}

	/**
	 * Simple Getter - does this really need documentation?
	 * @return The Value
	 */
	public int getDependId() {
		return dependId;
	}

	/**
	 * Simple Getter - does this really need documentation?
	 * @return The Value
	 */
	public String getDependChoice() {
		return dependChoice;
	}

	/**
	 * Simple Getter - does this really need documentation?
	 * @return The Value
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Simple Getter - does this really need documentation?
	 * @return The Value
	 */
	public String getLabel() {
		return label;
	}
}
