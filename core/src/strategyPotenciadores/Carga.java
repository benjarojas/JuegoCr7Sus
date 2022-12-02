package strategyPotenciadores;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.game.Jugador;

import templateMethod.Proyectil;

public class Carga implements Potenciador {
	private Array<Rectangle> posCarga;
	private long ultimaCarga;
	private Texture texturaCarga;
	
	public Carga(Texture texturaPotenciador)
	{
		texturaCarga=texturaPotenciador;
	}

	@Override
	public void CrearPotenciador() {
		int x,y;
		Rectangle newCarga = new Rectangle();
		x=MathUtils.random(0, 800-80);
		y=480;
		newCarga.x=x;
		newCarga.y=y;
		newCarga.height=105;
		newCarga.width=80;
		ultimaCarga=TimeUtils.millis();	
		posCarga.add(newCarga);
		
		
	}

	@Override
	public void Crear() {
		posCarga = new Array<Rectangle>();
		CrearPotenciador();
		
		
	}
	/*
	public void setPoder(AmungusNormal b)
	{
		b.setPuntosPoder(5);
	}
    */
	@Override
	public boolean actualizarMovimiento(Jugador cr7, ArrayList<Proyectil> balas) {
		
		if(TimeUtils.millis() - ultimaCarga > 50000) CrearPotenciador();
		 for (int i=0; i < posCarga.size; i++ ) {
			  Rectangle carga = posCarga.get(i);
		      carga.y -= 100 * Gdx.graphics.getDeltaTime();//velocidad de caida
		      
		      if(carga.overlaps(cr7.getArea())) {
		    	  cr7.setPuntosPotenciador(5);
		    	  posCarga.removeIndex(i);
		    	  
		      }
		      
		    	  
		      
		 }  
		return true;
	}

	@Override
	public void actualizarDibujoPotenciador(SpriteBatch batch) {
		 for (int i=0; i < posCarga.size; i++ ) {
			  Rectangle raindrop = posCarga.get(i);
			  
		      batch.draw(texturaCarga, raindrop.x, raindrop.y); 

		   }
		
	}

}
