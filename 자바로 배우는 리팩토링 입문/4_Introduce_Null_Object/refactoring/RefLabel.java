package refactoring;

public class RefLabel 
{
	private String value;
	
	public RefLabel(String value)
	{
		this.value = value;
	}
	
	public void display()
	{
		System.out.println("display " + value);
	}
	
	public boolean isNull()
	{
		return false;
	}
}
