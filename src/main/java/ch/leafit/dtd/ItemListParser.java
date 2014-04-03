package ch.leafit.dtd;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

/**
 * An XML parser for item lists
 */
public final class ItemListParser {
	/**
	 * Constructs a parser that can parse item lists
	 */
	public ItemListParser() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setIgnoringElementContentWhitespace(true);
		builder = factory.newDocumentBuilder();
	}

	/**
	 * Parses an XML file containing an item list
	 * 
	 * @param fileName
	 *            the name of the file
	 * @return an array list containing all items in the XML file
	 */
	public ArrayList<Item> parse(String fileName) throws SAXException,
			IOException {
		File f = new File(fileName);
		Document doc = builder.parse(f);

		// get the <items> root element

		Element root = doc.getDocumentElement();
		return getItems(root);
	}

	/**
	 * Obtains an array list of items from a DOM element
	 * 
	 * @param e
	 *            an <items> element
	 * @return an array list of all <item> children of e
	 */
	private static ArrayList<Item> getItems(Element e) {
		ArrayList<Item> items = new ArrayList<Item>();

		// get the <item> children

		NodeList children = e.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			Element childElement = (Element) children.item(i);
			Item c = getItem(childElement);
			items.add(c);
		}
		return items;
	}

	/**
	 * Obtains an item from a DOM element
	 * 
	 * @param e
	 *            an <item> element
	 * @return the item described by the given element
	 */
	private static Item getItem(Element e) {
		NodeList children = e.getChildNodes();

		Product p = getProduct((Element) children.item(0));

		Element quantityElement = (Element) children.item(1);
		Text quantityText = (Text) quantityElement.getFirstChild();
		int quantity = Integer.parseInt(quantityText.getData());

		return new Item(p, quantity);
	}

	/**
	 * Obtains a product from a DOM element
	 * 
	 * @param e
	 *            a <product> element
	 * @return the product described by the given element
	 */
	private static Product getProduct(Element e) {
		NodeList children = e.getChildNodes();

		Element descriptionElement = (Element) children.item(1);
		Text descriptionText = (Text) descriptionElement.getFirstChild();
		String description = descriptionText.getData();

		Element priceElement = (Element) children.item(1);
		Text priceText = (Text) priceElement.getFirstChild();
		double price = Double.parseDouble(priceText.getData());

		return new Product(description, price);
	}

	private DocumentBuilder builder;
}
