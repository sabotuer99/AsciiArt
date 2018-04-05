package whorten.ascii_art_generator;

import static org.junit.Assert.*;

import org.junit.Test;

import whorten.generator.PixelSet;

public class PixelSetTests {

	@Test
	public void squaredError_givenThreePixels_returnsCorrectScore(){
		PixelSet sut = new PixelSet();
		sut.addPixel(100, 100, 100);
		sut.addPixel(97, 97, 97);
		sut.addPixel(103, 103, 103);
		
		long score = sut.squaredError();
		
		assertEquals(54, score); 
	}
}
