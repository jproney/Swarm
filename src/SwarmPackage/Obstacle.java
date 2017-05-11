package SwarmPackage;

import java.awt.Graphics;
import java.util.ArrayList;

public class Obstacle implements Sprite{

	private Vector2d position;
	
	public Obstacle(Vector2d pos){
		position = pos;
	}
	
	public Obstacle(double x, double y){
		this(new Vector2d(x,y));
	}
	
	@Override
	public void update(ArrayList<Sprite> orgs) {}

	@Override
	public void draw(Graphics g) {
		g.fillArc((int)position.getX(), (int)position.getY(), 10, 10, 0, 360);
		
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

	@Override
	public Vector2d getPosition() {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public Vector2d getVelocity() {
		// TODO Auto-generated method stub
		return new Vector2d(0,0);
	}
	
}