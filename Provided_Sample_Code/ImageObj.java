import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

class ImageObj extends Component 
	{
		BufferedImage img;
		
		//Every object has dimentions...we define our dimentions (for our object)
		 public Dimension getPreferredSize() {
        if (img == null) {
	           return new Dimension(100,100);
        } else {
	         return new Dimension(img.getWidth(null), img.getHeight(null));
       }
    }
		ImageObj(BufferedImage imgObj)
		{
			//todo: 
			//copy all data to local object (not just pointer)...
			
		}
		
		ImageObj(final String filename)
		{
			try {
				img = ImageIO.read(new File(filename));
				}catch(Exception e)
				{
					System.out.println("Exception: "+e);
				}
		}

		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
		
		public BufferedImage getImageData()
		{
			return img;
		}
	}
	