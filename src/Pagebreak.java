
/**
 * Class to represent a Moodle Pagebreak item
 * 
 * @author MMC Team
 *
 */
public class Pagebreak extends Item {
	
	/**
	 * Simple constructor - does this really need documentation?
	 * @param id The Id of the Item 
	 */
	public Pagebreak(int id) {
		super(id);
	}

	/**
	 * Overriding toString() method to create XML
	 */
	@Override
	public String toString() {
		
		String value = "";
		
		value += String.format("<ITEM TYPE=\"pagebreak\" REQUIRED=\"0\">%n");
		value += String.format("<ITEMID>%n");
		value += String.format("%s%n", XMLTools.convertHTMLToCDATA(String.valueOf(this.getId())));
		value += String.format("</ITEMID>%n");
		value += String.format("<ITEMTEXT>%n");
		value += String.format("<![CDATA[]]>%n");
		value += String.format("</ITEMTEXT>%n");
		value += String.format("<ITEMLABEL>%n");
		value += String.format("<![CDATA[]]>%n");
		value += String.format("</ITEMLABEL>%n");
		value += String.format("<PRESENTATION>%n");
		value += String.format("<![CDATA[]]>%n");
		value += String.format("</PRESENTATION>%n");
		value += String.format("<OPTIONS>%n");
		value += String.format("<![CDATA[]]>%n");
		value += String.format("</OPTIONS>%n");
		value += String.format("<DEPENDITEM>%n");
		value += String.format("<![CDATA[0]]>%n");
		value += String.format("</DEPENDITEM>%n");
		value += String.format("<DEPENDVALUE>%n");
		value += String.format("<![CDATA[]]>%n");
		value += String.format("</DEPENDVALUE>%n");
		value += String.format("</ITEM>%n");
		

		return value;
	}
}
