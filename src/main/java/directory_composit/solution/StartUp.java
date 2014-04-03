package directory_composit.solution;

import org.w3c.dom.Document;
import java.io.File;

public final class StartUp {
	
	private StartUp(){}

	public static void main(String[] args) throws Exception {
		String path = "C:\\temp\\";
		File root = new File(path);
		if (root.isDirectory()) { // we always start with a directory{
			String absPath = root.getAbsolutePath();
			Composite rootComposite = new Composite(absPath);
			DirectoryParsing.directoryParser(absPath, rootComposite);
			walker(rootComposite); // to demonstrate the composite design pattern
			// Create the XML document, based on the rootComposite element
			Document doc = DOMCreator.componentToXMLFile(rootComposite);
			if (doc != null) {
				File f = new File("dirOut.xml");
				// Print it to file
				DOMCreator.prettyPrint(doc, f);
				// Print it to console
				DOMCreator.prettyPrint(doc, System.out);
			}
			else System.out.println("Doc is null!");
		}
		else System.out.println("Path must be a directory!");
	}
	private static void walker(Component c) { if (c != null) c.operation();}
}