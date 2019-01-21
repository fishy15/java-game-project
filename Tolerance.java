// Aaryan Prakash 
// 5/7/18
// Tolerance.java
// This represents a tolerance used with a Slider question. 

public class Tolerance
{
	private final int CORRECT = 0; // represents a correct answer
	private final int ALTERNATE = 1; // represents alternate answer
	private final int WRONG = 2; // represents wrong answer
	
	private int range; // how much up and down you can go from answer
	private int correctness; // if answer is correct, alternate, or wrong 
	private String reason; // tells user why answer was correct/incorrect
	
	// gives values to field variables
	public Tolerance(int rangeIn, int correctIn, String reasonIn)
	{
		range = rangeIn;
		correctness = correctIn;
		reason = reasonIn;
	}
	
	// returns if given answer is inside tolerance or not
	public boolean isInside(int middle, int givenAns)
	{
		int lower = middle - range;
		int upper = middle + range;
		
		if (lower <= givenAns && givenAns <= upper)
		{
			return true;
		}
		
		return false;
	}
	
	// returns range of tolerance
	public int getRange()
	{
		return range;
	}
	
	// returns int if is correct, alternate, or wrong
	public int getCorrect()
	{
		return correctness;
	}
	
	// gives reason for why answer is correct or incorrect
	public String getReason()
	{
		return reason;
	}
}
