package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Tarro tarro;
	//private Lluvia lluvia;
	private AmungusNormal amungusazul;
	private AmungusTirador amungusrojo;

	private ArrayList<Bullet> balas = new ArrayList<>();
	private ArrayList<AmungusNormal> normales = new ArrayList<>();
	private ArrayList<AmungusTirador> tiradores = new ArrayList<>();
	   
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("mute.mp3"));
		  tarro = new Tarro(new Texture(Gdx.files.internal("cristiano small.png")),new Texture(Gdx.files.internal("bola cristiano.png")),hurtSound);
         
	      // load the drop sound effect and the rain background "music" 
	     Texture gota = new Texture(Gdx.files.internal("amongus blue.png"));
	     Texture gotaMala = new Texture(Gdx.files.internal("amongus tirador.png"));
         Texture bala = new Texture(Gdx.files.internal("Rocket2.png"));
         
         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("amogus kill sonido.mp3"));
         Sound balaSound = Gdx.audio.newSound(Gdx.files.internal("cr7 siuuu sound.mp3"));
        
	     Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("amongus drip.mp3"));
         //lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
         amungusazul=new AmungusNormal(gota, dropSound, rainMusic);
         amungusrojo=new AmungusTirador(gotaMala, dropSound, rainMusic,bala,balaSound);
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion del tarro
	      tarro.crear();
	      
	      // creacion de la lluvia
	      //lluvia.crear();
	      amungusrojo.Crear();
	      amungusazul.Crear();
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Amogus totales: " + tarro.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();
	        for (Bullet b : balas) {       
		          if(!b.isDestroyed())
		          {
		        	  b.update();
		          }
		    }
			// caida de la lluvia 
	       if (!amungusrojo.actualizarMovimiento(tarro, balas)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<tarro.getPuntos())
	    		  game.setHigherScore(tarro.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
	       if (!amungusazul.actualizarMovimiento(tarro)) {
		    	  //actualizar HigherScore
		    	  if (game.getHigherScore()<tarro.getPuntos())
		    		  game.setHigherScore(tarro.getPuntos());  
		    	  //ir a la ventana de finde juego y destruir la actual
		    	  game.setScreen(new GameOverScreen(game));
		    	  dispose();
		   }
		}
		
		for (Bullet b : balas) {       
	          if(!b.isDestroyed())
	          {
	        	  b.draw(batch);
	          }
	    }
		
		tarro.dibujar(batch, this);
		
		amungusrojo.actualizarDibujoamungus(batch);
		amungusazul.actualizarDibujoamungus(batch);
		
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	public boolean agregarBala(Bullet bb) {
    	return balas.add(bb);
    }
	
	@Override
	public void show() {
	  // continuar con sonido de lluvia
		amungusazul.continuar();
		amungusrojo.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		amungusazul.pausar();
		amungusrojo.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		tarro.destruir();
	      amungusazul.destruir();
	      amungusrojo.destruir();

	}

}
