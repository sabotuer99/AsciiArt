package whorten.generator;

class Pixel{
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