
public class Tile 
{
	private int x;
	private int y;
	public Tile()
	{
		x = y = 0;
	}
	public Tile(int xP, int yP)
	{
		x = xP;
		y = yP;
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
	public void setX(int xP)
	{
		x = xP;
	}
	public void setY(int yP)
	{
		y = yP;
	}
}
