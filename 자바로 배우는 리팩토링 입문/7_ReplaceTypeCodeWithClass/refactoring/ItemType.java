public class ItemType {
	public static final ItemType BOOK = new ItemType(0);
	public static final ItemType DVD = new ItemType(1);
	public static final ItemType SOFTWARE = new ItemType(2);
	
	private int typecode;
	
	private ItemType(int typecode)
	{
		this.typecode = typecode;
	}
	
	public int getTypecdoe()
	{
		return this.typecode;
	}
}
