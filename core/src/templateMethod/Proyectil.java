package templateMethod;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public abstract class Proyectil {
	protected int xSpeed;
	protected int ySpeed;
	protected boolean destroyed = false;
	protected Sprite spr;
	protected boolean hit;
	
	public void CrearProyectil(Texture v,float x,float y,int xSped,int ySped,int a)//template method
	{
		//metodos abstractos
		setSpeedXY(xSped,ySped);
		setSpr(v,x,y);
		
		//metodo propio
		setHitbox(a);
		
	}
	
	public void update()
	{
		spr.setPosition(spr.getX()+xSpeed, spr.getY()+ySpeed);
        if (spr.getX() < 0 || spr.getX()+spr.getWidth() > Gdx.graphics.getWidth()) {
            destroyed = true;
        }
        if (spr.getY() < 0 || spr.getY()+spr.getHeight() > Gdx.graphics.getHeight()) {
        	destroyed = true;
        }
		
	}
	public abstract void setSpeedXY(int xSped,int ySped);

	public abstract void setSpr(Texture v,float x,float y);
	public Rectangle getArea()
	{
		return spr.getBoundingRectangle();
	}
	
	public void draw(SpriteBatch batch)
	{
		spr.draw(batch);
	}
	//ve si el proyectil tiene hitbox
	public void setHitbox(int a)
	{
		if(a==0)
		{
			hit=false;
		}
		else
		{
			hit=true;
		}
	}
	public abstract boolean isDestroyed();//verifica si esta destruido
	public abstract boolean hitBox();//retirna la hitbox que tiene el proyectil
	//retorna posicion de y para eliminar proyectil
	public float posY()
	{
		return spr.getY();
	}
	}

