import java.awt.Color;
import java.awt.Graphics;

public class Food
{
	private int x;
	private int y;
	private Color c;
	public Food(Color col)
	{
		c = col;
	}
	public void spawnFood()
	{
		x = 10*(int)(Math.random()*(65-2)+2);
		y = 10*(int)(Math.random()*(64-2)+2);
	}
	public void draw(Graphics g)
	{
		g.setColor(c);
		g.fillRect(x,y,10,10);
	}
	public int getX()
	{
		return x;
	}
	public int getY()
	{
		return y;
	}
}
