package directory_composit;

public final class Leaf implements Component {
	
	private String name;
	
	public Leaf(String name){this.name=name;}
 
	public void operation() {System.out.println("File: "+name);}

	@Override
	public String getName() {return name;}
}