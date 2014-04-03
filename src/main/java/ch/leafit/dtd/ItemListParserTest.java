package ch.leafit.dtd;

import java.util.ArrayList;

/**
 * This program parses an XML file containing an item list. The XML file should
 * reference the items.dtd
 */
public final  class ItemListParserTest {
	
	private ItemListParserTest(){}
	
	public static void main(String[] args) throws Exception {
		ItemListParser parser = new ItemListParser();
		ArrayList<?> items = parser.parse("items.xml");
		for (int i = 0; i < items.size(); i++) {
			Item anItem = (Item) items.get(i);
			System.out.println(anItem.format());
		}
	}
}
