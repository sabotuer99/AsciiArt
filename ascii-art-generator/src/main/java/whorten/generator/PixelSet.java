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
	
	public Pixel getAveragePixel(){
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
		
		return new Pixel((int)aveR, (int)aveG, (int)aveB);
	}
	
	public long squaredError(){
		
		Pixel avg = getAveragePixel();
		
		//get total sum of squared error
		long error = 0;
		for(Pixel p : pixels){
			error += (long)Math.pow(Math.abs(p.getRed() - avg.getRed()),2);
			error += (long)Math.pow(Math.abs(p.getGreen() - avg.getGreen()),2);
			error += (long)Math.pow(Math.abs(p.getBlue() - avg.getBlue()),2);
		}
		return error;
	}
}
