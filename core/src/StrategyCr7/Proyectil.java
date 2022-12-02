package StrategyCr7;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public interface Proyectil {
	public void update();
	public Rectangle getArea();
	public void draw(SpriteBatch batch);
	public boolean isDestroyed();
	public boolean hitBox();
	public float posY();

}
