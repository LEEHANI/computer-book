public abstract class AbstractShape 
{
	public static final int TYPECODE_LINE = 0;
	public static final int TYPECODE_RECTANGLE = 1;
	public static final int TYPECODE_OVAL = 2;
	
	private final int startx;
	private final int starty;
	private final int endx;
	private final int endy;
	
	protected AbstractShape(int startx, int starty, int endx, int endy)
	{
		this.startx = startx;
		this.starty = starty;
		this.endx = endx;
		this.endy = endy;
	}
	
	public static AbstractShape createShapeLine(int startx, int starty, int endx, int endy)
	{
		return new ShapeLine(startx, starty, endx, endy);
	}
	
	public static AbstractShape createShapeRectangle(int startx, int starty, int endx, int endy)
	{
		return new ShapeRectangle(startx, starty, endx, endy);
	}
	
	public static AbstractShape createShapeOval(int startx, int starty, int endx, int endy)
	{
		return new ShapeOval(startx, starty, endx, endy);
	}
	
	public abstract int getTypecode();
	
	public abstract String getName();
	
	public abstract void draw();
	
	@Override
	public String toString()
	{
		return getName() + ": " + startx + ", " + starty + "/ " + endx + ", " + endy;
	}
}
