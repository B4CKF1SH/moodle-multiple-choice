
public class XMLTools {
	
	private XMLTools() {}
	
	private static final String CDATA = "<![CDATA[%s]]>";
	
	public static String convertHTMLToCDATA(String htmlString) {
		
		return String.format(CDATA, htmlString.replace("]]>", "]]]]><![CDATA[>]]>"));
	}	
	
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
	
	public static int generateInitialId() {
		return  (int) ((Math.random() * 2000) + 2000);
	}
}
