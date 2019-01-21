// Aaryan Prakash
// 4/13/18
// GamePanel.java
// This contains the user interface to play the game.
// This holds the left and right sides of the game interface.

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;

import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel
{
	private GameHolder holder; // variable for panel that holds cards
	private CardLayout cards; // variable for CardLayout

	private Game currentGame; // represents current game

	private ImageViewer imageOptions; // for viewing images to help user
	private Dice diceViewer; // variable for dice
	private SettingsPanel settings; // variable for settings panel

	// name for the back button
	private final String BACK_NAME = new String("Back");

	// adds back button, left, and right sides to the panel
	public GamePanel(GameHolder holderIn, CardLayout cardsIn)
	{
		holder = holderIn;
		cards = cardsIn;

		setLayout(new BorderLayout(0, 0));
		JButton back = new JButton(BACK_NAME);
		back.addActionListener(new BackHandler());
		add(back, BorderLayout.NORTH);

		currentGame = new Game(this);
		diceViewer = new Dice(currentGame);
		imageOptions = new ImageViewer();
		settings = new SettingsPanel(currentGame);

		RightSide right = new RightSide();
		add(right, BorderLayout.EAST);

		LeftSide left = new LeftSide();
		add(left, BorderLayout.CENTER);

		addMouseListener(new RollDiceListener());
		addKeyListener(new RollDiceListener());
	}
	
	// creates a new game to play
	public void createNewGame()
	{
		currentGame.reset();
	}

	// returns instance of Game
	public Game getGame()
	{
		return currentGame;
	}
	
	// goes back to start page
	public void goToStart()
	{
		cards.show(holder, holder.getStartName());	
	}
	
	// returns dice panel instance
	public Dice getDice()
	{
		return diceViewer;
	}
	
	// ActionListener for back button, goes to start page if clicked
	class BackHandler implements ActionListener
	{
		// goes to start page if back button is clicked
		public void actionPerformed(ActionEvent evt)
		{
			WarnBackFrame wbf = new WarnBackFrame(holder, cards);
			wbf.createFrame();
		}
	}

	// class for the right part of the JPanel
	class RightSide extends JPanel
	{
		// adds image viewer panel and dice roller to panel
		public RightSide()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
			setPreferredSize(new Dimension(200, 1000));
			setBackground(Color.WHITE);

			add(imageOptions);
			add(diceViewer);
			add(settings);
		}
	}

	// class for the left part of the JPanel
	class LeftSide extends JPanel
	{
		// adds game board and alert box to the panel
		public LeftSide()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

			add(currentGame.getBoard());
			add(currentGame.getAlertBox());
		}
	}

	// MouseListener and KeyListener for this class
	class RollDiceListener implements MouseListener, KeyListener
	{
		// gets focus into current panel
		public void mousePressed(MouseEvent evt)
		{
			requestFocusInWindow();
		}

		// unused MouseListener methods
		public void mouseClicked(MouseEvent evt) {}
		public void mouseReleased(MouseEvent evt) {}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseExited(MouseEvent evt) {}

		// rolls the dice if user presses up
		public void keyPressed(KeyEvent evt)
		{
			int keyCode = evt.getKeyCode();

			if (keyCode == KeyEvent.VK_UP)
			{
				diceViewer.roll();
			}
			else if (keyCode == KeyEvent.VK_SPACE)
			{
				currentGame.reset();
			}
		}

		// unused KeyListener methods
		public void keyTyped(KeyEvent evt) {}
		public void keyReleased(KeyEvent evt) {}
	}
}
