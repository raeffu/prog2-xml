package directory_composit;

import java.io.File;
import java.io.PrintStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DOMCreator {

  public static Document componentToXMLFile(Composite rootComposite)
      throws ParserConfigurationException {

    System.out.println("");
    System.out.println("DOMCreator:");
    System.out.println(rootComposite.getName());

    // Create factory and docBuilder
    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

    // Get the Document object
    Document doc = docBuilder.newDocument();
    doc.setXmlStandalone(true);

    // Create the root element
    Element rootElement = doc.createElement("root");
    doc.appendChild(rootElement);

    addChildElements(doc, rootElement, rootComposite);

    return doc;
  }

  private static void addChildElements(Document doc, Element rootElement,
      Composite rootComposite) {
    
    List<Component> childs = rootComposite.getChilds();

    for (Component child : childs) {
      if (child instanceof Composite) {
        Element node = doc.createElement(child.getName().replace("\\", "_"));
        rootElement.appendChild(node);
        addChildElements(doc, node, (Composite) child);
      }
      else{
        Element element = doc.createElement(child.getName().replace("\\", "_"));
        rootElement.appendChild(element);
      }
    }
  }

  public static final void prettyPrint(Document xml, File file)
      throws Exception {
    Transformer tf = TransformerFactory.newInstance().newTransformer();
    tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    tf.setOutputProperty(OutputKeys.INDENT, "yes");
    StreamResult result = new StreamResult(file);
    Writer out = new StringWriter();
    tf.transform(new DOMSource(xml), result);
    System.out.println(out.toString());
  }

  public static final void prettyPrint(Document xml, PrintStream file)
      throws Exception {
    Transformer tf = TransformerFactory.newInstance().newTransformer();
    tf.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
    tf.setOutputProperty(OutputKeys.INDENT, "yes");
    StreamResult result = new StreamResult(file);
    Writer out = new StringWriter();
    tf.transform(new DOMSource(xml), result);
    System.out.println(out.toString());
  }

}
