package ch.leafit.transformer_samples;

import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;

public final class XSLTToHTMLSample {
	
	private XSLTToHTMLSample(){}

	public static void main(String[] args) {

		String dataXML = "person_data.xml";
		String inputXSL = "person_converter.xsl";
		String outputHTML = "person_data_converted.html";

		try {TransformationTools.transform(dataXML, inputXSL, outputHTML);}
		catch(TransformerConfigurationException e) {
			System.err.println("TransformerConfigurationException");
			System.err.println(e);
		}
		catch (TransformerException e) {
			System.err.println("TransformerException");
			System.err.println(e);
		}
	}
}
