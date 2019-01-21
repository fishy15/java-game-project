// Aaryan Prakash
// 5/3/18
// RadioQuestion.java
// This processes the radio question and shows the JFrame with the question.

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import java.awt.FlowLayout;

public class RadioQuestion extends Question
{
	private AnswerChoice[] answerChoices; // array of answer choices
	private JRadioButton[] buttons; // array of buttons user can choose
	private boolean[] buttonAdded; // tells if button has been added
	private JPanel options; // JPanel with Radio Buttons
	
	// gives values to field variables
	public RadioQuestion(Game currentGame, int code, String questionIn, AnswerChoice[] ac)
	{
		super(currentGame, code, questionIn);
		answerChoices = ac;
		buttons = new JRadioButton[ac.length];
		buttonAdded = new boolean[ac.length];
	}
	
	// sets up options panel
	public JPanel createOptions()
	{
		// resets array so it can be rearranged a second time
		buttonAdded = new boolean[answerChoices.length]; 
		
		options = new JPanel();
		options.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
		ButtonGroup optionsbg = new ButtonGroup();
		
		for (int i = 0; i < answerChoices.length; i++)
		{
			buttons[i] = new JRadioButton(answerChoices[i].getName());
			optionsbg.add(buttons[i]);
		}

		randomAdd();
		
		return options;
	}
	
	// this adds the radio buttons in random so the user doesn't know
	// the correct answer
	public void randomAdd()
	{
		int numAdded = 0;
		while (numAdded < buttons.length)
		{
			int randPos;
			do 
			{
				randPos = (int)(Math.random() * buttons.length);
			} 
			while (buttonAdded[randPos]);
			
			options.add(buttons[randPos]);
			
			// checks if it is the first button being added
			if (numAdded == 0)
			{
				buttons[randPos].setSelected(true);
			}
			
			buttonAdded[randPos] = true;
			numAdded++;
		}
	}
	
	// sets up respone page for user
	// sets userCorrect if user got correct, alternate, or wrong
	public void setUpSecondPage()
	{
		// finds index of button that is selected
		int index = -1;
		for (int i = 0; i < buttons.length; i++)
		{
			if (buttons[i].isSelected())
			{
				index = i;
			}
		}
		
		AnswerChoice choice = answerChoices[index];
		String[] boxes = getFieldText(choice.getCorrect());
		secondPage.setText(boxes[0], choice.getReason(), boxes[1]);
		
		userCorrect = choice.getCorrect();
	}
}
