package ch.leafit.domValidationSample;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Validate XML document specified via DTD using DOM Parser
 */
public final class DOMDTDValidation {
	
	private DOMDTDValidation(){}
	
	public static void main(String args[]) {
		try {
			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			// Enable the document validation as the document is being parsed.
			factory.setValidating(true);
			DocumentBuilder builder = factory.newDocumentBuilder();
			

			builder.setErrorHandler(new ErrorHandler() { // anonymous inner class
				// Ignore the fatal errors
				public void fatalError(SAXParseException exception) throws SAXException {}

				// Report validation errors
				public void error(SAXParseException e) throws SAXParseException {
					System.out.print("Error at line " + e.getLineNumber()+ ": ");
					System.out.println(e.getMessage());
				}

				// Report warnings
				public void warning(SAXParseException e) throws SAXParseException {
					System.out.print("Warning at line " + e.getLineNumber()+ ": ");
					System.out.println(e.getMessage());
				}
			});

			// External DTD validation
			Document xmlDocument = builder.parse(new File("bookstore-externalDTD_error.xml"));
			DOMSource source = new DOMSource(xmlDocument);
			
			// Open Declaration javax.xml.transform.stream.StreamResult
			// StreamResult: Acts as an holder for a transformation result,
			// which may be XML, plain Text, HTML, or some other form of markup.
			StreamResult result = new StreamResult(System.out);
			
			// Open Declaration javax.xml.transform.TransformerFactory
			// A TransformerFactory instance can be used to create javax.xml.transform
			// Transformer and javax.xml.transform.Templates objects.
			// The system property that determines which Factory implementation
			// to create is named "javax.xml.transform.TransformerFactory".
			// This property names a concrete subclass of the TransformerFactory abstract class.
			// If the property is not defined, a platform default is be used.
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			
			// We have to set up the external file: SYSTEM == external
			transformer.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM,"bookstore.dtd");
			transformer.transform(source, result);

			// Internal DTD validation
			xmlDocument = builder.parse(new File("bookstore-inlineDTD.xml"));
			source = new DOMSource(xmlDocument);
			result = new StreamResult(System.out);
			transformer.transform(source, result);
		}
		catch (Exception e) {e.printStackTrace();}
	}
}
