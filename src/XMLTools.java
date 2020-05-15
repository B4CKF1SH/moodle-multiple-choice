/**
 * Helper class to store useful static methods in
 * 
 * @author MMC Team
 *
 */
public class XMLTools {
	
	/**
	 * private constructor to hide default public one
	 */
	private XMLTools() {}
	
	private static final String CDATA = "<![CDATA[%s]]>";
	
	/**
	 * Wraps text in CDATA blocks - mostly used to store html data in XML
	 * @param htmlString input text
	 * @return output text
	 */
	public static String convertHTMLToCDATA(String htmlString) {
		
		return String.format(CDATA, htmlString.replace("]]>", "]]]]><![CDATA[>]]>"));
	}	
	
	/**
	 * Converts an Array of Strings into a single String concerning Moodle's XML format
	 * @param choices input choices
	 * @return output text
	 */
	public static String convertChoices(String[] choices) {
		
		String value = "";
		
		for (int i = 0; i < choices.length; i++) {
			value += choices[i];
			
			if (i < choices.length - 1) {
				value += String.format("%n|");
			}
		}
		
		return value;
	}	
	
	/**
	 * Generates a random Id between 2000 and 4000, as Moodle Item Ids seem to be site-wide
	 * @return generated Id
	 */
	public static int generateInitialId() {
		return  (int) ((Math.random() * 2000) + 2000);
	}
}
