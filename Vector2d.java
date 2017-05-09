
public class Vector2d{
	
	private double xComp;
	private double yComp;
	
	public Vector2d(double x, double y){
		xComp = x;
		yComp = y;
	}
	
	public double getX(){
		return xComp;
	}
	
	public double getY(){
		return yComp;
	}
	
	public double getMagnitude(){
		return Math.sqrt(Math.pow(xComp,2) + Math.pow(yComp,2));
	}
	
	public Vector2d add(Vector2d vec){
		return new Vector2d(xComp + vec.getX(), yComp + vec.getY());
	}
	
	public Vector2d scale(double scale){
		return new Vector2d(xComp*scale, yComp*scale);
	}
	
	public Vector2d normalize(double len){
		return scale(len/getMagnitude());
	}
	
	public Vector2d normalize(){
		return normalize(1.0);
	}
	
}