package before;

public class Person 
{
	private Label name;
	private Label email;
	
	public Person(Label name, Label email)
	{
		this.name = name;
		this.email = email;
	}
	
	public Person(Label name)
	{
		this(name, null);
	}
	
	public void display()
	{
		if(name != null)
		{
			name.display();
		}
		
		if(email != null)
		{
			email.display();
		}
		System.out.println();
	}

	public Label getName() {
		return name;
	}

	public void setName(Label name) {
		this.name = name;
	}

	public Label getEmail() {
		return email;
	}

	public void setEmail(Label email) {
		this.email = email;
	}

	
}
