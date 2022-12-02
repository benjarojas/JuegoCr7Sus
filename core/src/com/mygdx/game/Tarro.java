package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;

import StrategyCr7.Bullet;
import StrategyCr7.Cr7PoderSiu;
import StrategyCr7.StrategyProyectil;


public class Tarro implements Cristiano{
	   private Rectangle bucket;
	   private Sprite spr;
	   private Texture bucketImage;
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
	   
	   
	   
	   public Tarro(Texture tex, Texture txBala, Sound ss,Texture BalonOro,Sound siu) {
		   bucketImage = tex;
		   sonidoHerido = ss;
		   this.bulletTx = txBala;
		   PoderCr7=BalonOro;
		   Siuuuuuu=siu;
		   
		   
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
	   public void dibujar(SpriteBatch batch, GameScreen game,AmungusNormal b) {
		   StrategyProyectil stra= new StrategyProyectil();//Strategy 
		   if (!herido)  
			   batch.draw(bucketImage, bucket.x, bucket.y);
		   else {
		       batch.draw(bucketImage, bucket.x, bucket.y+ MathUtils.random(-5,5));
		       tiempoHerido--;
		       if (tiempoHerido<=0) herido = false;
		   }
		   if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {   
			   
			   //Bullet bala = new Bullet(bucket.getX()+bucket.getWidth()/2-3,bucket.getY()+ bucket.getHeight()-3,0,3,bulletTx);
			   //game.agregarBala(bala);
			   //stra.setProyectil(new Cr7PoderSiu(bucket.getX()+bucket.getWidth()/2-3,bucket.getY()+ bucket.getHeight()-3,0,3,PoderCr7));
			   stra.setProyectil(new Bullet(bucket.getX()+bucket.getWidth()/2-3,bucket.getY()+ bucket.getHeight()-3,0,3,bulletTx));
			   game.agregarBala(stra.tipoBala());
			   
			   //soundBala.play();
		   }
		   //condicion nueva, si apreta z se activa poder especial si este es igual a 5
		   if (Gdx.input.isKeyJustPressed(Input.Keys.Z) && b.getPuntosPoder()==5) {  
			   
			   stra.setProyectil(new Cr7PoderSiu(bucket.getX()+bucket.getWidth()/2-45,bucket.getY()+ bucket.getHeight()-3,0,3,PoderCr7));
			   //stra.setProyectil(new Bullet(bucket.getX()+bucket.getWidth()/2-3,bucket.getY()+ bucket.getHeight()-3,0,3,bulletTx));
			   game.agregarBala(stra.tipoBala());
			   Siuuuuuu.play();
			   b.setPuntosPoder(0);//resetea el contador de poderCr7 de la clase amungusnormal 
			   //soundBala.play();
		   }
	   } 
	   
	   
	   public void actualizarMovimiento() { 
		   // movimiento desde mouse/touchzz
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
