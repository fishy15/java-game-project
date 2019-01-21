// Aaryan Prakash
// 4/30/18 
// ImageViewer.java 
// The users uses this to look at various images for help. 

import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.imageio.ImageIO;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Image;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.io.File;
import java.io.IOException;

public class ImageViewer extends JPanel
{
	private JMenu choices; // JMenu variable that is shown
	private JMenuItem[] menuItems; // array of JMenuItems (choices of images)
	private String[] imageNames; // names of image files
	private String[] userImageNames; // names of images (user friendly)
	private int imageToShow; // index in array of image to show
	private Image[] images; // array holding all the images
	 
	private final Color DARK_GREEN = new Color(2, 68, 0); // dark green color for background
	
	// creates JMenuBar and arrays of Images
	public ImageViewer()
	{
		imageNames = new String[]{"img/foodplate.png", "img/sleepchart.jpg", 
			"img/fatamount.png", "img/burgerworkout.jpg"};
		userImageNames = new String[]{"Food Plate", "Sleep Chart", 
			"Fat Proportion", "Exercise Amounts"};
		images = new Image[imageNames.length];
		menuItems = new JMenuItem[imageNames.length];
		openAllImageFiles();
		
		setPreferredSize(new Dimension(200, 220));
		setBackground(DARK_GREEN);
		
		JMenuBar imageChooser = new JMenuBar();
		imageChooser.setPreferredSize(new Dimension(200, 20));
		imageChooser.setLocation(0, -10);
		
		choices = new JMenu("Choose Image:");
		choices.setPreferredSize(new Dimension(200, 20));
		
		createMenuItems();

		imageChooser.add(choices);		
		add(imageChooser);
		
		addMouseListener(new ImageClickListener());
		
		imageToShow = 0;
	}
	
	// draws image and background
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(images[imageToShow], 0, 30, 200, 190, this);
	}
	
	// adds JMenuItems to menuItems array
	public void createMenuItems()
	{
		for (int i = 0; i < imageNames.length; i++)
		{
			menuItems[i] = new JMenuItem(userImageNames[i]);
			menuItems[i].addActionListener(new MenuListener());
			choices.add(menuItems[i]);
			if (i != imageNames.length - 1) 
			{
				choices.addSeparator();
			}
		}
	}
	
	// opens image files and puts in images array
	public void openAllImageFiles()
	{
		for (int i = 0; i < imageNames.length; i++)
		{
			images[i] = openImage(imageNames[i]);
		}
	}
	
	// opens a single image file and returns the image
	public Image openImage(String imageName)
	{
		Image pic = null;
		File picFile = new File(imageName);
		try 
		{
			pic = ImageIO.read(picFile);
		}
		catch (IOException e)
		{
			System.err.printf("%s cannot be opened.", imageName);
			e.printStackTrace();
			System.exit(0);
		}
		
		return pic;
	}
	
	// listener for JMenuBar
	class MenuListener implements ActionListener
	{
		// changes imageToShow based on what user clicks 
		// and calls repaint() to redraw image
		public void actionPerformed(ActionEvent evt)
		{
			String source = evt.getActionCommand();
			
			for (int i = 0; i < userImageNames.length; i++)
			{
				if (source.equals(userImageNames[i]))
				{
					imageToShow = i;
				}
			}
			
			repaint();
		}
	}
	
	// listener for opening up image larger
	class ImageClickListener implements MouseListener
	{
		// checks if user clicked on image
		// if so, creates a frame with that image
		public void mousePressed(MouseEvent evt) 
		{
			int y = evt.getY();
			if (y >= 30)
			{
				ImagePopUp popup = new ImagePopUp(images[imageToShow], 
					userImageNames[imageToShow]);
				popup.createFrame();
			}
		}
		
		// unused MouseListener methods
		public void mouseReleased(MouseEvent evt) {}
		public void mouseClicked(MouseEvent evt) {}
		public void mouseEntered(MouseEvent evt) {}
		public void mouseExited(MouseEvent evt) {}
	}
}
