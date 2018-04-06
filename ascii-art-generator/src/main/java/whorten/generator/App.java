package whorten.generator;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

import javax.imageio.ImageIO;

import whorten.colors.Colors;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	ClassLoader classLoader = ClassLoader.getSystemClassLoader();
    	
    	String file = args[0];
    	
    	URL[] urls = ((URLClassLoader)classLoader).getURLs();

        for(URL url: urls){
        	System.out.println(url.getFile());
        }
    	
    	BufferedImage bi = ImageIO.read(classLoader.getResourceAsStream(file)); //new File("happykitty.jpg"));
    	StringBuilder output = new StringBuilder();
    	
    	for (int startrow = 0; startrow < bi.getHeight() - 16; startrow+=16) {
    		for (int startcol = 0; startcol < bi.getWidth() - 8; startcol+=8) {  
    			
    			int[] pixelsflattened = bi.getRGB(startcol, startrow, 8, 16, null, 0, 8);
    			int[][] chunk = new int[16][8];
    			for(int row = 0; row < 16; row++){
    				for(int col = 0; col < 8; col++){
    					chunk[row][col] = pixelsflattened[row*8 + col];
    				}				
    			}
    			PixelMapper sut = new PixelMapper();
    			String dope = sut.bestCharacter(chunk);
    			output.append(dope);
    		}	
    		output.append("\n");
    	}
    	
//    	for (int x = 0; x < bi.getHeight(); x+=6) {
//    	    for (int y = 0; y < bi.getWidth(); y+=3) {
//    	        int pixel = bi.getRGB(y, x);
//    	        int red = (pixel >> 16) & 0xff;
//    	        int green = (pixel >> 8) & 0xff;
//    	        int blue = (pixel) & 0xff;
//    	        //System.out.println("red: " + red + ", green: " + green + ", blue: " + blue);  
//    	        output.append(Colors.foreground(red, green, blue)).append(c);
//    	    }
//    	    output.append("\n");
//    	}
    	
    	System.out.println(output);
    	
    	//Scanner in = new Scanner(System.in);
    	//in.nextLine();
    	//in.close();
    }
}
