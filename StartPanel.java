// Aaryan Prakash
// 4/10/18
// StartPanel.java 
// This is the starting panel of the game that the user will first see.
// It has options for starting the game, looking at instructions, and quitting the game. 

import javax.swing.JPanel;
import javax.swing.JButton; 
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.CardLayout;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StartPanel extends JPanel
{
	private GameHolder holder; // variable for panel that holds cards
	private CardLayout cards; // variable for CardLayout
	
	// names for the JButtons
	private final String START_NAME = new String("Start");
	private final String HELP_NAME = new String("Help");
	private final String QUIT_NAME = new String("Quit");
	
	private final Color SKYISH_BLUE = new Color(104, 197, 255); // color for background
	// creates different components and adds them to panel
	public StartPanel(GameHolder holderIn, CardLayout cardsIn)
	{
		holder = holderIn;
		cards = cardsIn;
		
		setBackground(SKYISH_BLUE);
		setLayout(new FlowLayout(FlowLayout.CENTER, 100000, 100));
		
		JPanel pushDown = new JPanel(); // pushes everything down to center
		pushDown.setPreferredSize(new Dimension(10, 100));
		pushDown.setBackground(SKYISH_BLUE);
		
		Font titleFont = new Font("SansSerif", Font.BOLD, 70);
		JTextArea title = new JTextArea("   The Race to Health!", 1, 13);
		title.setEditable(false);
		title.setFont(titleFont);
		title.setBackground(SKYISH_BLUE);
		
		Font buttonFont = new Font("SansSerif", Font.PLAIN, 20);
		JPanel buttonHolder = new JPanel();
		buttonHolder.setBackground(SKYISH_BLUE);
		buttonHolder.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));
		
		JButton start = new JButton(START_NAME);
		start.setFont(buttonFont);
		start.addActionListener(new ButtonOptions());
		JButton help = new JButton(HELP_NAME);
		help.setFont(buttonFont);
		help.addActionListener(new ButtonOptions());
		JButton quit = new JButton(QUIT_NAME);
		quit.setFont(buttonFont);
		quit.addActionListener(new ButtonOptions());
		
		buttonHolder.add(start);
		buttonHolder.add(help);
		buttonHolder.add(quit);
		
		add(pushDown);
		add(title);
		add(buttonHolder);		
	}
	
	// ActionListener for the buttons
	class ButtonOptions implements ActionListener
	{
		// goes to the panel that clicking on the panel is supposed to go to
		public void actionPerformed(ActionEvent evt)
		{
			String source = evt.getActionCommand();
			
			if (source.equals(START_NAME)) 
			{
				cards.show(holder, holder.getBoardName()); 
				holder.getGameInstance().reset();
			}
			else if (source.equals(HELP_NAME)) 
			{
				cards.show(holder, holder.getHelpName()); 
			}
			else if (source.equals(QUIT_NAME)) 
			{
				//make sure to close all output files as well
				System.exit(0);
			}
		}
	}
}
