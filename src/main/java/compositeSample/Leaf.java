package compositeSample;

/** "Leaf" */
public class Leaf implements Component {
 
	private PersonDataElement personDataElement;
	
	public Leaf(PersonDataElement personDataElement){
		this.personDataElement=personDataElement;
	}

	public void operation() {
        System.out.println("Leaf:"+personDataElement.toString());
    }
}