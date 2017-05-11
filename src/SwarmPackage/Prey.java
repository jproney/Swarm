package SwarmPackage;

import java.awt.Graphics;

public class Prey implements Organism{

	private Vector2d position;
	private Vector2d velocity;
	private Vector2d acceleration; 
	
	public Prey(Vector2d pos){
		position = pos;
		velocity = new Vector2d(.2,.5);
		acceleration = new Vector2d(0.0,0.0);
	}
	
	public Prey(double x, double y){
		this(new Vector2d(x,y));
	}
	
	@Override
	public void update() {
		velocity.add(acceleration);
		position.add(velocity);
		if(position.getX() >= 590 || position.getX() <= 0){
			velocity.scale(-1,1);
		}
		if(position.getY() >= 590 || position.getY() <= 0){
			velocity.scale(1,-1);
		}
		
	}

	@Override
	public void draw(Graphics g) {
		g.fillRect((int)position.getX(), (int)position.getY(), 15, 15);
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return false;
	}
	
}