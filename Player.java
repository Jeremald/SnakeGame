import java.util.ArrayList;
import java.awt.Color;
import java.awt.Graphics;

public class Player 
{
	private ArrayList<Tile> tiles;
	private int xVel;
	private int yVel;
	private Color c;
	private int count;
	private boolean touchFood;
	public Player(int xS, int yS, Color color)
	{
		//Instanceates variables
		tiles = new ArrayList<Tile>();
		xVel = xS;
		yVel = yS;
		c = color;
		count = 0;
		touchFood = false;
		tiles.add(new Tile(20,50));
	}
	public void addTile()
	{
		Tile t = tiles.get(tiles.size()-1);
		tiles.add(new Tile(t.getX()+xVel, t.getY()+yVel));
		
	}
	//draws new tile, removes old one
	public void draw(Graphics g)
	{
		//Draws Snake
		g.setColor(c);
		for(int i = 0; i<tiles.size(); i++)
		{
		Tile t = tiles.get(i);
		g.fillRect(t.getX(),t.getY(),10,10);
		}
		//Removes last tile
		if(tiles.size()>1&&count>=5&&!touchFood)
		{
			g.setColor(Color.BLACK);
			Tile t = tiles.get(0);
			g.fillRect(t.getX(),t.getY(),10,10);
			tiles.remove(0);
		}
		if(touchFood)
			touchFood = false;
		if(count<6)
		count++;
	}
	//checks if player hit wall
	public boolean hitWall()
	{
		Tile t = tiles.get(tiles.size()-1);
		if(t.getX()<20||t.getX()>650)
			return true;
		if(t.getY()<20||t.getY()>=650)
			return true;
		return false;
	}
	//checks if player hit self
	public boolean hitSelf()
	{
		Tile t = tiles.get(tiles.size()-1);
		for(int i = 0; i<tiles.size()-1; i++)
		{
			Tile ti = tiles.get(i);
			if(t.getX()==ti.getX()&&t.getY()==ti.getY())
			{
				return true;
			}
		}
		return false;
	}
	
	public void setXVel(int xs)
	{
		xVel = xs;
	}
	public void setYVel(int ys)
	{
		yVel = ys;
	}
	public int getXVel()
	{
		return xVel;
	}
	public int getYVel()
	{
		return yVel;
	}
	public ArrayList<Tile> getTiles()
	{
		return tiles;
	}
	public void setTouchFood(boolean b)
	{
		touchFood = b;
	}
}
