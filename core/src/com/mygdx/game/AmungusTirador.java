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

import templateMethod.Proyectil;

public class AmungusTirador extends AmongUs {
	private Array<Rectangle> posAmong;
	private long ultimoAmong;
	private Sound tiroBala;
	private Array<Rectangle> posBala;
	private Texture texBala;
	private Array<Rectangle> posBala2;
	private int puntos=0;

	public AmungusTirador(Texture AmongUsColor,Sound cc,Music ll,Texture bala,Sound oo)
	{
		super(AmongUsColor,cc,ll);
		tiroBala=oo;//sonido
		texBala=bala;//textura bala 
		
	}
	
	public void Crear()
	{
		posAmong = new Array<Rectangle>();
		posBala = new Array<Rectangle>();
		posBala2 = new Array<Rectangle>();
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
		int x,y;
		Rectangle newAmong = new Rectangle();
		Rectangle newbala = new Rectangle();
		Rectangle newbala2 = new Rectangle();
		x=MathUtils.random(0, 800-80);
		y=480;
		newAmong.x = x;
		newAmong.y = y;
		newbala.x=x+35;
		newbala.y=y;
		newbala.width=10;
		newbala.width=20;
		newbala2.x=x+35;
		newbala2.y=y;
		newbala2.width=10;
		newbala2.width=20;
		newAmong.width = 80;
		newAmong.height = 105;
	    posAmong.add(newAmong);
	    posBala.add(newbala);
	    posBala2.add(newbala2);
	    ultimoAmong=TimeUtils.millis();
	    
		
	}

	public boolean actualizarMovimiento(Jugador tarro,ArrayList<Proyectil> balas)
	{
		if(TimeUtils.millis() - ultimoAmong > 5000) CrearAmongus();
		for(int j=0; j < posBala2.size; j++ )
		 {
			 Rectangle bala2 = posBala2.get(j);
			 bala2.y -= 100 * Gdx.graphics.getDeltaTime();
			 if(bala2.y + 64 < 0) {
		    	  posBala2.removeIndex(j);
		      }
			 if(bala2.overlaps(tarro.getArea())) {
		    	  tarro.dañar();
		    	  puntos-=50;
		    	  if (tarro.getVidas()<=0)
		    		 return false; // si se queda sin vidas retorna falso /game over
		    	  posBala2.removeIndex(j);
		    	  
		      }
		
		 }
		 for(int k=0; k < posBala.size; k++ )
		 {
			 Rectangle bala1 = posBala.get(k);
			 bala1.y -= 70 * Gdx.graphics.getDeltaTime();
			 if(bala1.y + 64 < 0) {
		    	  posBala.removeIndex(k);
		      }
			 if(bala1.overlaps(tarro.getArea())) {
		    	  tarro.dañar();
		    	  puntos-=50;
		    	  if (tarro.getVidas()<=0)
		    		 return false; // si se queda sin vidas retorna falso /game over
		    	  posBala.removeIndex(k);
		    	  
		      }
			 
		 }
		
		 for (int i=0; i < posAmong.size; i++ )
		 {
			  Rectangle among = posAmong.get(i);
			  
		      among.y -= 50 * Gdx.graphics.getDeltaTime();//velocidad de caida
		      //velocidad de caida
		      //cae al suelo y se elimina
		      if(among.y + 64 < 0) {
		    	  tarro.dañar();
		    	  puntos-=50;
		    	  if (tarro.getVidas()<=0)
			    		 return false;
		    	  posAmong.removeIndex(i); 
		    	  continue;
		      }
		      
		      
		   
		      if(among.overlaps(tarro.getArea())) {
		    	  tarro.dañar();
		    	  puntos-=50;
		    	  if (tarro.getVidas()<=0)
		    		 return false; // si se queda sin vidas retorna falso /game over
		    	  posAmong.removeIndex(i);
		    	  continue;
  
		      }
		      for(int n=0;n<balas.size();n++)
		      {
		    	  
		    	   Proyectil balaAux = balas.get(n);
		    	   if(among.overlaps(balaAux.getArea())) {
		    		   	  puntos+=300;	
				    	  posAmong.removeIndex(i);
				    	  KillAmong.play();
				    	  if(balaAux.hitBox())
				    	  {
				    		  balas.remove(n);
				    		  
				    	  }
				    	  if(balaAux.posY()>300 && !balaAux.hitBox()) {
				    		  balas.remove(n);}
				    	  
				    	  break;
		    	   }
		      }
		 }
		return true;
	}
	
	public void actualizarDibujoamungus(SpriteBatch batch) {
		
		 for (int i=0; i < posBala.size; i++ ) {
			  Rectangle raindrop = posBala.get(i);
			  
		      //batch.draw(AmongUsColor, raindrop.x, raindrop.y);
		      batch.draw(texBala, raindrop.x, raindrop.y); 
		      

		   }
		  for (int i=0; i < posBala2.size; i++ ) {
			  Rectangle raindrop = posBala2.get(i);
			  
		      //batch.draw(AmongUsColor, raindrop.x, raindrop.y);
		      batch.draw(texBala, raindrop.x, raindrop.y); 
		      

		   }
		
		  for (int i=0; i < posAmong.size; i++ ) {
			  Rectangle raindrop = posAmong.get(i);
			  
		      batch.draw(AmongUsColor, raindrop.x, raindrop.y);
		      //batch.draw(texBala, raindrop.x, raindrop.y); 
 
		   }
		 


		 
		
		
		
	}

}
