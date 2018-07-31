package SwarmPackage;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SimulationRunner {

    static JFrame frame = new JFrame();
    static JPanel panel = new JPanel();
    static JLabel addPrey = new JLabel("Click Anywhere to add Prey");
    static JLabel addObst = new JLabel("Shift + Click to add Obstacles");
    static JLabel addPred = new JLabel("Alt + Click to add Predators");
    static JLabel changeBreed =
            new JLabel("<html>Move the slider to change<br> Prey spawning rate</html>", SwingConstants.CENTER);
    static Arena arena = new Arena();
    static Thread executor = new Thread(arena);

    public static void main(String args[]) {
        panel.add(addPrey);
        panel.add(addObst);
        panel.add(addPred);
        panel.add(changeBreed);
        panel.setLayout(new GridLayout(4, 1, 5, 0));
        frame.getContentPane().add(arena);
        frame.getContentPane().add(panel, BorderLayout.EAST);
        // frame.getContentPane().add(panel);
        arena.addMouseListener(new MouseAdapter() {

            public void mousePressed(MouseEvent me) {
                if (me.isShiftDown()) {
                    arena.addOrganism(new Obstacle(me.getX(), me.getY()));
                } else if (me.isAltDown()) {
                    arena.addOrganism(new Predator(me.getX(), me.getY()));
                } else {
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