package whorten.generator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Scanner;

import whorten.colors.Colors;

public class PixelMapper {

	public String bestCharacter(int[][] image) throws IOException{
		if(image.length != 16 || image[0].length != 8)
			throw new IllegalArgumentException("Input must be 8x16");
		
		long bestScore = Long.MAX_VALUE;
		String bestChar = "";
		
		for(File map : getMapFiles()){
			Scanner file = new Scanner(Files.newInputStream(map.toPath()));
			String rep = file.nextLine();
			PixelSet A = new PixelSet();
			PixelSet B = new PixelSet();
			//use map file to assign pixels to respective maps
			for(int row = 0; row < 16; row++){
				String line = file.nextLine();
				for(int col = 0; col < 8; col++){
					
	    	        int pixel = image[row][col];
	    	        int red = (pixel >> 16) & 0xff;
	    	        int green = (pixel >> 8) & 0xff;
	    	        int blue = (pixel) & 0xff;
										
					char indicator = line.charAt(col);
					if(indicator == '0'){
						A.addPixel(red, green, blue);
					} else {
						B.addPixel(red, green, blue);
					}
				}
			}
			file.close();
						
			long scoreThisFile = A.squaredError() + B.squaredError();
			//System.out.println("Score for '" + rep + "': " + scoreThisFile);
					
			if(scoreThisFile < bestScore){
				bestScore = scoreThisFile;
				//append(Colors.foreground(red, green, blue))
				Pixel avgA = A.getAveragePixel();
				Pixel avgB = B.getAveragePixel();
				bestChar = Colors.foreground(avgB.getRed(), avgB.getGreen(), avgB.getBlue()) + 
						   //Colors.background(avgA.getRed(), avgA.getGreen(), avgA.getBlue()) + 
						   rep +
						   Colors.RESET_ALL;
			}
		}
		
		return bestChar;
	}
	
	protected File[] getMapFiles() {
		ClassLoader loader = ClassLoader.getSystemClassLoader();
		URL url = loader.getResource("maps");
		String path = url.getPath();
		return new File(path).listFiles();
	}
}
