package whorten.generator;

import java.util.ArrayList;
import java.util.List;

public class PixelSet {

	List<Pixel> pixels;
	
	public PixelSet(){
		pixels = new ArrayList<Pixel>();
	}
	
	public void addPixel(int r, int g, int b){
		pixels.add(new Pixel(r,g,b));
	}
	
	public long squaredError(){
		//first, get average of r, g, b
		double aveR = 0;
		double aveG = 0;
		double aveB = 0;
		
		for(Pixel p : pixels){
			aveR += p.getRed();
			aveG += p.getGreen();
			aveB += p.getBlue();
		}
		
		aveR /= pixels.size();
		aveG /= pixels.size();
		aveB /= pixels.size();
		
		//get total sum of squared error
		long error = 0;
		for(Pixel p : pixels){
			error += (long)Math.pow(Math.abs(p.getRed() * 1.0 - aveR),2);
			error += (long)Math.pow(Math.abs(p.getGreen() * 1.0 - aveG),2);
			error += (long)Math.pow(Math.abs(p.getBlue() * 1.0 - aveB),2);
		}
		return error;
	}
	
	private class Pixel{
		private final int r;
		private final int g;
		private final int b;
		
		Pixel(int r, int g, int b){
			this.r = r;
			this.g = g;
			this.b = b;
		}
		
		int getRed(){
			return r;
		}
		
		int getGreen(){
			return g;
		}
		
		int getBlue(){
			return b;
		}
	}
}
