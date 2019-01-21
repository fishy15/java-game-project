// Aaryan Prakash
// 5/4/18
// SecondPage.java
// This is the second page of the Question JPanel. 

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.BorderLayout;

import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SecondPage extends JPanel
{
	// name of close button
	private final String CLOSE_TEXT = new String("Close");
	
	private Font sansSerif28; // font that is SansSerif, plain, and size 28	
	
	private JTextField response; // tells user if correct or not
	private JTextArea reason; // tells why they got right/wrong
	private JTextField moves; // tells how far the user gets to move
	
	private Question question; // variable for the question
	
	// gives values to field and sets info about panel
	// adds panels to page as well
	public SecondPage(Question questionIn)
	{
		question = questionIn;
		
		sansSerif28 = new Font("Arial", Font.PLAIN, 28);
		 
		setLayout(new BorderLayout(0, 0));
		
		response = new JTextField();
		response.setFont(sansSerif28);
		response.setEditable(false);
		
		reason = new JTextArea(6, 10);
		reason.setFont(sansSerif28);
		reason.setEditable(false);
		reason.setWrapStyleWord(true);
		reason.setLineWrap(true);
		
		moves = new JTextField();
		moves.setFont(sansSerif28);
		moves.setEditable(false);
		
		JPanel closePanel = new JPanel();
		JButton close = new JButton(CLOSE_TEXT);
		close.addActionListener(new CloseListener());
		closePanel.add(close);
		
		JPanel top = new JPanel();
		top.setLayout(new BorderLayout(0, 0));
		top.add(response, BorderLayout.CENTER);
		top.add(reason, BorderLayout.SOUTH);
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout(0, 0));
		bottom.add(moves, BorderLayout.NORTH);
		bottom.add(closePanel, BorderLayout.SOUTH);
		
		add(top, BorderLayout.NORTH);
		add(bottom, BorderLayout.CENTER);
	}
	
	// sets the text of the three sections
	public void setText(String responseIn, String reasonIn, String movesIn)
	{
		response.setText(responseIn);
		reason.setText(reasonIn);
		moves.setText(movesIn);
	}
	
	// listener for close button
	class CloseListener implements ActionListener
	{
		// closes the frame
		public void actionPerformed(ActionEvent evt)
		{
			question.moveToken();
			question.getFrame().setVisible(false);
		}
	}
}
