// Aaryan Prakash
// 5/4/18
// FirstPage.java
// This is the first page of the Question JPanel. 

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.awt.Font;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FirstPage extends JPanel
{
	private final int CORRECT = 0; // represents a correct answer
	private final int ALTERNATE = 1; // represents alternate answer
	private final int WRONG = 2; // represents wrong answer
	
	// name of submit button
	private final String SUBMIT_TEXT = new String("Submit");
	
	private Font sansSerif28; // font that is sansserif, plain, and size 28
	
	private Question question; // variable for question
	
	private final Color DARK_GREEN = new Color(2, 68, 0); // dark green color for background

	// adds parts of first page to panel
	public FirstPage(Question questionIn)
	{
		question = questionIn;
		
		sansSerif28 = new Font("Arial", Font.PLAIN, 28);
		
		setLayout(new BorderLayout(0, 0));
		
		JTextArea questionBox = new JTextArea(question.getQuestion(), 2, 10);
		questionBox.setFont(sansSerif28);
		questionBox.setEditable(false);
		questionBox.setWrapStyleWord(true);
		questionBox.setLineWrap(true);
		
		JPanel middle = new JPanel();
		middle.setLayout(new BorderLayout(0, 0));
		middle.add(new ImageViewer(), BorderLayout.EAST);
		middle.add(question.createOptions(), BorderLayout.CENTER);

		JPanel submitPanel = new JPanel(); 
		submitPanel.setLayout(new FlowLayout());
		JButton submit = new JButton(SUBMIT_TEXT);
		submit.addActionListener(new SubmitListener());
		submitPanel.add(submit);
		submitPanel.setBackground(DARK_GREEN);
		
		add(questionBox, BorderLayout.NORTH);
		add(submitPanel, BorderLayout.SOUTH);
		add(middle, BorderLayout.CENTER);
	}
	
	// listener for submit button
	class SubmitListener implements ActionListener
	{
		// tells Question frame to calculate moves and go to response page
		public void actionPerformed(ActionEvent evt)
		{
			question.setUpSecondPage();
			question.showNextPage();
		}
	}
}
