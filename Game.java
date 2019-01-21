// Aaryan Prakash
// 10/24/18
// Game.java
// Holds the game state and calls methods for various components
// to change the UI. 

import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class Game 
{
	// used to tell which token to move (name changed for ease in reading)
	private boolean BLUE_TOKEN = true;
	private boolean RED_TOKEN = false;
	
	private final int CORRECT = 0; // represents a correct answer
	private final int ALTERNATE = 1; // represents alternate answer
	private final int WRONG = 2; // represents wrong answer
	
	private Board board; // current board that is used
	private Alerts alertBox; // alert box that is being used
	
	private boolean gameEnded; // tells if game has ended
	private int redPos; // red player's position
	private int bluePos; // blue player's position
	private int roll; // the roll the player did
	private Timer redTurnWait; // waits for 1/2 second and does red turn
	
	private String questionFileName; // name of the file with questions
	private Scanner input; // scanner that scans through
	private int numQuestions; // stores number of questions
	private Question[] questions; // stores question to show to user
	private boolean[] questionsDone; // stores which questions have been asked/answered already

	// used to go back out of the panel or create new game
	private GamePanel gamePanel; // variable for the GamePanel this is controlling
	
	private double compWrongProb; // probability computer gets a question wrong
	// easy = 0.5, medium = 0.33, hard = 0.25, impossible = 0
	
	// sets intial state of game and creates components
	public Game(GamePanel gamePanelIn)
	{
		gamePanel = gamePanelIn;
		
		roll = 0;
		redPos = 1;
		bluePos = 1;
		gameEnded = false;
		
		board = new Board();
		alertBox = new Alerts();
		
		questionFileName = new String("questions/questions.txt");
		input = null;		
		
		openQuestionFile();
		openQuestions();
		
		redTurnWait = new Timer(1250, new TimerListener()); // adjust number for time
		
		compWrongProb = 0.33; // set default to medium difficulty
	}
	
	// receives roll from dice, tells token to move forward, and adds message to alertBox
	public void getDiceValue(int rollIn)
	{
		roll = rollIn;
		
		if (!gameEnded) 
		{
			// creates the alert to send to alertBox
			String alert = new String();
			alert = "You rolled a " + roll;
			
			if (roll == 6)
			{
				alert += "!";
				roll += (int)(Math.random() * 6 + 1); // adds an extra move for player
			}
			else
			{
				alert += ".";
			}
			
			alertBox.addAlert(alert);
		}
		
		openRandQuestion();
	}
	
	// this is called by the question class to move the blue token forward
	public void moveBlueForward(int correctness)
	{
		String alert = new String();
		
		if (correctness == CORRECT)
		{
			board.moveForward(BLUE_TOKEN, bluePos, roll);
			bluePos = Math.min(bluePos + roll, 100);
			alertBox.addAlert("You moved " + roll + " forward!");
		}
		else if (correctness == ALTERNATE)
		{
			board.moveForward(BLUE_TOKEN, bluePos, roll - 1);
			bluePos = Math.min(bluePos + roll - 1, 100);
			alertBox.addAlert("You moved " + (roll - 1) + " forward.");
		}
		else
		{
			alertBox.addAlert("You lost your turn.");
		}
		
		// adds extra move if player rolled a 6
		if (roll == 5 || roll == 6)
		{
		System.out.println("hi");
			int extraMove = (int)(Math.random() * 6 + 1);
			alertBox.addAlert("Because you rolled a 5 or 6, you get to move " + extraMove + " extra.");
			board.moveForward(BLUE_TOKEN, bluePos, extraMove);
			bluePos = Math.min(bluePos + extraMove, 100);
		}
		
		checkWin();
		redTurnWait.start();
	}
	
	// this does a turn for the red player
	public void redTurn()
	{
		redTurnWait.stop();
		int redRoll = gamePanel.getDice().rollComputer();
		
		// creates the alert to send to alertBox
		String alert = "The computer rolled a " + redRoll;
		
		if (redRoll == 6)
		{
			alert += "!";
		}
		else
		{
			alert += ".";
		}
		
		alertBox.addAlert(alert);	
		
		double mistakeProb = Math.random();
		if (mistakeProb > compWrongProb)
		{
			board.moveForward(RED_TOKEN, redPos, redRoll);
			redPos = Math.min(redPos + redRoll, 100);
			alert = new String("The computer moves " + redRoll + " forward.");
		}
		else
		{
			alert = new String("The computer lost their turn");
		}
		
		alertBox.addAlert(alert);
		checkWin();	
	}
	
	// checks if anyone has one yet
	public void checkWin()
	{
		if (bluePos == 100) 
		{
			alertBox.addAlert("You won the game!");
			gameEnded = true;
		}
		else if (redPos == 100)
		{
			alertBox.addAlert("The computer won the game!");
			gameEnded = true;
		}		
	}
		
	// opens up a random, unanswered question for the user to answer
	public void openRandQuestion()
	{
		int questionChecked = 0;
		
		while (questionChecked != -1 && questionChecked < numQuestions)
		{
			int pos = (int)(Math.random() * numQuestions);
			if (!questionsDone[pos])
			{
				questions[pos].createFrame();
				questionsDone[pos] = true;
				questionChecked = -2; // makes it so ++ makes it = -1
			}
			
			questionChecked++;
		}

		if (questionChecked == numQuestions)
		{
			RunOutQuestion roq = new RunOutQuestion(gamePanel);
			roq.createFrame();
		}
	}
	
	// opens up questions and adds to questions array
	public void openQuestions()
	{
		numQuestions = input.nextInt();
		questions = new Question[numQuestions];
		questionsDone = new boolean[numQuestions];
		
		int questionsAdded = 0;
		while (questionsAdded < numQuestions)
		{
			int code = input.nextInt();
			input.nextLine(); // flushes to next line
			String question = input.nextLine();
			String type = input.next();
			if (type.equals("radio"))
			{
				questions[questionsAdded] = openRadioQuestion(code, question);
			} 
			else
			{
				questions[questionsAdded] = openSliderQuestion(code, question);
			}
			questionsAdded++;
		}
	}
	
	public Question openRadioQuestion(int code, String question)
	{
		int numChoices = input.nextInt();
		input.nextLine(); // flushes to next line
		int choicesAdded = 0;
		AnswerChoice[] choices = new AnswerChoice[numChoices];
		input.next(); // skips the word that identifies it is correct
		int numCorrect = input.nextInt();
		input.nextLine(); // flushes to next line
		while (numCorrect > 0)
		{
			String name = input.nextLine();
			String reason = input.nextLine();
			choices[choicesAdded] = new AnswerChoice(name, CORRECT, reason);
			choicesAdded++;
			numCorrect--;
		}
		
		input.next(); // skips alternate identifier
		int numAlt = input.nextInt();
		input.nextLine(); // flushes to next line
		while (numAlt > 0)
		{
			String name = input.nextLine();
			String reason = input.nextLine();
			choices[choicesAdded] = new AnswerChoice(name, ALTERNATE, reason);
			choicesAdded++;
			numAlt--;
		}
		
		input.next(); // skips wrong identifier
		int numWrong = input.nextInt();
		input.nextLine(); // flushes to next line
		while (numWrong > 0)
		{
			String name = input.nextLine();
			String reason = input.nextLine();
			choices[choicesAdded] = new AnswerChoice(name, WRONG, reason);
			choicesAdded++;
			numWrong--;
		}
		
		Question theQuestion = new RadioQuestion(this, code, question, choices);
		return theQuestion;
	}
	
	public Question openSliderQuestion(int code, String question)
	{
		int[] minMax = new int[2];
		minMax[0] = input.nextInt();
		minMax[1] = input.nextInt();
		
		int ans = input.nextInt();
		input.nextLine(); // flushes to next line
		Tolerance[] tolerances = new Tolerance[3];
		for (int i = 0; i < 3; i++)
		{
			input.nextLine(); // skips over correct identifier
			int range = input.nextInt();
			input.nextLine(); // flushes to next line
			String reason = input.nextLine();
			Tolerance tol = new Tolerance(range, i, reason);
			tolerances[i] = tol;
		}
		
		Question theQuestion = new SliderQuestion(this, code, question, 
			minMax, ans, tolerances);
			
		return theQuestion;
	}
	
	// opens scanner to read question file
	public void openQuestionFile()
	{
		File outFile = new File(questionFileName);
		try
		{
			input = new Scanner(outFile);
		} 
		catch (FileNotFoundException e)
		{
			System.err.printf("Could not open %s.\n", questionFileName);
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	// returns Board instance
	public Board getBoard()
	{
		return board;
	}
	
	// returns Alerts instance
	public Alerts getAlertBox()
	{
		return alertBox;
	}
	
	// resets alertBox and board to original state (empty, at pos 1)
	public void reset()
	{
		board.reset(bluePos, redPos);
		bluePos = 1;
		redPos = 1;
		
		gameEnded = false;
		
		questionsDone = new boolean[numQuestions];
		
		alertBox.resetAlerts();
	}
	
	// sets the value of compWrongProb 
	public void setCompWrongProb(double prob)
	{
		compWrongProb = prob;
	}

	// class for timer
	class TimerListener implements ActionListener
	{
		// does a red move after 1 second
		public void actionPerformed(ActionEvent evt)
		{
			redTurn();
		}
	}
}
