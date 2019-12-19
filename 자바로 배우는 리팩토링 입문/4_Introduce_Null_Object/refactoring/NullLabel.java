package refactoring;

public class NullLabel extends RefLabel 
{
	public NullLabel() 
	{
		super("none");
	}
	
	@Override
	public boolean isNull()
	{
		return true;
	}
	
	@Override
	public void display()
	{
	}

}
