// Aaryan Prakash
// 5/3/18
// Question.java
// This is the super-class for all the types of questions that are
// in the program.

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JRootPane;

import java.awt.CardLayout;

import java.awt.Font;
import java.awt.Point;

public class Question
{
	protected final int CORRECT = 0; // represents a correct answer
	protected final int ALTERNATE = 1; // represents alternate answer
	protected final int WRONG = 2; // represents wrong answer
	
	protected Font sansSerif28; // sans serif font, plain, size 28 
	
	// ints to select which type of button it is
	// might not need
	protected int RADIO = 1;
	protected int SLIDER = 2;
	protected int CHECKBOX = 3;
	
	protected int questionCode; // unique number assigned to each question 
	protected String question; // string that stores the question
	protected int userCorrect; // tells if user was correct or not
	
	protected Game currentGame; // game class so can send moves to it
	protected JFrame frame; // variable frame
	protected JPanel holderPanel; //holds firstPage and secondPage
	protected CardLayout cards; // CardLayout used on holderPanel
	protected FirstPage firstPage; // first page of question frame
	protected SecondPage secondPage; // second page of question frame
	
	// initializes variables
	public Question(Game currentGameIn, int code, String questionIn)
	{
		currentGame = currentGameIn;
		sansSerif28 = new Font("Arial", Font.PLAIN, 28);
		questionCode = code;
		question = questionIn;
	}
	
	// creates and displays a frame with the question
	public void createFrame()
	{
		frame = new JFrame("Question " + questionCode + ":"); // title is removed (see below)
		frame.setSize(600, 348);
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation(300, 426);
		
		holderPanel = new JPanel();
		cards = new CardLayout();
		holderPanel.setLayout(cards);
		
		firstPage = new FirstPage(this);
		secondPage = new SecondPage(this);
		
		holderPanel.add(firstPage, "first"); 
		holderPanel.add(secondPage, "second");
		
		frame.getContentPane().add(holderPanel);
		
		// Got the below code from: 
		//https://stackoverflow.com/questions/276254/how-to-disable-or-hide-the-close-x-button-on-a-jframe 
		// it removes the top bar so user can't close it by themselves
		// they are forced to answer the question
		frame.setUndecorated(true);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
		
		frame.setVisible(true);

	}
	
	// sets up respone page for user
	// sets userCorrect if user got right, alternate, or wrong
	public void setUpSecondPage()
	{
		// is implemented by subclasses
		System.out.println("if this prints setUpSecondPage this hasn't been overridden yet");
	}
	
	// creates responses in response and moves JTextField and returns results
	// in array
	public String[] getFieldText(int correctness)
	{
		String response = new String("");
		String moves = new String("");
		
		// choice is correct
		if (correctness == CORRECT)
		{
			response = new String("                              Correct!");
			moves = new String("You get to move your full roll forward!");
		}
		// choice is almost correct
		else if (correctness == ALTERNATE)
		{
			response = new String("                                Close!");
			moves = new String("You lose 1 space in this turn.");
		}
		// choice is wrong
		else
		{
			response = new String("                                 Oops!");
			moves = new String("You lost your turn! Better luck next time.");
		}
		
		String[] toReturn = {response, moves};
		return toReturn;
	}
	
	// this moves the token forward when user closes question frame
	public void moveToken()
	{
		currentGame.moveBlueForward(userCorrect);
	}
	
	// shows response page of question frame
	public void showNextPage()
	{
		cards.next(holderPanel);
	}
	
	// creates the options JPanel
	public JPanel createOptions()
	{
		// is implemented by subclasses
		System.out.println("if this prints createOptions this hasn't been overridden yet");
		return null;
	}
	
	// returns question text
	public String getQuestion()
	{
		return question;
	}
	
	// returns the unique code of the question
	public int getCode()
	{
		return questionCode;
	}
	
	// returns frame
	public JFrame getFrame()
	{
		return frame;
	}
}
