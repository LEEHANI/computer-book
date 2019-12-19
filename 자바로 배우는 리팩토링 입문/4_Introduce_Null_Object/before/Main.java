package before;

import refactoring.RefLabel;
import refactoring.RefPerson;

public class Main 
{
	public static void main(String[] args) 
	{
		origin();
		System.out.println();
		refactoring();
	}
	
	public static void origin()
	{
		Person person = new Person(new Label("may"), new Label("rorean@naver.com"));
		Person person2 = new Person(new Label("may2"));
		
		person.display();
		person2.display();
	}
	
	public static void refactoring()
	{
		RefPerson person = new RefPerson(new RefLabel("may1"), new RefLabel("rorean@naver.com"));
		RefPerson person2 = new RefPerson(new RefLabel("may2"));
		
		person.display();
		System.out.println("person email null? :" + person.getEmail().isNull());
		
		person2.display();
		System.out.println("person2 email null? :" + person2.getEmail().isNull());
	}
}
