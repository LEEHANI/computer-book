package refactoring;

public class RefPerson 
{
	private RefLabel name;
	private RefLabel email;
	
	public RefPerson(RefLabel name, RefLabel email)
	{
		this.name = name;
		this.email = email;
	}
	
	public RefPerson(RefLabel name)
	{
		this(name, new NullLabel());
	}
	
	public void display()
	{
		name.display();
		email.display();
		System.out.println();
	}

	public RefLabel getName() {
		return name;
	}

	public void setName(RefLabel name) {
		this.name = name;
	}

	public RefLabel getEmail() {
		return email;
	}

	public void setEmail(RefLabel email) {
		this.email = email;
	}

	
}
