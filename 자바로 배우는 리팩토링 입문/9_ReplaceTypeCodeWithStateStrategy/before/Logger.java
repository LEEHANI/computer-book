package nineBefore;

public class Logger 
{
	public static final int STATE_STOPPED = 0;
	public static final int STATE_LOGGING = 1;
	
	private int state;
	
	public Logger()
	{
		this.state = STATE_STOPPED;
	}
	
	public void start()
	{
		switch (state)
		{
			case STATE_STOPPED:
				System.out.println("** START LOGGING **");
				state = STATE_LOGGING;
				break;
				
			case STATE_LOGGING:
				break;	
			
			default:
				System.out.println("Inbalid state: " + state);
		}
	}
	
	public void stop()
	{
		switch (state)
		{
			case STATE_STOPPED:
				break;
				
			case STATE_LOGGING:
				System.out.println("** STOP LOGGING **");
				state = STATE_STOPPED;
				break;	
			
			default:
				System.out.println("Inbalid state: " + state);
		}
	}
	
	public void log(String info)
	{
		switch (state)
		{
			case STATE_STOPPED:
				System.out.println("Ignoring: " + info);
				break;
				
			case STATE_LOGGING:
				System.out.println("Logging: " + info);
				break;	
			
			default:
				System.out.println("Inbalid state: " + state);
		}
	}
}
