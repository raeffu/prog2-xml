package compositeSample;

public final class PersonDataElement {
	
	private String firstName, lastName;
	private int id;
	private float salary;
	
	public String getFirstName() {return firstName;}
	public void setFirstName(String firstName) {this.firstName = firstName;}
	public String getLastName() {return lastName;}
	public void setLastName(String lastName) {this.lastName = lastName;}
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	public float getSalary() {return salary;}
	public void setSalary(float salary) {this.salary = salary;}
	public PersonDataElement(String firstName, String lastName, int id, float salary) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.id = id;
		this.salary = salary;
	}
	
	public String toString(){return firstName;}
}
