package SwarmPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Prey implements Sprite{

	
	private static int ARENA_WIDTH = 600;
	private static int ARENA_HEIGHT = 600;
	private static double ATTRACTION = .00001;
	private static double VEL_MATCHING = .001;
	
	private Vector2d position;
	private Vector2d velocity;
	private boolean dead = false;
	private double velMag;
	
	public Prey(Vector2d pos){
		position = pos;
		velocity = new Vector2d(.2,.5);
		velMag = velocity.getMagnitude();
	}
	
	public Prey(double x, double y){
		this(new Vector2d(x,y));
	}
	
	@Override
	public void update(ArrayList<Sprite> orgs) {
		Vector2d com = new Vector2d(0,0);
		Vector2d avgVel = new Vector2d(0,0);
		Vector2d repuls = new Vector2d(0,0);
		int numPreyInRadius = 0;
		for(Sprite o : orgs){
			if(o instanceof Prey && !o.equals(this)){
				Vector2d p1 = position.copy();
				Vector2d p2 = o.getPosition().copy();
				p2.scale(-1);
				p1.add(p2);
				if(p1.getMagnitude() <= 150){
					com.add(o.getPosition());
					avgVel.add(o.getVelocity());
				    numPreyInRadius++;
				}
				if(p1.getMagnitude() <= 10){
					p1.normalize(.01);
					repuls.add(p1);
				}
			}
			else if(o instanceof Obstacle || o instanceof Predator){
				Vector2d p1 = position.copy();
				Vector2d p2 = o.getPosition().copy();
				p2.scale(-1);
				p1.add(p2);
				if(p1.getMagnitude() <= 15 && o instanceof Predator){
					this.die();
				}
				else if(p1.getMagnitude() <= 50){
					p1.normalize(.01);
					Vector2d orth = p1.ortho();
					repuls.add(orth);
				}
			}
		}
		
		if(numPreyInRadius > 0){
			com.scale(1.0/numPreyInRadius);
			Vector2d p2 = position.copy();
			p2.scale(-1.0);
			com.add(p2);
			com.scale(ATTRACTION);
			
			avgVel.scale(1.0/numPreyInRadius);
			avgVel.scale(VEL_MATCHING);
			
			velocity.add(com);
			velocity.add(avgVel);	
			velocity.add(repuls);
		}

		if((ARENA_WIDTH - position.getX()) < 150){
			velocity.add(new Vector2d(-.001,0));
		}
		if((position.getX()) < 150){
			velocity.add(new Vector2d(.001, 0));
		}
		if((ARENA_HEIGHT - position.getY()) < 150){
			velocity.add(new Vector2d(0, -.001));
		}
		if((position.getY()) < 150){
			velocity.add(new Vector2d(0, .001));
		}
		
		velocity.capMag(velMag);
		position.add(velocity);
		
	}
	
	public Vector2d getPosition(){
		return position;
	}
	
	public Vector2d getVelocity(){
		return velocity;
	}


	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		Vector2d axis1 = velocity.copy();
		axis1.normalize(10);
		Vector2d axis2 = axis1.ortho();
		axis2.normalize(7);
		int[] xPoints = {(int)(position.getX() + axis1.getX()),(int)(position.getX() + axis2.getX()),
				(int)(position.getX() - axis1.getX()), (int)(position.getX() - axis2.getX())};
		int[] yPoints = {(int)(position.getY() + axis1.getY()),(int)(position.getY() + axis2.getY()),
				(int)(position.getY() - axis1.getY()), (int)(position.getY() - axis2.getY())};
		g.fillPolygon(xPoints, yPoints, 4);
	}

	@Override
	public void die() {
		dead = true;
		
	}

	@Override
	public boolean isDead() {
		// TODO Auto-generated method stub
		return dead;
	}
	
}