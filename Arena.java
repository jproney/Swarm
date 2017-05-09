
import java.util.ArrayList;

public class Arena{
	
	private ArrayList<Organism> orgList;
	
	public Arena(){
		orgList = new ArrayList<Organism>();
	}
	
	public void addOrganism(Organism o){
		orgList.add(o);
	}
	
}