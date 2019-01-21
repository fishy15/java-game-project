// Aaryan Prakash
// 4/12/18
// HelpPanel.java
// This displays the instructions to ther user. 

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout; 

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

public class HelpPanel extends JPanel
{
	private String[] instructions; // holds text for the instructions
		
	private GameHolder holder; // variable for panel that holds cards
	private CardLayout cards; // variable for CardLayout
	
	// 3 different components of the panel
	private PageNumViewer pnv; 
	private JTextArea instructionsText;
	private BottomButtonHolder bbh;
	
	// names of the 3 buttons
	private final String NEXT_NAME = new String("Next");
	private final String PREV_NAME = new String("Previous");
	private final String EXIT_NAME = new String("Back");
	
	// stores the current page of the instructions
	private int currentPage;
	
	private String helpFileName; // name of file containing instructions
	private Scanner input; // reads helpFileName to get 
	
	// sets layout and adds components 
	public HelpPanel(GameHolder holderIn, CardLayout cardsIn)
	{
		holder = holderIn;
		cards = cardsIn;
		
		setLayout(new BorderLayout(0, 0));
		
		pnv = new PageNumViewer();
		add(pnv, BorderLayout.WEST);
		
		instructions = new String[4];
		helpFileName = new String("help.txt");
		input = null;
		openHelp();
		
		Font textFont = new Font("SansSerif", Font.PLAIN, 45);
		instructionsText = new JTextArea(instructions[1], 20, 20);
		instructionsText.setWrapStyleWord(true);
		instructionsText.setLineWrap(true);
		//instructionsText.setEditable(false);
		instructionsText.setFont(textFont);
		add(instructionsText, BorderLayout.CENTER);
		
		bbh = new BottomButtonHolder();
		add(bbh, BorderLayout.SOUTH);
		
		currentPage = 1;
	}
	
	// bar on the left that shows which page of instructions
	// user is currently on
	class PageNumViewer extends JPanel 
	{
		// sets bg color and size
		public PageNumViewer()
		{
			setBackground(Color.CYAN);
			setPreferredSize(new Dimension(100, 900));
		}
		
		// paints the numbers on the panel
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			
			if (currentPage == 1)
			{
				drawPage1Viewer(g, true);
				drawPage2Viewer(g, false);
				drawPage3Viewer(g, false);
			} 
			else if (currentPage == 2)
			{
				drawPage1Viewer(g, false);
				drawPage2Viewer(g, true);
				drawPage3Viewer(g, false);
			}
			else if (currentPage == 3)
			{
				drawPage1Viewer(g, false);
				drawPage2Viewer(g, false);
				drawPage3Viewer(g, true);
			}
		}
		
		// draws specifically the first number box
		// if user is on first page then bg of box is yellow
		public void drawPage1Viewer(Graphics g, boolean isOn)
		{
			if (isOn) 
			{
				g.setColor(Color.YELLOW);
				g.fillRect(25, 187, 50, 50);	
			}
			
			g.setColor(Color.BLACK);
			g.drawRect(25, 187, 50, 50);
			g.drawString("1", 37, 200);
		}
		
		// draws specifically the second number box
		// if user is on second page then bg of box is yellow
		public void drawPage2Viewer(Graphics g, boolean isOn)
		{
			if (isOn) 
			{
				g.setColor(Color.YELLOW);
				g.fillRect(25, 424, 50, 50);	
			}
			
			g.setColor(Color.BLACK);
			g.drawRect(25, 424, 50, 50);
			g.drawString("2", 37, 437);
		}
		
		// draws specifically the third number box
		// if user is on third page then bg of box is yellow
		public void drawPage3Viewer(Graphics g, boolean isOn)
		{
			if (isOn) 
			{
				g.setColor(Color.YELLOW);
				g.fillRect(25, 661, 50, 50);	
			}
			
			g.setColor(Color.BLACK);
			g.drawRect(25, 661, 50, 50);
			g.drawString("3", 37, 674);
		}
	}
	
	// holds the buttons at the bottom to move between instruction
	// pages and go back to instructions
	class BottomButtonHolder extends JPanel
	{
		// creates buttons and adds to panel
		public BottomButtonHolder()
		{
			setLayout(new FlowLayout(FlowLayout.CENTER, 100, 35));
			setPreferredSize(new Dimension(1000, 100));
			
			JButton next = new JButton(NEXT_NAME);
			JButton prev = new JButton(PREV_NAME);
			JButton exit = new JButton(EXIT_NAME);
			
			next.addActionListener(new BottomButtonHandler());
			prev.addActionListener(new BottomButtonHandler());
			exit.addActionListener(new BottomButtonHandler());
			
			add(prev);
			add(exit);
			add(next);
		}
	}
	
	// ActionListener for buttons
	class BottomButtonHandler implements ActionListener
	{
		// if next or previous is pressed, goes to corresponding 
		// instructions page 
		// if exit is pressed, goes back to start page
		public void actionPerformed(ActionEvent evt)
		{
			String source = evt.getActionCommand();
			
			if (source.equals(NEXT_NAME))
			{
				currentPage = Math.min(currentPage + 1, 3);
				changePage();
			} 
			else if (source.equals(PREV_NAME))
			{
				currentPage = Math.max(currentPage - 1, 1);
				changePage();
			}
			else
			{
				cards.show(holder, holder.getStartName());
			}
		}
	}
	
	// changes text on instructions JTextArea and 
	// changes page shown on the page viewer panel 
	public void changePage()
	{
		instructionsText.setText(instructions[currentPage]);
		pnv.repaint();
	}
	
	// opens scanner to read question file
	// then reads in Strings from file
	public void openHelp()
	{
		File inFile = new File(helpFileName);
		try
		{
			input = new Scanner(inFile);
		} 
		catch (FileNotFoundException e)
		{
			System.err.printf("Could not open %s.\n", helpFileName);
			e.printStackTrace();
			System.exit(1);
		}
		
		instructions[1] = input.nextLine();
		instructions[2] = input.nextLine();
		instructions[3] = input.nextLine();		
	}
}
