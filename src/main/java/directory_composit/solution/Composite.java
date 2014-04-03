package directory_composit.solution;

import java.util.List;
import java.util.ArrayList;

public final class Composite implements Component {
	
	private String name;
	
	public Composite(String name){this.name=name;}
 
    //Collection of child Components.
    private List<Component> childs = new ArrayList<Component>();
    
    public List<Component> getChilds(){return childs;}
 
    public void operation() {
    	System.out.println("Directory: "+name);
    	// the ask your children
        for (Component component : childs)  component.operation();
    }
 
    public void add(Component component) {childs.add(component);}
    public void remove(Component component) {childs.remove(component);}

	@Override
	public String getName() {return name;}
}
