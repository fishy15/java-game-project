// Aaryan Prakash
// 5/11/18
// RunOutQuestion.java
// This opens up when the user runs out of questions to answer so they
// can reset the game. 

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JRootPane;

import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RunOutQuestion
{
	// names of button to reset game or exit to normal screen
	private final String RESET_NAME = new String("Reset");
	private final String EXIT_NAME = new String("Exit Game");
	
	private Font sansSerif28; // font that is SansSerif, plain, and size 28	

	private GamePanel gamePanel; // game panel to rest game or go out
	
	private JFrame frame; // frame that pops up
	
	// passes in holder and 
	public RunOutQuestion(GamePanel gamePanelIn)
	{
		sansSerif28 = new Font("Arial", Font.PLAIN, 28);
		gamePanel = gamePanelIn;
	}
	
	// creates frame and shows to user
	public void createFrame()
	{
		frame = new JFrame();
		frame.setSize(600, 348);
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(300, 426);
		
		JPanel holds = new JPanel();
		holds.setLayout(new BorderLayout());
		
		// removes top 
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		JTextArea warning = new JTextArea("", 10, 10);
		warning.setText("                 You ran out questions!\n"
			+ "There are no more questions for you to answer! This "
			+ "means you will not be able to finish the game. Click "
			+ "\"Reset\" to reset the game and start again, or click "
			+ "\"Exit Game\" to return to the start screen");
		warning.setFont(sansSerif28);
		warning.setWrapStyleWord(true);
		warning.setLineWrap(true);
		warning.setEditable(false);
		holds.add(warning, BorderLayout.NORTH);
		
		JPanel bottom = new JPanel();
		JButton reset = new JButton(RESET_NAME);
		JButton exit = new JButton(EXIT_NAME);
		
		reset.addActionListener(new ResetButtonListener());
		exit.addActionListener(new ResetButtonListener());
		
		bottom.add(reset);
		bottom.add(exit);
		holds.add(bottom, BorderLayout.CENTER);
		
		frame.getContentPane().add(holds);
		frame.setVisible(true);
	}
	
	// listener for reset button
	class ResetButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String source = evt.getActionCommand();
			
			// resets the game to original state
			if (source.equals(RESET_NAME))
			{
				gamePanel.createNewGame();	
			}
			else
			{
				gamePanel.goToStart();
			}
			frame.setVisible(false);
		}
	}
	
	
}
