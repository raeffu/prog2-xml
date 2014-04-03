package ch.leafit.domParserSample;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Use DOM Parser to display all books: isbn, title and authors.
 */
public final class DOMParserBookStore1 {
	
	private DOMParserBookStore1(){}
	
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = factory.newDocumentBuilder();
		File file = new File("bookStore_sample.xml");
		Document doc = docBuilder.parse(file);

		// Get a list of all <book> elements in the document
		NodeList bookNodes = doc.getElementsByTagName("book");
		for (int i = 0; i < bookNodes.getLength(); i++) {
			Element bookElement = (Element) bookNodes.item(i); // <book> element
			System.out.println("BOOK " + (i + 1));
			String isbn = bookElement.getAttribute("ISBN"); // <book> attribute
			System.out.println("\tISBN:\t" + isbn);

			// Get the child elements <title> of <book>, only one
			NodeList titleNodes = bookElement.getElementsByTagName("title");
			Element titleElement = (Element) titleNodes.item(0);
			System.out.println("\tTitle:\t" + titleElement.getTextContent());

			// Get the child elements <author> of <book>, one or more
			NodeList authorNodes = bookElement.getElementsByTagName("author");
			for (int author = 0; author < authorNodes.getLength(); author++) {
				Element authorElement = (Element) authorNodes.item(author);
				System.out.println("\tAuthor:\t"+ authorElement.getTextContent());
			}
		}
	}
}
