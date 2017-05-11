package SwarmPackage;


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
	
	public void add(Vector2d vec){
		xComp += vec.getX();
		yComp += vec.getY();
	}
	
	public void scale(double x, double y){
		xComp *= x;
		yComp *= y;
	}

	
	public void scale(double scale){
		scale(scale, scale);
	}
		
	public void normalize(double len){
		scale(len/getMagnitude());
	}
	
	public void normalize(){
		normalize(1.0);
	}
	
}