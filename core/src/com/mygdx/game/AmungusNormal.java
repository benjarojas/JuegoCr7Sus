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

import StrategyCr7.Proyectil;

public class AmungusNormal extends AmongUs {
	private Array<Rectangle> posAmong;
	private long ultimoAmong;
	private int puntos=0;
	private int poderCr7;
	private Music aficion;
	private int vari=0;
	private float porcentaje=0;

	public AmungusNormal(Texture AmongUsColor,Sound cc,Music ll,Music cr7)
	{
		super(AmongUsColor,cc,ll);
		aficion=cr7;//sonido muchas gracias aficion
	}
	public void Crear()
	{
		posAmong = new Array<Rectangle>();
		CrearAmongus();
		AmongDrip.setLooping(true);	
		AmongDrip.play();
		
		
	}
	//metodo nuevo que retorna el porcentaje del poder para mostrar en pantalla
	public String getPuntosPoderPorcentaje()
	{
		
		porcentaje=((float)poderCr7/5)*100;
		String porce= (int)porcentaje + "%";
		return porce;
	}
	//metodo para saber cuando tirar poder
	public int getPuntosPoder()
	{
		return poderCr7;
	}
	public void setPuntosPoder(int poderCr7)
	{
		this.poderCr7=poderCr7;
		vari=0;
		porcentaje=0;
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

	public boolean actualizarMovimiento(Tarro tarro,ArrayList<Proyectil> balas)
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
		    	  
		    	   Proyectil balaAux = balas.get(n);
		    	   if(among.overlaps(balaAux.getArea())) {
		    		   	  puntos+=100;
				    	  posAmong.removeIndex(i);
				    	  KillAmong.play();
				    	  
				    	  //metodo nuevo interfaz amongUs
				    	  //si tiene hitbox(True) es la clase bullet
				    	  //si si hitbox retorna (false) es la clase podercr7siu por lo que la bala no se destruye
				    	  if(balaAux.hitBox())
				    	  {
				    		  balas.remove(n);
				    		  if(poderCr7<5) {
				    			  poderCr7++;}

				    		  
				    	  }
				    	  //destruccion de la bala cuando sale de rango
				    	  if(balaAux.posY()>300 && !balaAux.hitBox()) 
				    	  {
				    		  if(poderCr7<5) {
				    			  poderCr7++;}
				    		  
				    		  balas.remove(n);
				    	  }
				    	  //si la cantidad de poder es igual a 5 suena muchas gracias aficion
				    	  //valor de poderCr7 se modifica a 0 cuando se dispara
				    	  if (poderCr7==5 && vari==0)
			    		  {
			    			  aficion.play();
			    			  vari++;
			    		  }
				    	  break;
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