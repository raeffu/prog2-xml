package ch.leafit.transformer_samples;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public final class XMLToFileSample {

	private XMLToFileSample() {}

	public static void main(String[] args) {
		String dataXML = "person_data.xml";
		String outputXML = "person_data_converted.xml";
		File sourceXmlFile = new File(dataXML);
		File destinationXmlFile = new File(outputXML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(sourceXmlFile);
			TransformationTools.prettyPrint(doc, destinationXmlFile);
			System.out.println("New file " + outputXML
					+ " successfully created.");
		}
		catch (Exception e) {
			System.err.println("Exception");
			System.err.println(e);
		}
	}
}
