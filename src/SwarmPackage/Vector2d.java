package SwarmPackage;


public class Vector2d{
	
	private double xComp;
	private double yComp;
	
	public Vector2d(double x, double y){
		xComp = x;
		yComp = y;
	}
	
	public void rotate(double ang){
		xComp = xComp*Math.cos(ang) - yComp*Math.sin(ang);
		yComp = xComp*Math.sin(ang) + yComp*Math.cos(ang);
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
	
	public void capMag(double max){
		if(getMagnitude() > max){
			scale(Math.sqrt(max/getMagnitude()));
		}
	}

	
	public Vector2d copy(){
		return new Vector2d(xComp, yComp);
	}
	
	public double dot(Vector2d other){
		return xComp*other.getX() + yComp*other.getY();
	}
	
	public Vector2d project(Vector2d other){
		Vector2d projection = other.copy();
		projection.scale(other.dot(this)/other.dot(other));
		return projection;
	}
	
	public Vector2d ortho(){
		return new Vector2d(yComp, -xComp);
	}
		
}