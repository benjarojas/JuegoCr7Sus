package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

import templateMethod.Bullet;
import templateMethod.Cr7PoderSiu;
import templateMethod.Proyectil;
import templateMethod.StrategyProyectil;



public class Jugador {
	   private Rectangle cristiano;
	   private Sprite spr;
	   private Texture playerImg;
	   private Texture bulletTx;
	   private Texture PoderCr7;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   private Sound Siuuuuuu;
	   private long ultimaBala;
	   private long cooldownDisparo;
	   private int puntosPotenciador;
	   
	   
	   
	   public Jugador(Texture tex, Texture txBala, Sound ss,Texture BalonOro,Sound siu) {
		   playerImg = tex;
		   sonidoHerido = ss;
		   this.bulletTx = txBala;
		   PoderCr7=BalonOro;
		   Siuuuuuu=siu;
		   ultimaBala = 0; 
		   cooldownDisparo = 250; // cooldown por defecto (tiempo entre c/ disparo)
	   }
	   
		public int getVidas() {
			return vidas;
		}
	
		public int getPuntos() {
			return puntos;
		}
		public Rectangle getArea() {
			return cristiano;
		}
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}
		
	
	   public void crear() {
		      cristiano = new Rectangle();
		      cristiano.x = 800 / 2 - 45 / 2;
		      cristiano.y = 5;
		      cristiano.width = 45;
		      cristiano.height = 64;
	   }
	   
	   public void dañar() {
		  vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  sonidoHerido.play();
	   }
	   public void curar()
	   {
		   vidas++;
	   }
	   public void setPuntosPotenciador(int puntosPoder)
	   {
		   puntosPotenciador=puntosPoder;
	   }
	   
	   public void dibujar(SpriteBatch batch, GameScreen game,AmungusNormal b) {
		   Proyectil stra;//Strategy 
		   if (!herido)  
			   batch.draw(playerImg, cristiano.x, cristiano.y);
		   else {
		       batch.draw(playerImg, cristiano.x, cristiano.y+ MathUtils.random(-5,5));
		       tiempoHerido--;
		       if (tiempoHerido<=0) herido = false;
		   }
		   if(puntosPotenciador==5)
		   {
			   b.setPuntosPoder(5);
		   }
		   
		   if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {   
			   
			   
			   //Bullet bala = new Bullet(cristiano.getX()+cristiano.getWidth()/2-3,cristiano.getY()+ cristiano.getHeight()-3,0,3,bulletTx);
			   //game.agregarBala(bala);
			   //stra.setProyectil(new Cr7PoderSiu(cristiano.getX()+cristiano.getWidth()/2-3,cristiano.getY()+ cristiano.getHeight()-3,0,3,PoderCr7));
			   
			   // verificamos que hayan pasado a lo menos x milisegundos antes de poder disparar nuevamente
			   // para evitar spameo de disparos
			   if(TimeUtils.millis() - ultimaBala >= cooldownDisparo)
			   {
				   stra=new Cr7PoderSiu();
				   stra.CrearProyectil(bulletTx, cristiano.getX()+cristiano.getWidth()/2-3, cristiano.getY()+ cristiano.getHeight()-3, 0, 3, 1);
				   //stra.setProyectil(new Bullet(cristiano.getX()+cristiano.getWidth()/2-3,cristiano.getY()+ cristiano.getHeight()-3,0,3,bulletTx));
				   game.agregarBala(stra);
				   ultimaBala = TimeUtils.millis();
			   }
			   
			   //soundBala.play();
		   }
		   //condicion nueva, si apreta z se activa poder especial si este es igual a 5
		   if ((Gdx.input.isKeyJustPressed(Input.Keys.Z) && b.getPuntosPoder()==5) || ((Gdx.input.isKeyJustPressed(Input.Keys.Z) && puntosPotenciador==5))) {  
			   //b.setPuntosPoder(5);
			   stra=new Cr7PoderSiu();
			   stra.CrearProyectil(PoderCr7, cristiano.getX()+cristiano.getWidth()/2-70, cristiano.getY()+ cristiano.getHeight()-3, 0, 3, 0);
			   //stra.setProyectil(new Cr7PoderSiu(cristiano.getX()+cristiano.getWidth()/2-70,cristiano.getY()+ cristiano.getHeight()-3,0,3,PoderCr7));
			   //stra.setProyectil(new Bullet(cristiano.getX()+cristiano.getWidth()/2-3,cristiano.getY()+ cristiano.getHeight()-3,0,3,bulletTx));
			   game.agregarBala(stra);
			   Siuuuuuu.play();
			   b.setPuntosPoder(0);//resetea el contador de poderCr7 de la clase amungusnormal 
			   puntosPotenciador=0;
			   //soundBala.play();
		   }
		   
	   } 
	   
	   
	   public void actualizarMovimiento() { 
		   // movimiento desde mouse/touchzz
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      cristiano.x = touchPos.x - 64 / 2;
			}*/
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) cristiano.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) cristiano.x += velx * Gdx.graphics.getDeltaTime();
		   // que no se salga de los bordes izq y der
		   if(cristiano.x < 0) cristiano.x = 0;
		   if(cristiano.x > 800 - 45) cristiano.x = 800 - 45;
	   }
	    
	public void destruir() {
		    playerImg.dispose();
	   }
	
   public boolean estaHerido() {
	   return herido;
   }
	   
}
