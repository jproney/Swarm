package SwarmPackage;

import java.awt.Graphics;
import java.util.ArrayList;

public interface Sprite {

    public void update(ArrayList<Sprite> orgs);

    public void draw(Graphics g);

    public void die();

    public boolean isDead();

    public Vector2d getPosition();

    public Vector2d getVelocity();

}