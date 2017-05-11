package SwarmPackage;

import java.awt.Graphics;
import java.util.ArrayList;

public class Prey implements Organism{

	private static double ATTRACTION = .00001;
	private static double VEL_MATCHING = .001;
	
	private Vector2d position;
	private Vector2d velocity;
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
	public void update(ArrayList<Organism> orgs) {
		Vector2d com = new Vector2d(0,0);
		Vector2d avgVel = new Vector2d(0,0);
		Vector2d repuls = new Vector2d(0,0);
		int numPrey = 0;
		for(Organism o : orgs){
			if(o instanceof Prey && !o.equals(this)){
				com.add(o.getPosition());
				avgVel.add(o.getVelocity());
				
				Vector2d p1 = position.copy();
				Vector2d p2 = o.getPosition().copy();
				p2.scale(-1);
				p1.add(p2);
				if(p1.getMagnitude() <= 10){
					p1.scale(.01);
					repuls.add(p1);
				}
				numPrey++;
			}
		}
		
		if(numPrey > 0){
			com.scale(1.0/numPrey);
			Vector2d p2 = position.copy();
			p2.scale(-1.0);
			com.add(p2);
			com.scale(ATTRACTION);
			
			avgVel.scale(1.0/numPrey);
			avgVel.scale(VEL_MATCHING);
			
			velocity.add(com);
			velocity.add(avgVel);	
			velocity.add(repuls);
		}

		if(position.getX() >= 590 || position.getX() <= 0){
			velocity.scale(-1,1);
		}
		if(position.getY() >= 590 || position.getY() <= 0){
			velocity.scale(1,-1);
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