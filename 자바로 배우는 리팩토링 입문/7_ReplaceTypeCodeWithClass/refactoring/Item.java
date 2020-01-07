public class Item 
{
	public static final int TYPECODE_BOOK = ItemType.BOOK.getTypecdoe();
	public static final int TYPECODE_DVD = ItemType.DVD.getTypecdoe();
	public static final int TYPECODE_SOFTWARE = ItemType.SOFTWARE.getTypecdoe();
	
	private final ItemType itemtype;
	private String title;
	private int price;
	
	public Item(ItemType itemType, String title, int price)
	{
		this.itemtype = itemType;
		this.title = title;
		this.price = price;
	}
	
	public int getItemType()
	{
		return itemtype.getTypecdoe();
	}
}
