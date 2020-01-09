public class Main 
{
	public static void main(String[] args) {
		Shape line = new Shape(Shape.TYPECODE_LINE, 0, 0, 100, 200);
		Shape rectangle = new Shape(Shape.TYPECODE_RECTANGLE, 10, 20, 30, 40);
		Shape oval = new Shape(Shape.TYPECODE_OVAL, 100, 200, 300, 400);
		
		System.out.println(line.toString());
		System.out.println(rectangle.toString());
		System.out.println(oval.toString());
	}
}
