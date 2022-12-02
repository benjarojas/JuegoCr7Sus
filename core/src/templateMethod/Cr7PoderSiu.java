package templateMethod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Cr7PoderSiu extends Proyectil{
	    
	    public Cr7PoderSiu() {	}
	    
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
		@Override
		public void setSpeedXY(int xSped, int ySped) {
			xSpeed = xSped;
	        ySpeed = ySped;
			// TODO Auto-generated method stub
			
		}
		@Override
		public void setSpr(Texture v, float x, float y) {
			spr = new Sprite(v);
	    	spr.setPosition(x, y);
			// TODO Auto-generated method stub
			
		}
	    
	

}
