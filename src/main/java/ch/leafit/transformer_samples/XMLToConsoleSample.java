package ch.leafit.transformer_samples;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

public final class XMLToConsoleSample {

	private XMLToConsoleSample() {}

	public static void main(String[] args) {
		String dataXML = "person_data.xml";
		File sourceXmlFile = new File(dataXML);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try{dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(sourceXmlFile);
			TransformationTools.prettyPrint(doc, System.out);
			System.out.println("New file " + System.out+ " successfully created.");
		}
		catch (Exception e) {
			System.err.println("Exception");
			System.err.println(e);
		}
	}
}
