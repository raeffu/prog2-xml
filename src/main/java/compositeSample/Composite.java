package compositeSample;

import java.util.List;
import java.util.ArrayList;

public final class Composite implements Component {
 
    //Collection of child Components.
    private List<Component> childs = new ArrayList<Component>();
 
    public void operation() {
    	// first do your operation ....
    	// operationSelf...
    	System.out.println(this.toString());
    	
    	// the ask your children
        for (Component component : childs)  component.operation();
    }
 
    public void add(Component component) {childs.add(component);}
    public void remove(Component component) {childs.remove(component);}   
    public String toString(){return "Composite";}
}
