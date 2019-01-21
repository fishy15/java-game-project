// Aaryan Prakash
// 4/27/18
// Alerts.java
// This is the JTextArea for telling the user what is going on in the game.

import javax.swing.JTextArea;

import java.awt.Font;

public class Alerts extends JTextArea
{
	private Font sansSerif30; // Arial font, size 28 
	private int numAlerts; // tells number of alerts created
	private String[] alerts; // stores 3 previous alerts
	
	// creates JTextArea
	public Alerts()	
	{
		super(" Alerts:\n", 12, 35);
		alerts = new String[]{"", "", ""};
		numAlerts = 0;
		sansSerif30 = new Font("Arial", Font.PLAIN, 28);
		setFont(sansSerif30);
		setEditable(false);
	}
	
	// adds alert to alerts array
	public void addAlert(String newAlert)
	{
		numAlerts++;
		if (numAlerts == 1)
		{
			alerts[0] = newAlert;
		}
		else if (numAlerts == 2)
		{
			alerts[1] = newAlert;
		}
		else if (numAlerts == 3)
		{
			alerts[2] = newAlert;
		}
		else
		{
			alerts[0] = alerts[1];
			alerts[1] = alerts[2];
			alerts[2] = newAlert;
		}
		
		showAlerts();
	}
	
	// shows the new alerts queue in the JTextArea 
	public void showAlerts()
	{
		String text = " Alerts:\n\n " + alerts[0] + "\n " + alerts[1]
			+ "\n " + alerts[2];
		setText(text);
	}

	// resets alerts to an empty state
	public void resetAlerts()
	{
		alerts[0] = "";
		alerts[1] = "";
		alerts[2] = "";
		showAlerts();
	}
}
