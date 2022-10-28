package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public class Tarro implements Cristiano{
	   private Rectangle bucket;
	   private Sprite spr;
	   private Texture bucketImage;
	   private Texture bulletTx;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int puntos = 0;
	   private int velx = 400;
	   private boolean herido = false;
	   private int tiempoHeridoMax=50;
	   private int tiempoHerido;
	   
	   
	   public Tarro(Texture tex, Texture txBala, Sound ss) {
		   bucketImage = tex;
		   sonidoHerido = ss;
		   this.bulletTx = txBala;
	   }
	   
		public int getVidas() {
			return vidas;
		}
	
		public int getPuntos() {
			return puntos;
		}
		public Rectangle getArea() {
			return bucket;
		}
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}
		
	
	   public void crear() {
		      bucket = new Rectangle();
		      bucket.x = 800 / 2 - 45 / 2;
		      bucket.y = 5;
		      bucket.width = 45;
		      bucket.height = 64;
	   }
	   public void dañar() {
		  vidas--;
		  herido = true;
		  tiempoHerido=tiempoHeridoMax;
		  sonidoHerido.play();
	   }
	   public void dibujar(SpriteBatch batch, GameScreen game) {
		   if (!herido)  
			   batch.draw(bucketImage, bucket.x, bucket.y);
		   else {
		       batch.draw(bucketImage, bucket.x, bucket.y+ MathUtils.random(-5,5));
		       tiempoHerido--;
		       if (tiempoHerido<=0) herido = false;
		   }
		   if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {         
			   Bullet bala = new Bullet(bucket.getX()+bucket.getWidth()/2-3,bucket.getY()+ bucket.getHeight()-3,0,3,bulletTx);
			   game.agregarBala(bala);
			   //soundBala.play();
		   }
	   } 
	   
	   
	   public void actualizarMovimiento() { 
		   // movimiento desde mouse/touch
		   /*if(Gdx.input.isTouched()) {
			      Vector3 touchPos = new Vector3();
			      touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			      camera.unproject(touchPos);
			      bucket.x = touchPos.x - 64 / 2;
			}*/
		   //movimiento desde teclado
		   if(Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= velx * Gdx.graphics.getDeltaTime();
		   if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += velx * Gdx.graphics.getDeltaTime();
		   // que no se salga de los bordes izq y der
		   if(bucket.x < 0) bucket.x = 0;
		   if(bucket.x > 800 - 45) bucket.x = 800 - 45;
	   }
	    
	   
	

	public void destruir() {
		    bucketImage.dispose();
	   }
	
   public boolean estaHerido() {
	   return herido;
   }
	   
}
