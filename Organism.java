
public abstract class Organism{
	
	protected Vector2d position;
	protected Vector2d velocity;
	protected Vector2d acceleration;
	
	public abstract void update();
	
	public abstract void draw();
	
	public void addToArena(){
		
	}
	
	public void removeFromArena(){
		
	}
	
}