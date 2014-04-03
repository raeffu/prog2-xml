package ch.leafit.saxParserExample;

import java.io.File;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class SAXPersonParser {
	
	private String currentElement;
	private int counter = 1;

	public SAXPersonParser() {
		try{
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			saxParser.parse(new File("PersonsTest.xml"), new PersonHandler());
		}
		catch (Exception e) {e.printStackTrace();}
	}

	public static void main(String args[]) {new SAXPersonParser();}

	public class PersonHandler extends DefaultHandler {

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			currentElement = qName;
			if (currentElement.equals("tns:person")) {
				System.out.println("Person " + counter);
				counter++;
				String nbr = attributes.getValue("personNbr");
				System.out.println("\tpersonNbr:\t" + nbr);
			}
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
			currentElement = "";
		}

		@Override
		public void characters(char[] chars, int start, int length) throws SAXException {
			if (currentElement.equals("tns:first_name")) 
				System.out.println("\tfirstName:\t"+ new String(chars, start, length));
			if (currentElement.equals("tns:last_name")) 
				System.out.println("\tlastName:\t"+ new String(chars, start, length));
		}
	}
}
