package StrategyCr7;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class StrategyProyectil {
	private Proyectil proyectil;
	
	public void setProyectil(Proyectil proyectil)
	{
		this.proyectil=proyectil;
	}
	public Proyectil tipoBala()
	{
		return proyectil;
	}

}
