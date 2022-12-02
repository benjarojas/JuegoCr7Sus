package StrategyCr7;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bullet implements Proyectil{

	private int xSpeed;
	private int ySpeed;
	private boolean destroyed = false;
	private Sprite spr;
	private boolean hit=true;
	    
	    public Bullet(float x, float y, int xSpeed, int ySpeed, Texture tx) {
	    	spr = new Sprite(tx);
	    	spr.setPosition(x, y);
	        this.xSpeed = xSpeed;
	        this.ySpeed = ySpeed;
	    }
	    @Override
	    public void update() {
	        spr.setPosition(spr.getX()+xSpeed, spr.getY()+ySpeed);
	        if (spr.getX() < 0 || spr.getX()+spr.getWidth() > Gdx.graphics.getWidth()) {
	            destroyed = true;
	        }
	        if (spr.getY() < 0 || spr.getY()+spr.getHeight() > Gdx.graphics.getHeight()) {
	        	destroyed = true;
	        }
	        
	    }
	    @Override
	    public Rectangle getArea() {
	    	return spr.getBoundingRectangle();
	    }
	    @Override
	    public void draw(SpriteBatch batch) {
	    	spr.draw(batch);
	    }
	    @Override
	    public boolean isDestroyed() {return destroyed;}
	    @Override
	    public boolean hitBox()
	    {
	    	return hit;
	    }
	    @Override
	    public float posY()
	    {
	    	return spr.getY();
	    }

}
