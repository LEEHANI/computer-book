package seven;

public enum ItemTypeEnum 
{
	BOOK(0),
	DVD(1),
	SOFTWARE(2);
	
	private final int typecode;
	
	private ItemTypeEnum(int typecode)
	{
		this.typecode = typecode;
	}
	
	public int getTypecode()
	{
		return this.typecode;
	}
}
