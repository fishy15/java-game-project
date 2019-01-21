// Aaryan Prakash 
// 5/7/18
// SliderQuestion.java
// This processes the slider question and shows the JFrame with the question.

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JSlider;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.FlowLayout;

public class SliderQuestion extends Question
{
	private int questionCode; // unique code of the question
	private String question; // user is asked this question
	private int[] sliderRange; // size 2 array that has min and max of slider
	private int ans; // the middle answer of the question
	private Tolerance[] tolerances; // stores tolerances possible user input
	
	private JPanel options; // JPanel that contains slider
	private JSlider slider; // slider to move up and down
	private JTextField valueShower; // shows value on slider
	
	// gives values to field variables
	public SliderQuestion(Game currentGame, int code, String questionIn,
		int[] sliderRangeIn, int answer, Tolerance[] tolerancesIn)
	{
		super(currentGame, code, questionIn);
		questionCode = code;
		question = questionIn;
		sliderRange = sliderRangeIn;
		ans = answer;
		tolerances = tolerancesIn;
	}

	// sets up options panel
	public JPanel createOptions()
	{
		options = new JPanel();
		options.setLayout(new FlowLayout(FlowLayout.CENTER, 1000, 50));
		
		slider = new JSlider(JSlider.HORIZONTAL, sliderRange[0], 
			sliderRange[1], (sliderRange[0] + sliderRange[1])/ 2);
		slider.addChangeListener(new SliderListener());
		options.add(slider);
		
		valueShower = new JTextField("Move It!", 5);
		valueShower.setFont(sansSerif28);
		valueShower.setEditable(false);
		options.add(valueShower);
		
		return options; 
	}
	
	// sets up respone page for user
	// sets userCorrect if user got right, alternate, or wrong
	public void setUpSecondPage()
	{
		int tolIndex = -1;
		
		for (int i = 0; i < tolerances.length && tolIndex == -1; i++)
		{
			if (tolerances[i].isInside(ans, slider.getValue()))
			{
				tolIndex = i;
			}
		}
		
		Tolerance tolChosen = tolerances[tolIndex];
		String[] boxes = getFieldText(tolChosen.getCorrect());
		secondPage.setText(boxes[0], tolChosen.getReason(), boxes[1]);
		
		userCorrect = tolChosen.getCorrect();
	}
	
	// listener for slider
	class SliderListener implements ChangeListener
	{
		// changes the value shown in valueShower
		public void stateChanged(ChangeEvent evt)
		{
			int val = slider.getValue();
			String valString = val + "";
			valueShower.setText(valString);
		}
	}
}
