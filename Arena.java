import java.util.Observer;
import java.util.ArrayList;
import java.awt.Graphics;
import java.util.Observable;

public class Arena implements Observer{
	
	private ArrayList<Organism> orgList;
	private Graphics graph;
	
	public Arena(Graphics g){
		orgList = new ArrayList<Organism>();
		graph = g;
	}
	
	public void addOrganism(Organism o){
		orgList.add(o);
	}
	
	
	public void updateOrganisms(){
		for(int i = 0; i < orgList.size(); i++){
			orgList.get(i).update();
			if(orgList.get(i).isDead()){
				orgList.remove(i);
				i--;
			}
			else{
				orgList.get(i).draw(graph);
			}
		}
	}
	
	public void update(Observable o, Object arg){
		
	}
	
}