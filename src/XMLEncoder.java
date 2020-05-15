
public class XMLEncoder {
	
	private XMLEncoder() {}
	
	private static final String CDATA = "<![CDATA[%s]]>";
	
	public static String convertHTMLToCDATA(String htmlString) {
		
		return String.format(CDATA, htmlString.replace("]]>", "]]]]><![CDATA[>]]>"));
	}	
}
