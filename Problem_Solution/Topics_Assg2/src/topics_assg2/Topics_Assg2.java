/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package topics_assg2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author fawad
 */
public class Topics_Assg2 {

    /**
     * @param args the command line arguments
     */
   /* public static void main(String[] args) {
        // TODO code application logic here
    }*/
    
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
		new Topics_Assg2().CrateGUI();
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
		String[] initialVals = {"Color2Gray", "Negative", "Flip-HRZ", "Flip-VRT","Rotate-90-Left", "Rotate-90-Right", "3x3 Weighted Averaging", "Scale Up", "Scale Down", "3x3 Gaussian" };
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
			originalBufImage = ImageObj1.getImageData();	//get the original image data in ImageBuffer form
			tgtBufImage = originalBufImage; 		//copy to another object so that we can do processing separately
			//todo:
			//use a loop to copy the image to target 
			
			
			
			//todo:
			//Now call the Image Library function(you need to write library) to perform desired action
			//operation depends on the selected value (.getSelectedItem) of the Jcombo
			System.out.println(comboSelectOperation.getSelectedItem());
                        
                        if(comboSelectOperation.getSelectedItem()=="Color2Gray")
                        {
                          tgtBufImage = ImageLibrary.ConvertToGray(originalBufImage);  
                        }
                        else if(comboSelectOperation.getSelectedItem()=="Negative")
                        {
                          tgtBufImage = ImageLibrary.Negative(originalBufImage);  
                        }
                        else if(comboSelectOperation.getSelectedItem()=="Flip-HRZ")
                        {
                            tgtBufImage = ImageLibrary.invertImage(originalBufImage);
                            tgtBufImage = ImageLibrary.FlipH(originalBufImage);  
                        }
                        else if(comboSelectOperation.getSelectedItem()=="Flip-VRT")
                        { 
                            tgtBufImage = ImageLibrary.FlipV(originalBufImage);  
                        }
                        else if(comboSelectOperation.getSelectedItem()=="Rotate-90-Left")
                        {
                            tgtBufImage = ImageLibrary.invertImage(originalBufImage);
                            tgtBufImage = ImageLibrary.rotate90ToLeft(originalBufImage);
                        }
                        else if(comboSelectOperation.getSelectedItem()=="Rotate-90-Right")
                        {  
                            tgtBufImage = ImageLibrary.rotate90ToRight(originalBufImage);
                        }
                        else if(comboSelectOperation.getSelectedItem()=="3x3 Weighted Averaging")
                        {
                           tgtBufImage = ImageLibrary.weightedAveraging(originalBufImage); 
                        }
                        else if(comboSelectOperation.getSelectedItem()=="Scale Up")
                        {
                                try { 
                                    tgtBufImage = ImageLibrary.ScaleUp(originalBufImage);
                                } catch (IOException ex) {
                                    Logger.getLogger(Topics_Assg2.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }
			else if(comboSelectOperation.getSelectedItem()=="Scale Down")
                        {
                                try {
                                    tgtBufImage = ImageLibrary.ScaleDown(originalBufImage);                                    
                                } catch (IOException ex) {
                                    Logger.getLogger(Topics_Assg2.class.getName()).log(Level.SEVERE, null, ex);
                                }
                        }
                        else if(comboSelectOperation.getSelectedItem()=="3x3 Gaussian")
                        {
                            tgtBufImage = originalBufImage;
                        }
                        
                        
			File output = new File(imgfileProcessed);
                        try {
                            ImageIO.write(tgtBufImage, "bmp", output);						
                            
                            //update the ".bmp" file (after the operation is complete
                            //update the ".bmp" file (after the operation is complete
                        } catch (IOException ex) {
                            Logger.getLogger(Topics_Assg2.class.getName()).log(Level.SEVERE, null, ex);
                        }
			
                        
			
			
			//update the image on the screen
			panelCenterRigth.remove(ImageObj2);                        //Step1: remove the previous
                        //imgfileProcessed = ImageObj2.getImageData();
			ImageObj2 =  new ImageObj(imgfileProcessed); //Step 2: update the processedImage Object and add to the panel
			panelCenterRigth.add( ImageObj2 );
			f.pack(); //will will be resized as image 
			f.validate(); 								//Step 3: (all window will be redrawn again)
			
			}
		}
	}
	
    
}
