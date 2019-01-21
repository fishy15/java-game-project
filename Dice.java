// Aaryan Prakash
// 10/24/18
// Dice.java
// When the user clicks on the die to roll it, it rolls, shows the die
// end result, and tells the Game class the result.

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class Dice extends JPanel
{
	private Game currentGame; // instance of the current game
	private int rollNum; // number rolled by dice
	private int[] dotX; // x coordinates of the dots that make up dice
	private int[] dotY; // y coordinates of the dots that make up dice
	// indices of coordinate variables are for dots in positions below
	
	/*   ___________
	 *   | 0     4 |
	 *   |         |
	 *   | 1  3  5 |
	 *   |         |
	 *   | 2     6 |
	 *   ~~~~~~~~~~~
	*/
	
	// creates dice panel and sets coordinates of dots
	public Dice(Game curGameIn)
	{
		currentGame = curGameIn;
		rollNum = 1;
		setPreferredSize(new Dimension(200, 200));
		setBackground(Color.RED);
		
		addMouseListener(new DiceListener());
		
		dotX = new int[]{58, 58, 58, 95, 133, 133, 133};
		dotY = new int[]{58, 95, 133, 95, 58, 95, 133}; 
	}
	
	// calls methods to draw cube and dots on the cube
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		drawCube(g);
		
		if (rollNum == 1)
		{
			drawOne(g);
		}
		else if (rollNum == 2)
		{
			drawTwo(g);
		} 
		else if (rollNum == 3)
		{
			drawThree(g);
		}
		else if (rollNum == 4)
		{
			drawFour(g);
		}
		else if (rollNum == 5)
		{
			drawFive(g);
		}
		else
		{
			drawSix(g);
		}
	}
	
	// draws frame for cube
	public void drawCube(Graphics g)
	{
		g.setColor(Color.WHITE);
		g.fillRect(25, 25, 150, 150);
		
		g.setColor(Color.BLACK);
		g.drawRect(25, 25, 150, 150);
	}
	
	// draws one dot on cube
	public void drawOne(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(dotX[3], dotY[3], 10, 10);
	}
	
	// draws two dots on cube
	public void drawTwo(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(dotX[2], dotY[2], 10, 10);
		g.fillOval(dotX[4], dotY[4], 10, 10);
	}
	
	// draws three dots on cube
	public void drawThree(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(dotX[2], dotY[2], 10, 10);
		g.fillOval(dotX[3], dotY[3], 10, 10);
		g.fillOval(dotX[4], dotY[4], 10, 10);
	}
	
	// draws four dots on cube
	public void drawFour(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(dotX[0], dotY[0], 10, 10);
		g.fillOval(dotX[2], dotY[2], 10, 10);
		g.fillOval(dotX[4], dotY[4], 10, 10);
		g.fillOval(dotX[6], dotY[6], 10, 10);
	}

	// draws five dots on cube
	public void drawFive(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(dotX[0], dotY[0], 10, 10);
		g.fillOval(dotX[2], dotY[2], 10, 10);
		g.fillOval(dotX[3], dotY[3], 10, 10);
		g.fillOval(dotX[4], dotY[4], 10, 10);
		g.fillOval(dotX[6], dotY[6], 10, 10);
	}
	
	// draws six dots on cube
	public void drawSix(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillOval(dotX[0], dotY[0], 10, 10);
		g.fillOval(dotX[1], dotY[1], 10, 10);
		g.fillOval(dotX[2], dotY[2], 10, 10);
		g.fillOval(dotX[4], dotY[4], 10, 10);
		g.fillOval(dotX[5], dotY[5], 10, 10);
		g.fillOval(dotX[6], dotY[6], 10, 10);
	} 
	
	// does a roll of the dice
	public void roll()
	{
		rollNum = (int)(Math.random() * 6 + 1);
		repaint();
		
		currentGame.getDiceValue(rollNum);
	}
	
	// does a roll of the dice for the computer (does not send to getDiceValue)
	public int rollComputer()
	{
		rollNum = (int)(Math.random() * 6 + 1);
		repaint();

		return rollNum;
	}
	
	// MouseListener for teh class
	class DiceListener implements MouseListener
	{
		// rolls the dice if user clicks inside the cube
		public void mousePressed(MouseEvent evt)
		{
			int x = evt.getX();
			int y = evt.getY();
			
			if (25 <= x && x <= 175 && 25 <= y && y <= 175)
			{
				roll();
			}
		}
		
		// unused MouseListener methods
		public void mouseReleased(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseExited(MouseEvent evt) {}		
	}
}
