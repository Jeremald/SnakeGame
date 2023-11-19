import javax.swing.*;
import java.awt.Color;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.net.*;
import java.io.*;
import java.util.Scanner;

public class SnakeGame extends JPanel
{
	private Player player;
	private KeyControls kc;
	private Food food;
	private int state;
	private boolean isFood;
	private boolean hasMoved = false;
	private int score;
	private int hiscore;
	private BufferedImage scoreP;
	private BufferedImage gameOver;
	private BufferedImage highScore;
	private FileWriter fw;
	private BufferedWriter bw;
	private Font font = new Font("Tahoma", Font.PLAIN, 50);
	public SnakeGame() throws IOException
	{
		player = new Player(10,0,Color.GREEN);
		//add controller
		kc = new KeyControls();
		addKeyListener(kc);
		setFocusable(true);
		//add food
		food = new Food(Color.GREEN);
		isFood = false;
		//setup game
		setBackground(Color.BLACK);
        setVisible(true);
        score = 0;
        state = 1;
        //load high score
        Scanner file = new Scanner(new File("hiscore.dat"));
        hiscore = file.nextInt();
        fw = null;
        bw = null;
        //load images
        scoreP = ImageIO.read(new File("images/score.png"));
        gameOver = ImageIO.read(new File("images/GameOver.png"));
        highScore = ImageIO.read(new File("images/highScore.png"));
        //game loop
        Timer t = new Timer(30, new ActionListener()
        {
        	public void actionPerformed(ActionEvent e)
        	{
        		checkKeyMovement();
        		update();
        		hasMoved = true;
        		repaint();
        	}
        });
        t.start();
	}
	
	public void update()
	{
		if(state==1)
		{
			player.addTile();
			if(!isFood)
			{
				food.spawnFood();
				isFood = true;
			}
		}
		if(player.hitWall()||player.hitSelf())
		{
			state = 2;
		}
		if(hitFood())
		{
			player.setTouchFood(true);
			food.spawnFood();
			score++;
			if(score>hiscore)
			{
				hiscore = score;
				writeHighScore();
			}
		}
	}
	//repaints screen
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if(state==1||state==2)
		{
			//draws player
			if(state==1)
			{
			player.draw(g);
			//draws food
			food.draw(g);
			}
			//draws walls
			g.setColor(Color.RED);
			g.fillRect(15,15,650,5);
			g.fillRect(15,15,5,635);
			g.fillRect(15,650,650,5);
			g.fillRect(660,15,5,635);
			//draw score
			//g.fillRect(680,15,200,100);
			g.drawImage(scoreP, 680, 15, null);
			g.drawImage(highScore, 680, 150, null);
			g.setColor(Color.WHITE);
			g.setFont(font);
			g.drawString(""+score, 680, 120);
			g.drawString(""+hiscore, 680, 255);
		}
		//game over screen
		if(state==2)
		{
			if(player.getTiles().size()>1) //Show snake cave in
				player.draw(g);
			else
				g.drawImage(gameOver, 40, 200, null);
		}
	}
	public boolean hitFood()
	{
		for(int i = 0; i<player.getTiles().size(); i++)
		{
			if(player.getTiles().get(i).getX()==food.getX()&&player.getTiles().get(i).getY()==food.getY())
			{
				return true;
			}
		}
		return false;
	}
	public void writeHighScore()
	{
		try{
			fw = new FileWriter("hiscore.dat");
			bw = new BufferedWriter(fw);
			bw.write(""+hiscore);
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			try {
				if (bw != null)
					bw.close();
				if (fw != null)
					fw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	public void checkKeyMovement()
	{
		if(state==1)
		{
			//moving up
			if(kc.getW())
			{
				if(player.getYVel()==0&&hasMoved)
				{
					hasMoved = false;
					player.setXVel(0);
					player.setYVel(-10);
				}
			}
			//moving left
			if(kc.getA())
			{
				if(player.getXVel()==0&&hasMoved)
				{
					hasMoved = false;
					player.setYVel(0);
					player.setXVel(-10);
				}
			}
			//moving down
			if(kc.getS())
			{
				if(player.getYVel()==0&&hasMoved)
				{
					hasMoved = false;
					player.setXVel(0);
					player.setYVel(10);
				}
			}
			//moving right
			if(kc.getD())
			{
				if(player.getXVel()==0&&hasMoved)
				{
					hasMoved = false;
					player.setYVel(0);
					player.setXVel(10);
				}
			}
		}
		if(state==2)
		{
			//resets game
			if(kc.getSpace())
			{
				state = 1;
				isFood = false;
				player = new Player(10,0,Color.GREEN);
				score = 0;
			}
		}
	}
}
