package compositeSample;

public final class Program {
	
	private Program(){}
 
    public static void main(String[] args) {
        Leaf l1 = new Leaf(new PersonDataElement("Adam", "Eva", 1,1234.6f));
        Leaf l2 = new Leaf(new PersonDataElement("Eva", "Adam", 2,1234.6f));
        Leaf l3 = new Leaf(new PersonDataElement("Marie", "Eva", 3,1234.6f));
        Leaf l4 = new Leaf(new PersonDataElement("Kurt", "Eva", 4,1234.6f));
        Composite root = new Composite();
        root.add(l1);
        root.add(l2);
        root.add(l3);
        root.add(l4);
        root.operation();
    }
}