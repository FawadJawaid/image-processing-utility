package topics_assg2;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageLibrary {

    public static BufferedImage ConvertToGray(BufferedImage image) {
        BufferedImage result = new BufferedImage //create a new empty image first
                (
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_BYTE_GRAY //empty image is of type Gray
                );
        //copy the data from color image (passed as parameter) to the empty gray image
        Graphics g = result.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();

        return result;
    }

    public static BufferedImage FlipH(BufferedImage image) {
        //todo: put your code here
        AffineTransform tx = AffineTransform.getScaleInstance(1, -1);
        tx.translate(0, -image.getHeight(null));
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);

        return image;
    }

    public static BufferedImage FlipV(BufferedImage image) {
        AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
        tx.translate(-image.getWidth(null), 0);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        image = op.filter(image, null);

        return image;
    }

    //write other functions below
    public static BufferedImage Negative(BufferedImage image) {
        //Make negative of the image
        int width = image.getWidth(); //width and height
        int height = image.getHeight();
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                //print info for every pixel
                Color c = new Color(image.getRGB(j, i));

                //Make negative of the image
                int red = (int) (c.getRed());
                int green = (int) (c.getGreen());
                int blue = (int) (c.getBlue());
                Color newColor = new Color(255 - red, 255 - green, 255 - blue);
                image.setRGB(j, i, newColor.getRGB()); //update pixel values
            } //inner for loop
        } //outer for loop
        return image;
    }

    public static BufferedImage rotate90ToLeft(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage returnImage = new BufferedImage(height, width, inputImage.getType());


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                returnImage.setRGB(y, width - x - 1, inputImage.getRGB(x, y));
            }
        }

        return returnImage;
    }

    public static BufferedImage rotate90ToRight(BufferedImage inputImage) {
        int width = inputImage.getWidth();
        int height = inputImage.getHeight();
        BufferedImage returnImage = new BufferedImage(height, width, inputImage.getType());


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                returnImage.setRGB(height - y - 1, x, inputImage.getRGB(x, y));
            }
        }

        return returnImage;
    }

    public static BufferedImage weightedAveraging(BufferedImage original) {

        int alpha, red, green, blue;
        int newPixel;

        BufferedImage lum = new BufferedImage(original.getWidth(), original.getHeight(), original.getType());

        for (int i = 0; i < original.getWidth(); i++) {
            for (int j = 0; j < original.getHeight(); j++) {

                // Get pixels by R, G, B
                alpha = new Color(original.getRGB(i, j)).getAlpha();
                red = new Color(original.getRGB(i, j)).getRed();
                green = new Color(original.getRGB(i, j)).getGreen();
                blue = new Color(original.getRGB(i, j)).getBlue();

                red = (int) (0.21 * red + 0.71 * green + 0.07 * blue);
                // Return back to original format
                newPixel = colorToRGB(alpha, red, red, red);

                // Write pixels into image
                lum.setRGB(i, j, newPixel);

            }
        }

        return lum;
    }

    public static BufferedImage ScaleUp(BufferedImage image) throws IOException {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double) 600 / imageWidth;
        double scaleY = (double) 500 / imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
                image,
                new BufferedImage(600, 500, image.getType()));
    }
    
     public static BufferedImage ScaleDown(BufferedImage image) throws IOException {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        double scaleX = (double) 300 / imageWidth;
        double scaleY = (double) 300 / imageHeight;
        AffineTransform scaleTransform = AffineTransform.getScaleInstance(scaleX, scaleY);
        AffineTransformOp bilinearScaleOp = new AffineTransformOp(scaleTransform, AffineTransformOp.TYPE_BILINEAR);

        return bilinearScaleOp.filter(
                image,
                new BufferedImage(300, 300, image.getType()));
    }
    
    public static BufferedImage invertImage (BufferedImage inputFile) {
       
        for (int x = 0; x < inputFile.getWidth(); x++) {
            for (int y = 0; y < inputFile.getHeight(); y++) {
                int rgba = inputFile.getRGB(x, y);
                Color col = new Color(rgba, true);
                col = new Color(255 - col.getRed(),
                                255 - col.getGreen(),
                                255 - col.getBlue());
                inputFile.setRGB(x, y, col.getRGB());
            }
        }

        return inputFile;
      
        } 
     
    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red;
        newPixel = newPixel << 8;
        newPixel += green;
        newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }
} //class ImageLibrary ends
