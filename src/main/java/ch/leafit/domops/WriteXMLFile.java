package ch.leafit.domops;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.transform.OutputKeys;

public final class WriteXMLFile {
	
	private WriteXMLFile(){}

	public static void main(String argv[]) {

		try {

			// Create factory and docBuilder
			DocumentBuilderFactory docFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// Get the Document object
			Document doc = docBuilder.newDocument();
			doc.setXmlStandalone(true);

			// Create the root element
			Element rootElement = doc.createElement("root");
			doc.appendChild(rootElement);

			// Create some elements
			Element staff = doc.createElement("child1");
			rootElement.appendChild(staff);
			staff.setAttribute("id", "1");

			Element firstname = doc.createElement("first_name");
			firstname.appendChild(doc.createTextNode("Max"));
			staff.appendChild(firstname);

			Element lastname = doc.createElement("last_name");
			lastname.appendChild(doc.createTextNode("Mueller"));
			staff.appendChild(lastname);

			Element nickname = doc.createElement("date_of_birth");
			nickname.appendChild(doc.createTextNode("12.12.2012"));
			staff.appendChild(nickname);

			Element salary = doc.createElement("salary");
			salary.appendChild(doc.createTextNode("70000"));
			staff.appendChild(salary);

			Element el2 = doc.createElement("child2");
			rootElement.appendChild(el2);
			el2.setAttribute("id", "2");

			firstname = doc.createElement("first_name");
			firstname.appendChild(doc.createTextNode("Marie"));
			el2.appendChild(firstname);

			lastname = doc.createElement("last_name");
			lastname.appendChild(doc.createTextNode("Mueller"));
			el2.appendChild(lastname);

			nickname = doc.createElement("date_of_birth");
			nickname.appendChild(doc.createTextNode("12.11.2010"));
			el2.appendChild(nickname);

			salary = doc.createElement("salary");
			salary.appendChild(doc.createTextNode("78000"));
			el2.appendChild(salary);

			// Write document to file
			prettyPrint(doc, new File("file5.xml"));
			
			// Display document on console
			prettyPrint(doc, System.out);
			System.out.println("File saved!");
			
		}
		catch (ParserConfigurationException pce) {pce.printStackTrace();}
		catch (TransformerException tfe) {tfe.printStackTrace();}
		catch (Exception tfe) {tfe.printStackTrace();}
	}

	public static final void prettyPrint(Document xmlDoc, File file) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty(OutputKeys.METHOD, "xml");
		StreamResult result = new StreamResult(file);
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xmlDoc), result);
		System.out.println(out.toString());
	}

	public static final void prettyPrint(Document xmlDoc, PrintStream file) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		tf.setOutputProperty(OutputKeys.METHOD, "xml");
		StreamResult result = new StreamResult(file);
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xmlDoc), result);
		System.out.println(out.toString());
	}
}