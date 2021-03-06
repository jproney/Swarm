package SwarmPackage;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Arena extends JPanel implements Runnable, ChangeListener {

    private double breedLevel = 0.0;
    private JSlider breedSlider;
    private static long GAME_TICK = 20 * 1000; // 20000 nanoseconds = 20 ms
    private ArrayList<Sprite> orgList;

    public Arena() {
        orgList = new ArrayList<Sprite>();
        breedSlider = new JSlider(JSlider.HORIZONTAL, 0, 100, 0);
        breedSlider.setMajorTickSpacing(10);
        breedSlider.addChangeListener(this);
        this.add(breedSlider);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
        if (!source.getValueIsAdjusting()) {
            breedLevel = source.getValue();
        }
    }

    public void addOrganism(Sprite o) {
        orgList.add(o);
    }

    public void breed() {
        if (Math.random() > (1 - .0000015 * breedLevel * orgList.size())) {
            addOrganism(new Prey((int) (Math.random() * 800), (int) (Math.random() * 800)));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < orgList.size(); i++) {
            orgList.get(i).update(orgList);
            if (orgList.get(i).isDead()) {
                orgList.remove(i);
                i--;
            } else {
                orgList.get(i).draw(g);
            }
        }
        breed();
    }

    @Override
    public Dimension getPreferredSize() {
        // so that our GUI is big enough
        return new Dimension(800, 800);
    }

    @Override
    public void run() {
        long startTime = System.nanoTime();
        while (true) {
            long nanosDiff = System.nanoTime() - startTime;
            if (nanosDiff > GAME_TICK) {
                repaint();
                startTime = System.nanoTime();
            } else {
                try {
                    TimeUnit.NANOSECONDS.sleep(nanosDiff);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

    }

}