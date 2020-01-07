public class Main {
	public static void main(String[] args) {
		
		Item book = new Item(ItemType.BOOK, "���迪��", 4800);
		Item dvd = new Item(ItemType.DVD, "Ư����", 3000);
		Item soft = new Item(ItemType.SOFTWARE, "Ʃ��", 3200);

		System.out.println(book.getItemType() + "/ " + book.toString());
		System.out.println(dvd.toString());
		System.out.println(soft.toString());
	}
}
