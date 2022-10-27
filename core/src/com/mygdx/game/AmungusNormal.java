package com.mygdx.game;

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

	public boolean actualizarMovimiento(Tarro tarro)
	{
		if(TimeUtils.millis() - ultimoAmong > 10000) CrearAmongus();
		
		 for (int i=0; i < posAmong.size; i++ ) {
			  Rectangle among = posAmong.get(i);
		      among.y -= 100 * Gdx.graphics.getDeltaTime();//velocidad de caida
		      //cae al suelo y se elimina
		      if(among.y + 64 < 0) {
		    	  //tarro.dañar();
		    	  if (tarro.getVidas()<=0)
			    		 return false;
		    	  posAmong.removeIndex(i); 
		    	 
		      }
		      if(among.overlaps(tarro.getArea())) {
		    	  tarro.dañar();
		    	  if (tarro.getVidas()<=0)
		    		 return false; // si se queda sin vidas retorna falso /game over
		    	  posAmong.removeIndex(i);
		    	  
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