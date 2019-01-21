// Aaryan Prakash
// 5/14/18
// SettingsPanel.java
// The user can adjust a slider in this panel to change the difficulty of the computer. 

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JSlider;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.FlowLayout;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SettingsPanel extends JPanel
{
	// different ints representing difficulty of game set 
	private final int EASY = 0; // 0.5 chance computer gets wrong
	private final int MEDIUM = 1; // 0.33 chance computer gets wrong
	private final int HARD = 2; // 0.25 chance computer gets wrong
	private final int IMPOSSIBLE = 3; // 0 chance computer gets wrong

	private Game currentGame; // variable for Game (can change difficulty in game)
	private JSlider difficultySlider; // variable for slider to get value

	private final Color SKY_BLUE = new Color(117, 245, 255); // sky blue color for panel
	
	// initializes variables and creates/adds Slider
	public SettingsPanel(Game gameIn)
	{
		currentGame = gameIn;
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 200, 25));
		setPreferredSize(new Dimension(200, 450));
		setBackground(SKY_BLUE);
				
		JTextField title = new JTextField(" Difficulty Settings", 10);
		title.setEditable(false);
		title.setBackground(SKY_BLUE);
		
		difficultySlider = new JSlider(JSlider.VERTICAL, 0, 3, 1);
		difficultySlider.setBackground(SKY_BLUE);
		difficultySlider.addChangeListener(new DifficultyListener());
		
		JTextArea difficultyInfo = new JTextArea("", 4, 15);
		difficultyInfo.setText("Difficulty Info:\n\n"
			+ "1 - Easy\n"
			+ "2 - Medium\n"
			+ "3 - Hard\n"
			+ "4 - Impossible\n");
		difficultyInfo.setEditable(false);
		difficultyInfo.setBackground(SKY_BLUE);
		
		add(title);
		add(difficultySlider);
		add(difficultyInfo);
	}
	
	// draws numbers on the side of the slider to tell what is the difficulty
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawString("4", 115, 100);
		g.drawString("3", 115, 150);
		g.drawString("2", 115, 200);
		g.drawString("1", 115, 250);
	}
	
	// listener for the slider
	class DifficultyListener implements ChangeListener
	{
		// checks difficulty level of slider and sends to Game
		public void stateChanged(ChangeEvent evt)
		{
			int difficulty = difficultySlider.getValue();
			
			if (difficulty == EASY)
			{
				currentGame.setCompWrongProb(0.5);
			}
			else if (difficulty == MEDIUM)
			{
				currentGame.setCompWrongProb(0.33);
			}
			else if (difficulty == HARD)
			{
				currentGame.setCompWrongProb(0.25);
			}
			else
			{
				currentGame.setCompWrongProb(0.0);
			}
		}
	}
}
