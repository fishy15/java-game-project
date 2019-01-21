// Aaryan Prakash
// 5/1/18
// ImagePopUp.java
// This opens a pop-up of the image when the user clicks on the image
// in the ImageViewer. 

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.FlowLayout;
import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ImagePopUp
{
	// string that holds the name of the button
	private final String CLOSE_NAME = new String("Close");

	private JFrame frame; // variable for the frame created
	private Image img; // image that is shown
	private String imgName; // name of the image that is shown 
	                        // also name of the frame
	private int width; // width of the image
	private int height; // height of the image
	
	private final Color DARK_GREEN = new Color(2, 68, 0); // dark green color for background

	// gets the image and name and calculates width and height
	public ImagePopUp(Image imgIn, String imgNameIn)
	{
		img = imgIn;
		imgName = imgNameIn;
		
		width = img.getWidth(null);
		height = img.getHeight(null);
	}
	
	// creates frame, adds holder along with JPanels to it
	public void createFrame()
	{
		frame = new JFrame(imgName);
		frame.setSize(width, height + 40);
		frame.setDefaultCloseOperation(frame.HIDE_ON_CLOSE);

		
		JPanel holder = new JPanel();
		holder.setLayout(new BorderLayout(0, 0));
		
		holder.add(new ButtonPanel(), BorderLayout.SOUTH);
		holder.add(new ImagePanel(), BorderLayout.CENTER);
		
		frame.getContentPane().add(holder);
		frame.setVisible(true);
	}
	
	// JPanel for drawing image
	class ImagePanel extends JPanel
	{
		// sets background color
		public ImagePanel()
		{
			setBackground(DARK_GREEN);
		}
		
		// draws background and image
		public void paintComponent(Graphics g)
		{
			super.paintComponent(g);
			g.drawImage(img, 0, 0, width, height, this);
		}
	}
	
	// JPanel for button
	class ButtonPanel extends JPanel
	{
		// creates button and adds to panel
		public ButtonPanel()
		{
			setBackground(DARK_GREEN);
			setLayout(new FlowLayout());
			setPreferredSize(new Dimension(width, 40));
			
			JButton close = new JButton(CLOSE_NAME);
			close.addActionListener(new CloseButtonListener());
			add(close);
		}
	}
	
	// button listener to close frame
	class CloseButtonListener implements ActionListener
	{
		// closes the frame
		public void actionPerformed(ActionEvent evt)
		{
			frame.setVisible(false);
		}
	}
}
