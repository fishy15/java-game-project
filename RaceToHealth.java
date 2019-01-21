// Aaryan Prakash
// 4/10/18
// RaceToNutrition.java
// add description of game

import javax.swing.JFrame;

public class RaceToHealth
{	
	// create instance of game and runs it
	public static void main(String[] args) 
	{
		RaceToHealth rth = new RaceToHealth();
		rth.createGame();
	}
	
	// creates frame for the game and adds GameHolder panel 
	public void createGame() 
	{
		JFrame frame = new JFrame("Race to Health");
		frame.setSize(1000, 1000);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		GameHolder gh = new GameHolder();
		frame.getContentPane().add(gh);
		
		frame.setVisible(true);
	}	
}
