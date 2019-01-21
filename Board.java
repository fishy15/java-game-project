// Aaryan Prakash
// 10/24/18
// Board.java
// Holds all the tiles that make up the game board. Changes the tiles 
// when a move made. 

import javax.swing.JPanel;
import javax.swing.JButton; // remove later

import java.awt.GridLayout;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class Board extends JPanel 
{
	// used to tell which token to move (name changed for ease in reading)
	private boolean BLUE_TOKEN = true; 
	private boolean RED_TOKEN = false;
	
	private Tile[] boardTiles; // panels for each square of the game board
	// indexed from 0-100, 0th tile is ignored
	
	// sets values of panel and adds game board tiles
	public Board()
	{
		setLayout(new GridLayout(10, 10, 0, 0));
		setPreferredSize(new Dimension(800, 800));
		
		boardTiles = new Tile[101]; 
		
		// loops over each row of board
		for (int i = 9; i >= 0; i--)
		{
			if (i % 2 == 0)
			{
				for (int j = 1; j <= 10; j++)
				{
					addTile(10 * i + j);
				}
			}
			else
			{
				for (int j = 10; j > 0; j--)
				{
					addTile(10 * i + j);
				}
			}
		}
	}
	
	// method to create tile, add to array, and add to panel
	public void addTile(int pos)
	{
		Tile tileToAdd = new Tile(pos);
		boardTiles[pos] = tileToAdd;
		add(tileToAdd);
	}
	
	// moves the token forward 
	public void moveForward(boolean whichToken, int originalPos, int stepsForward)
	{
		int newPos = Math.min(originalPos + stepsForward, 100);
		
		if (whichToken == BLUE_TOKEN)
		{	
			boardTiles[originalPos].blueTokenOn(false);
			boardTiles[originalPos].repaint();
			boardTiles[newPos].blueTokenOn(true);
			boardTiles[newPos].repaint();
		}
		else
		{
			boardTiles[originalPos].redTokenOn(false);
			boardTiles[originalPos].repaint();
			boardTiles[newPos].redTokenOn(true);
			boardTiles[newPos].repaint();			
		}
	}
	
	// resets board to initial condition
	public void reset(int curBluePos, int curRedPos)
	{
		boardTiles[curBluePos].blueTokenOn(false);
		boardTiles[curBluePos].repaint();
		
		boardTiles[curRedPos].redTokenOn(false);
		boardTiles[curRedPos].repaint();
		
		boardTiles[1].blueTokenOn(true);
		boardTiles[1].redTokenOn(true);
		boardTiles[1].repaint();
	}
	
	// JPanel class for each tile of the gameboard
	class Tile extends JPanel
	{
		private int tileNum; // number shown on tile
		private boolean hasBlue; // tells if blue token is on it
		private boolean hasRed; // tells if red token is on it
		
		// sets number and color
		// if it is the first tile, tells blue and red tokens are on it
		public Tile(int num)
		{
			tileNum = num;
			
			if (num == 1)
			{
				hasBlue = true;
				hasRed = true;
			}
			else
			{
				hasBlue = false;
				hasRed = false;
			}
			
			// the below statement checks if it is a odd or even diagonal
			if (num % 2 == 0) 
			{
				setBackground(Color.WHITE);
			}
			else 
			{
				setBackground(Color.BLACK);
			}
		}
		
		// draws number and tokens
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			if (tileNum % 2 == 0) 
			{
				g.setColor(Color.BLACK);
			}
			else 
			{
				g.setColor(Color.WHITE);
			}
			
			g.drawString(tileNum + "", 10, 10);
			
			if (hasBlue)
			{
				drawBlueToken(g);
			}
			
			if (hasRed)
			{
				drawRedToken(g);
			}
		}
	
		// draws blue token on the board
		public void drawBlueToken(Graphics g)
		{
			g.setColor(Color.BLUE);
			g.drawOval(40, 10, 35, 35);
			g.fillOval(40, 10, 35, 35);
		}
		
		// draws red token on the board
		public void drawRedToken(Graphics g)
		{
			g.setColor(Color.RED);
			g.drawOval(10, 40, 35, 35);
			g.fillOval(10, 40, 35, 35);
		}
		
		// changes if blue token is on tile or not
		public void blueTokenOn(boolean isOn)
		{
			hasBlue = isOn;
		}
		
		// changes if red token is on tile or not
		public void redTokenOn(boolean isOn)
		{
			hasRed = isOn;
		}
	}
}
