package directory_composit;

import java.io.File;

public final class DirectoryParsing {
	
	private DirectoryParsing(){}

    public static void directoryParser(String path, Composite rootComposite) {
        File root = new File( path );
        File[] list = root.listFiles();
        if(list == null) return;
        for(File f : list ) {
            if (f.isDirectory() ) {
            	String newPath= f.getAbsolutePath() ;
            	Composite composite = new Composite(newPath );
            	rootComposite.add(composite);
                directoryParser(newPath, composite);
            }
            else{
            	String newPath= f.getAbsolutePath() ;
            	Leaf leaf = new Leaf(newPath);
            	rootComposite.add(leaf);
            }
        }
    }
   
//    public static void main(String[] m) {
//        String path  = "C:/Users/lar02/Dropbox/BFH/Prog2/" ;
//        Component comp = new Composite(path);
//        directoryParser(path, (Composite)comp);
//        comp.operation();
//    }    
}