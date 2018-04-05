package whorten.ascii_art_generator;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import whorten.generator.PixelMapper;

public class PixelMapperTests {

	@Test
	public void bestCharacter_givenObviousCommaPic_returnsComma() throws IOException{
		int[][] image = get2d("comma_test.png");
		PixelMapper sut = new PixelMapper();
		String result = sut.bestCharacter(image);
		
		assertEquals(",", result);
	}
	
	int[][] get2d(String filename) throws IOException{
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		BufferedImage bi = ImageIO.read(classLoader.getResourceAsStream(filename));
		int width = bi.getWidth();
		int height = bi.getHeight();
		int[] pixelsflattened = bi.getRGB(0, 0, width, height, null, 0, width);
		int[][] array2d = new int[height][width];
		for(int row = 0; row < height; row++){
			for(int col = 0; col < width; col++){
				array2d[row][col] = pixelsflattened[row*width + col];
			}				
		}
		return array2d;
	}
}