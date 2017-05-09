import java.awt.Graphics;

public interface Organism{
	
	public void update();
	
	public void draw(Graphics g);
	
	public void die();
	
	public boolean isDead();
	
}