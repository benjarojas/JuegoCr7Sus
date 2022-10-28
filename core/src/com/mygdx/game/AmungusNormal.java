package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class AmungusNormal extends AmongUs {
	private Array<Rectangle> posAmong;
	private long ultimoAmong;
	private int puntos=0;

	public AmungusNormal(Texture AmongUsColor,Sound cc,Music ll)
	{
		super(AmongUsColor,cc,ll);
	}
	public void Crear()
	{
		posAmong = new Array<Rectangle>();
		CrearAmongus();
		AmongDrip.setLooping(true);	
		AmongDrip.play();
		
		
	}
	public int getPuntos()
	{
		return puntos;
	}
	public void CrearAmongus()
	{
		Rectangle newAmong = new Rectangle();
		
		newAmong.x = MathUtils.random(0, 800-80);
		newAmong.y = 480;
		newAmong.width = 80;
		newAmong.height = 105;
	    posAmong.add(newAmong);
	    ultimoAmong=TimeUtils.millis();
		
	}

	public boolean actualizarMovimiento(Tarro tarro,ArrayList<Bullet> balas)
	{
		if(TimeUtils.millis() - ultimoAmong > 1000) CrearAmongus();
		
		 for (int i=0; i < posAmong.size; i++ ) {
			  Rectangle among = posAmong.get(i);
		      among.y -= 100 * Gdx.graphics.getDeltaTime();//velocidad de caida
		      //cae al suelo y se elimina
		      if(among.y + 64 < 0) {
		    	  tarro.dañar();
		    	  puntos-=50;
		    	  if (tarro.getVidas()<=0)
			    		 return false;
		    	  posAmong.removeIndex(i); 
		    	 
		      }
		      if(among.overlaps(tarro.getArea())) {
		    	  tarro.dañar();
		    	  puntos-=50;
		    	  if (tarro.getVidas()<=0)
		    		 return false; // si se queda sin vidas retorna falso /game over
		    	  posAmong.removeIndex(i);
		    	  
		      }
		      for(int n=0;n<balas.size();n++)
		      {
		    	  
		    	   Bullet balaAux = balas.get(n);
		    	   if(among.overlaps(balaAux.getArea())) {
		    		   	  puntos+=100;
				    	  posAmong.removeIndex(i);
				    	  KillAmong.play();
				    	  balas.remove(n);
		      }
		    	  
		      
		 }    
		 }     
		return true;
	}
	public void actualizarDibujoamungus(SpriteBatch batch) {
		
		
		  for (int i=0; i < posAmong.size; i++ ) {
			  Rectangle raindrop = posAmong.get(i);
			  
		      batch.draw(AmongUsColor, raindrop.x, raindrop.y); 
 
		   }
		
		
		
	}
}