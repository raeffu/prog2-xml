package ch.leafit.saxParserExample;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * Use SAX Parser to display all books: isbn, title and authors.
 */
public final class SAXParserBookstore {
	
	private String currentElement;
	private int bookCount = 1;

	// Constructor
	public SAXParserBookstore() {
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(new File("bookstore_sample.xml"), new MyHandler());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Entry main method
	public static void main(String args[]) {new SAXParserBookstore();}

	/*
	 * Inner class for the Callback Handlers.
	 */
	class MyHandler extends DefaultHandler {
		// Callback to handle element start tag
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			currentElement = qName;
			if (currentElement.equals("book")) {
				System.out.println("Book " + bookCount);
				bookCount++;
				String isbn = attributes.getValue("ISBN");
				System.out.println("\tISBN:\t" + isbn);
			}
		}

		// Callback to handle element end tag
		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			currentElement = "";
		}

		// Callback to handle the character text data inside an element
		@Override
		public void characters(char[] chars, int start, int length) throws SAXException {
			if (currentElement.equals("title")) 
				System.out.println("\tTitle:\t"+ new String(chars, start, length));
			 else if (currentElement.equals("author")) 
				System.out.println("\tAuthor:\t"+ new String(chars, start, length));
		}
	}
}
