package directory_composit.solution;

import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.transform.OutputKeys;

public final class DOMCreator {

	private DOMCreator() {}

	private static void addNext(Component comp, Element tempRoot, Document doc) {
		if (comp != null) {
			if (comp instanceof Composite) {
				String s = stringAdapt(comp.getName());
				Element element = doc.createElement("Directory");
				element.appendChild(doc.createTextNode(s));
				tempRoot.appendChild(element);
				for (Component c : ((Composite) comp).getChilds()) {
					Element el;
					if (c instanceof Composite) {
						el = doc.createElement("Directory");
						el.appendChild(doc.createTextNode(s));
						element.appendChild(el);
						addNext(c, el, doc);
					} else {
						el = doc.createElement("File");
						s = stringAdapt(c.getName());
						el.appendChild(doc.createTextNode(s));
						element.appendChild(el);
					}
				}
			}
			else{
				String s = stringAdapt(comp.getName());
				Element element = doc.createElement("File");
				element.appendChild(doc.createTextNode(s));
				tempRoot.appendChild(element);
			}
		}
	}

	public static Document componentToXMLFile(Component root) {
		Document doc = null;
		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			doc = docBuilder.newDocument();
			// Treat the root element
			if (root != null) {
				if(root instanceof Component) {
					String s = stringAdapt(root.getName());
					Element elem = doc.createElement("Start_Directory");
					elem.appendChild(doc.createTextNode(s));
					doc.appendChild(elem);
					for (Component c : ((Composite) root).getChilds()) {
						Element el;
						s = stringAdapt(c.getName());
						if (c instanceof Composite) {
							el = doc.createElement("Directory");
							el.appendChild(doc.createTextNode(s));
							elem.appendChild(el);
							addNext(c, el, doc);
						} else {
							el = doc.createElement("File");
							el.appendChild(doc.createTextNode(s));
							elem.appendChild(el);
						}
					}
				}
			}
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (Exception tfe) {
			tfe.printStackTrace();
		}
		return doc;
	}

	public static final void prettyPrint(Document xml, File file) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(file);
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xml), result);
		System.out.println(out.toString());
	}

	public static final void prettyPrint(Document xml, PrintStream file) throws Exception {
		Transformer tf = TransformerFactory.newInstance().newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		tf.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(file);
		Writer out = new StringWriter();
		tf.transform(new DOMSource(xml), result);
		System.out.println(out.toString());
	}

	private static String stringAdapt(String s) {
		s = s.replace('\\', '_');
		s = s.replace(':', '_');
		return s;
	}
}