import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyControls implements KeyListener
{
	private boolean w;
	private boolean a;
	private boolean s;
	private boolean d;
	private boolean space;
	
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_W)
			w = true;
		else w = false;
		if(e.getKeyCode()==KeyEvent.VK_A)
			a = true;
		else a = false;
		if(e.getKeyCode()==KeyEvent.VK_S)
			s = true;
		else s = false;
		if(e.getKeyCode()==KeyEvent.VK_D)
			d = true;
		else d = false;
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
			space = true;
	}
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode()==KeyEvent.VK_W)
			w = false;
		if(e.getKeyCode()==KeyEvent.VK_A)
			a = false;
		if(e.getKeyCode()==KeyEvent.VK_S)
			s = false;
		if(e.getKeyCode()==KeyEvent.VK_D)
			d = false;
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
			space = false;
	}
	public boolean getW(){return w;}
	public boolean getA(){return a;}
	public boolean getS(){return s;}
	public boolean getD(){return d;}
	public boolean getSpace(){return space;}
	
	public void keyTyped(KeyEvent e){}
}
