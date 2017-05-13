package SwarmPackage;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SimulationRunner {

	static JFrame frame = new JFrame();
	static JPanel panel = new JPanel();
	static JButton addPrey = new JButton("Add Prey");
	static Arena arena = new Arena();
	static Thread executor = new Thread(arena);

	public static void main(String args[]) {
		addPrey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arena.addOrganism(new Prey(100, 100));
			}
		});
		panel.add(addPrey);
		frame.setLayout(new GridLayout(1, 2));
		frame.getContentPane().add(arena);
		frame.getContentPane().add(panel);
		frame.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				if (me.isShiftDown()) {
					arena.addOrganism(new Obstacle(me.getX(), me.getY()));
				}
				else if (me.isAltDown()){
					arena.addOrganism(new Predator(me.getX(), me.getY()));
				}
				else {
					arena.addOrganism(new Prey(me.getX(), me.getY()));
				}
			}
		});
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		executor.start();
	}

}