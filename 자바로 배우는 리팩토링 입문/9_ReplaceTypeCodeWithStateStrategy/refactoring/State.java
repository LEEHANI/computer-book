package nineRefactoring;

/**
 * 리팩토링 후 Logger 객체의 switch 문이 없어지고, 
 * state.start(), state.stop(), state.log()의 행동을 state에 위임하는 형태로 바뀜
 * 
 * State가 null일 때 주의해야 함 
 */
public enum State 
{
	STOPPED
	{
		@Override
		public void start() 
		{
			System.out.println("** START LOGGING **");
		}

		@Override
		public void stop() 
		{
			/* Do nothing */
		}

		@Override
		public void log(String info) 
		{
			System.out.println("Ignoring: " + info);
		}
	},
	LOGGING
	{
		@Override
		public void start()
		{
			/* Do nothing */
		}

		@Override
		public void stop() 
		{
			System.out.println("** STOP LOGGING **");
		}

		@Override
		public void log(String info) 
		{
			System.out.println("Logging: " + info);
		}
	};
	
	public abstract void start();
	public abstract void stop();
	public abstract void log(String info);
}
