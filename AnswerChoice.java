// Aaryan Prakash
// 5/4/18
// AnswerChoice.java
// This is the variable that represents an answer choice for a radio button.

public class AnswerChoice
{	
	private final int CORRECT = 0; // represents a correct answer
	private final int ALTERNATE = 1; // represents alternate answer
	private final int WRONG = 2; // represents wrong answer
	
	private String name; // name of the choice
	private int correctness; // tells if ans is right/wrong
	private String reason; // reason that given answer is right/wrong
	
	// gives value to field variables
	public AnswerChoice(String nameIn, int correctIn, String reasonIn)
	{
		name = nameIn;
		correctness = correctIn;
		reason = reasonIn;
	}
	
	// returns name of answer choice
	public String getName()
	{
		return name;
	}
	
	// gets int that represents correctness
	public int getCorrect()
	{
		return correctness;
	}
	
	// returns reason
	public String getReason()
	{
		return reason;
	}
}