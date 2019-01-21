// Aaryan Prakash
// 5/10/18
// WarnBackFrame.java
// This opens up when the user clicks out of the GamePanel panel so that
// the user knows this will reset the game.

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

public class WarnBackFrame
{
	// names of buttons to exit game or close warning
	private final String EXIT_NAME = new String("Close Game");
	private final String CANCEL_NAME = new String("Back to Game"); 
	
	private Font sansSerif28; // font that is SansSerif, plain, and size 28	

	private GameHolder holder; // variable for panel that holds cards
	private CardLayout cards; // variable for CardLayout
	
	private JFrame frame; // frame that pops up
	
	// passes in holder
	public WarnBackFrame(GameHolder holderIn, CardLayout cardsIn)
	{
		sansSerif28 = new Font("Arial", Font.PLAIN, 28);
		holder = holderIn;
		cards = cardsIn;
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
		warning.setText("                              Warning!\n"
			+ "If you close the game, the game will reset! Are you sure"
			+ " that you want to continue?");
		warning.setFont(sansSerif28);
		warning.setWrapStyleWord(true);
		warning.setLineWrap(true);
		warning.setEditable(false);
		holds.add(warning, BorderLayout.NORTH);
		
		JPanel bottom = new JPanel();
		JButton exit = new JButton(EXIT_NAME);
		JButton cancel = new JButton(CANCEL_NAME);
		
		exit.addActionListener(new ButtonListener());
		cancel.addActionListener(new ButtonListener());
		
		bottom.add(exit);
		bottom.add(cancel);
		holds.add(bottom, BorderLayout.CENTER);
		
		frame.getContentPane().add(holds);
		frame.setVisible(true);
	}
	
	// listener for buttons
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent evt)
		{
			String source = evt.getActionCommand();
			
			// goes back to normal start screen
			if (source.equals(EXIT_NAME))
			{
				cards.show(holder, holder.getStartName());	
			}
			frame.setVisible(false);
		}
	}
	
	
}
