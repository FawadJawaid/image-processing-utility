//JFrame is always needed.
//JPanel is contained inside a JFrame.   JPanel can contain other objects and the whole set of objects (inside a panel) can be considered as one big object

//Further Reference:  https://docs.oracle.com/javase/tutorial/2d/images/index.html

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;


//Main Program (i.e. this class) creates/handles GUI.
public class Proj2_Template
{
	/* ==============================
	Global variables (for GUI) because they are used by more than one functions in this class
	==============================*/
	JFrame f;
	JComboBox comboSelectOperation;
	JButton updateButton;
	JPanel panelCenterRigth;
	ImageObj ImageObj1, ImageObj2;
	String imgFileOriginal = "Image1.bmp";  //original image...not supposed to change
	//this file will be updated after every operation. So GUI will load this file after every operation.
	String imgfileProcessed = "Processed_Image_tmp_000.bmp";	//initially use as the original image
	
	 BufferedImage originalBufImage;	//to store image data from original image
	 BufferedImage tgtBufImage;			//modified data will be stored here (based on the operation)
	  
	  
	
	
	public static void main(String[] args) 
	{
		//Create and display GUI
		new Proj2_Template().CrateGUI();
	} //main() ends here
	
	
	
	
	
	//This function creates the GUI.
	void CrateGUI()
	{
	
//main Window (i.e. Frame)	
		/* GUI Related Code starts Here */
		f = new JFrame("Template for Project-2(Image Processing)");
		f.setSize(350, 250);
		Container contTopFrame = f.getContentPane();  //windows(area) where other objects can be placed
		
		//Program terminated when "X" button of window pressed
		f.addWindowListener( new WindowAdapter()
		{
            public void windowClosing(WindowEvent e) 
			{
                System.exit(0);
            }
			public void windowActivated(WindowEvent e) 
			{
				System.out.println("WindowListener method called: windowActivated.");
			}
 		}
		);


//top panel		
		/////////////////////////////////////////////
		//on top we need three controls (Label, ComboBox and a Button). So add them to a panel and add panel to the NORTH.  (because can not add two objects directly on north, so panel needed
		/////////////////////////////////////////////
		JLabel topLabel = new JLabel("Chose an Operation: ");
		String[] initialVals = {"Color2Gray", "Negative", "Flip-HRZ", "Flip-VRT","Rotate-90-Left", "Rotate-90-Right" };
		comboSelectOperation = new JComboBox(initialVals);
		comboSelectOperation.setEditable(true);
		updateButton= new JButton("Update");
		ButtonListener btnListener = new ButtonListener();	//Class that listens to buttons and takes actions
		updateButton.addActionListener(btnListener);
		
		JPanel panelNorth = new JPanel();
		panelNorth.add(topLabel);			//control 1:
		panelNorth.add(comboSelectOperation);//control 2:
		panelNorth.add(updateButton);	//control 3:
		contTopFrame.add(panelNorth, BorderLayout.NORTH);  //add North panel to the frame container area

//middle panel			
		/////////////////////////////////////////////
		//Center Panel:    has two image displayers.
		/////////////////////////////////////////////
		JPanel panelCenter= new JPanel();
		panelCenter.setLayout(new GridLayout(1,2));
		JPanel panelCenterLeft = new JPanel(); panelCenterLeft.setBackground(Color.white);
		panelCenterRigth = new JPanel(); panelCenterRigth.setBackground(Color.lightGray);
		ImageObj1 = new ImageObj(imgFileOriginal);
		ImageObj2 = new ImageObj(imgFileOriginal);  //in the beginning use same file for both 
		
		panelCenterLeft.add( ImageObj1 );
		panelCenterRigth.add( ImageObj2 );
			
		panelCenter.add(panelCenterLeft);
		panelCenter.add(panelCenterRigth);
		contTopFrame.add(panelCenter, BorderLayout.CENTER);  //add North panel to the frame
		
//Bottom panel	
		//Bottom panel (on the SOUTH). Two labels (original image and processed image");
		JPanel panelBottom= new JPanel();
		panelBottom.setLayout(new GridLayout(1,2));  //one row, two columns
		panelBottom.add(new Label("Original Image"));
		panelBottom.add(new Label("Processed Image"));
		contTopFrame.add(panelBottom, BorderLayout.SOUTH);  //add at bootom
	
//Resize and Show		
		f.setSize(800,600); 
		f.pack();  //for image adjustments(all objects are resized to fit). If removed, image is in default size
		f.setVisible(true); //Display the GUI
		/* GUI Related Code ends */ 
	} //CrateGUI() ends here
	
	
	
	//inner class (Button Handler)
	class ButtonListener implements ActionListener 
	{
		public void actionPerformed(ActionEvent e) 
		{
			if (e.getSource() == updateButton)
			{
			System.out.println("Update Button Pressed"); // button

			//Here: put your Image processing algorithm (i.e. call to your image library function)
			originalBufImage =ImageObj1.getImageData();	//get the original image data in ImageBuffer form
			tgtBufImage = originalBufImage; 		//copy to another object so that we can do processing separately
			//todo:
			//use a loop to copy the image to target 
			
			
			
			//todo:
			//Now call the Image Library function(you need to write library) to perform desired action
			//operation depends on the selected value (.getSelectedItem) of the Jcombo
			System.out.println(comboSelectOperation.getSelectedItem());
			
									
			
			//todo: 
			//update the ".bmp" file (after the operation is complete
			
			
			
			//update the image on the screen
			panelCenterRigth.remove(ImageObj2);  		//Step1: remove the previous
			ImageObj2 = new ImageObj(imgfileProcessed); //Step 2: update the processedImage Object and add to the panel
			panelCenterRigth.add( ImageObj2 );
			//f.pack(); //will will be resized as image 
			f.validate(); 								//Step 3: (all window will be redrawn again)
			
			}
		}
	}
	
	
} //class ends()
