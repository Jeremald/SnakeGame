import java.awt.Component;
import javax.swing.JFrame;
import java.io.*;

public class SnakeRunner extends JFrame
{
	private static final int WIDTH = 900;
	private static final int HEIGHT = 700;
	public static void main(String[] args) throws IOException
	{
		JFrame frame = new JFrame();
		frame.setSize(WIDTH,HEIGHT);
		frame.setLocationRelativeTo(null);
		SnakeGame game = new SnakeGame();
		((Component)game).setFocusable(true);
		frame.getContentPane().add(game);
		frame.setTitle("Snake");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
