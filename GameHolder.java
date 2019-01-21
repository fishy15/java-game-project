// Aaryan Prakash
// 4/10/18
// GameHolder.java
// This holds all the panels of the game.

import javax.swing.JPanel;

import java.awt.CardLayout;

public class GameHolder extends JPanel
{
	private CardLayout cards; // var for CardLayout so can be passed down
	private GameHolder holder; // var for panel so can be passed down
	
	// variables for different cards in panel
	private StartPanel startPage;
	private HelpPanel helpPage;
	private GamePanel boardPage;
	
	// names for different panels
	// keeps consistency between different panels so is easier to change
	private final String START_NAME = new String("start"); 
	private final String HELP_NAME = new String("help");
	private final String BOARD_NAME = new String("board");
	
	// sets up CardLayout and adds panels to the layout
	public GameHolder()
	{
		holder = this;
			
		cards = new CardLayout();
		setLayout(cards);	
		
		startPage = new StartPanel(holder, cards);
		helpPage = new HelpPanel(holder, cards);
		boardPage = new GamePanel(holder, cards);

		add(startPage, START_NAME);
		add(helpPage, HELP_NAME);
		add(boardPage, BOARD_NAME);		
	}
	
	// returns name of the start panel
	// does this so other panels can use CardLayout and go 
	// to the correct panel
	public String getStartName()
	{
		return START_NAME;
	}
	
	// returns name of the instructions panel
	public String getHelpName()
	{
		return HELP_NAME;
	}
	
	// returns name of the game board panel
	public String getBoardName()
	{
		return BOARD_NAME;
	}
	
	// gets instance of Game from the JPanel with the game
	public Game getGameInstance()
	{
		return boardPage.getGame();
	}
}
