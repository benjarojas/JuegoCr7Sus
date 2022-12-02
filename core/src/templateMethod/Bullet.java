package templateMethod;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;


public class Bullet extends Proyectil{

	public Bullet() {
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
	@Override
	public void setSpeedXY(int xSped,int ySped) {
		xSpeed=xSped;
		ySpeed=ySped;
	}

	@Override
	public void setSpr(Texture v,float x,float y) {
		spr = new Sprite(v);
		spr.setPosition(x, y);

	}
		
}
