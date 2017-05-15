package SwarmPackage;


import javax.swing.JPanel;

import java.util.ArrayList;
import java.awt.Dimension;
import java.awt.Graphics;

public class Arena extends JPanel implements Runnable{
	
	
	private static long GAME_TICK = 20 * 1000; //20000 nanoseconds = 20 ms
	private ArrayList<Organism> orgList;
	
	public Arena(){
		orgList = new ArrayList<Organism>();
	}
	
	public void addOrganism(Organism o){
		orgList.add(o);
	}
	
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i < orgList.size(); i++){
			orgList.get(i).update(orgList);
			if(orgList.get(i).isDead()){
				orgList.remove(i);
				i--;
			}
			else{
				orgList.get(i).draw(g);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
	    // so that our GUI is big enough
		return new Dimension(600, 600);
	}
	

	@Override
	public void run() {
		long startTime = System.nanoTime();
		while(true){
			if(System.nanoTime() - startTime > GAME_TICK){
				repaint();
				startTime = System.nanoTime();
			}
		}
		
	}
	
}