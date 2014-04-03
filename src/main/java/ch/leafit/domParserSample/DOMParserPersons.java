package ch.leafit.domParserSample;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public final class DOMParserPersons {
	
	private DOMParserPersons(){}
	
	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		File file = new File("PersonsTest.xml");
		Document doc = docBuilder.parse(file);

		// Get a list of all elements in the document
		// The wild card * matches all tags
		NodeList list = doc.getElementsByTagName("*");
		int counter = 0;
		for (int i = 0; i < list.getLength(); i++) {
			// Get the elements book (attribute isbn), title, author
			Element element = (Element) list.item(i);
			String nodeName = element.getNodeName();
			if (nodeName.equals("tns:person")) {
				counter++;
				System.out.println("Person " + counter);
				String nbr = element.getAttribute("personNbr");
				System.out.println("\tNbr:\t" + nbr);
			}
			else if (nodeName.equals("tns:first_name")) 
				System.out.println("\tFirst name:\t"+ element.getChildNodes().item(0).getNodeValue());
			else if (nodeName.equals("tns:last_name")) 
				System.out.println("\tLast name:\t"+ element.getChildNodes().item(0).getNodeValue());
		}
	}
}
