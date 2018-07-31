package SwarmPackage;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Predator implements Sprite {

    private static double ATTRACTION = .00001;
    private static int ARENA_WIDTH = 600;
    private static int ARENA_HEIGHT = 600;

    private Vector2d position;
    private Vector2d velocity;
    private double velMag;

    public Predator(Vector2d pos) {
        position = pos;
        velocity = new Vector2d(.2, .5);
        velMag = velocity.getMagnitude();
    }

    public Predator(double x, double y) {
        this(new Vector2d(x, y));
    }

    @Override
    public void update(ArrayList<Sprite> orgs) {
        Vector2d com = new Vector2d(0, 0);
        Vector2d repuls = new Vector2d(0, 0);
        int numPrey = 0;
        for (Sprite o : orgs) {
            if (o instanceof Prey) {
                Vector2d p1 = position.copy();
                Vector2d p2 = o.getPosition().copy();
                p2.scale(-1);
                p1.add(p2);
                if (p1.getMagnitude() <= 150) {
                    com.add(o.getPosition());
                    numPrey++;
                }
            } else if (o instanceof Obstacle) {
                Vector2d p1 = position.copy();
                Vector2d p2 = o.getPosition().copy();
                p2.scale(-1);
                p1.add(p2);
                if (p1.getMagnitude() <= 50) {
                    p1.normalize(.01);
                    Vector2d orth = p1.ortho();
                    repuls.add(orth);
                }
            }
        }

        if (numPrey > 0) {
            com.scale(1.0 / numPrey);
            Vector2d p2 = position.copy();
            p2.scale(-1.0);
            com.add(p2);
            com.scale(ATTRACTION);

            velocity.add(com);
            velocity.add(repuls);
        }

        if ((ARENA_WIDTH - position.getX()) < 150) {
            velocity.add(new Vector2d(-.001, 0));
            if ((ARENA_WIDTH - position.getX()) < 0) {
                velocity.setX(-Math.abs(velocity.getX()));
            }
        }
        if ((position.getX()) < 150) {
            velocity.add(new Vector2d(.001, 0));
            if (position.getX() < 0) {
                velocity.setX(Math.abs(velocity.getX()));
            }
        }
        if ((ARENA_HEIGHT - position.getY()) < 150) {
            velocity.add(new Vector2d(0, -.001));
            if ((ARENA_HEIGHT - position.getY()) < 0) {
                velocity.setY(-Math.abs(velocity.getY()));
            }
        }
        if ((position.getY()) < 150) {
            velocity.add(new Vector2d(0, .001));
            if (position.getY() < 0) {
                velocity.setY(Math.abs(velocity.getY()));
            }
        }

        velocity.capMag(velMag);
        position.add(velocity);

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.RED);
        Vector2d axis1 = velocity.copy();
        axis1.normalize(10);
        Vector2d axis2 = axis1.ortho();
        int[] xPoints = { (int) (position.getX() + axis1.getX()), (int) (position.getX() + axis2.getX()),
                (int) (position.getX() - axis1.getX()), (int) (position.getX() - axis2.getX()) };
        int[] yPoints = { (int) (position.getY() + axis1.getY()), (int) (position.getY() + axis2.getY()),
                (int) (position.getY() - axis1.getY()), (int) (position.getY() - axis2.getY()) };
        g.fillPolygon(xPoints, yPoints, 4);

    }

    @Override
    public void die() {

    }

    @Override
    public boolean isDead() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Vector2d getPosition() {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public Vector2d getVelocity() {
        // TODO Auto-generated method stub
        return velocity;
    }

}