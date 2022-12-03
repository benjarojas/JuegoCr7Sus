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

import builder.Director;
import builder.GeneralScreen;
import builder.ScreenBuilder;
import strategyPotenciadores.Carga;
import strategyPotenciadores.Potenciador;
import strategyPotenciadores.StrategyPotenciador;
import strategyPotenciadores.VidaExtra;
import templateMethod.Proyectil;

public class GameScreen extends Pantalla implements Screen {
	final JuegoCr7 game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Jugador tarro;
	private AmungusNormal amungusazul;
	private AmungusTirador amungusrojo;
	private StrategyPotenciador potenciador;
	private ArrayList<Proyectil> balas = new ArrayList<>();
	private Director director;
	private ScreenBuilder builder;
	
	public GameScreen(final JuegoCr7 game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        
        director = new Director();
		builder = new ScreenBuilder();
        
		  // load the images for player and its bullets, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("mute.mp3"));
		  Sound siu = Gdx.audio.newSound(Gdx.files.internal("cr7 siuuu sound.mp3"));//sonido nuevo siu
		  tarro = new Jugador(new Texture(Gdx.files.internal("cristiano small.png")),new Texture(Gdx.files.internal("bola cristiano.png")),hurtSound,new Texture(Gdx.files.internal("balonOroSinBordes.png")),siu);
         
	      // load sound effects and the background musicm 
	     Texture gota = new Texture(Gdx.files.internal("amongus blue.png"));
	     Texture gotaMala = new Texture(Gdx.files.internal("amongus tirador.png"));
         Texture bala = new Texture(Gdx.files.internal("Rocket2.png"));
         Texture vida = new Texture(Gdx.files.internal("corazon.png"));
         Texture Carga = new Texture(Gdx.files.internal("png-transparent-ballon-d-or-2017-ballon-d-or-2016-2018-world-cup-2014-fifa-ballon-d-or-2015-fifa-ballon-d-or-football-removebg-preview (1).png"));
         
         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("amogus kill sonido.mp3"));
         Sound balaSound = Gdx.audio.newSound(Gdx.files.internal("cr7 siuuu sound.mp3"));
        
	     Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("amongus drip.mp3"));
	     Music muchasGraciasAficion = Gdx.audio.newMusic(Gdx.files.internal("cr7 gracias sound.mp3"));
      
         amungusazul=new AmungusNormal(gota, dropSound, rainMusic,muchasGraciasAficion);
         amungusrojo=new AmungusTirador(gotaMala, dropSound, rainMusic,bala,balaSound);
         potenciador=new StrategyPotenciador();
         
         int n=(int) (Math.random()*2+1);
         if(n==1)
         {
        	potenciador.setPotenciador(new VidaExtra(vida));
   	      	potenciador.Crear();
        	 
         }
         
         else
         {
        	potenciador.setPotenciador(new Carga(Carga));
   	      	potenciador.Crear();
        	 
         }
	      
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      batch = new SpriteBatch();
	      // creacion de CR7
	      tarro.crear();
	      
	      
	      // creacion enemigos among us
	      
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
		font.draw(batch, "AmongPuntos: " + (amungusrojo.getPuntos()+amungusazul.getPuntos()), 5, 475);
		font.draw(batch, "Vidas : " + tarro.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		font.draw(batch, "Carga del poder : " + (amungusazul.getPuntosPoderPorcentaje()), 2, 30);//muestra porcentaje del poder en pantalla
		
		if (!tarro.estaHerido()) {
			// movimiento del tarro desde teclado
	        tarro.actualizarMovimiento();
	        for (Proyectil b : balas) {       
		          if(!b.isDestroyed())
		          {
		        	  b.update();
		          }
		    }
			// caida de amongus
	       if (!amungusrojo.actualizarMovimiento(tarro,balas)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<amungusrojo.getPuntos())
	    		  game.setHigherScore(amungusrojo.getPuntos()+amungusazul.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  director.constructGameOverScreen(builder, game);
	  		  GeneralScreen pantallaGameOver = this.builder.getResult();	
	    	  game.setScreen(pantallaGameOver);
	    	  dispose();
	       } 
	       if (!amungusazul.actualizarMovimiento(tarro,balas)) {
		    	  //actualizar HigherScore
		    	  
		    	  //ir a la ventana de finde juego y destruir la actual
	    	   	director.constructGameOverScreen(builder, game);
		  		  GeneralScreen pantallaGameOver = this.builder.getResult();	
		    	  game.setScreen(pantallaGameOver);
		    	  dispose();
		   }
	       if (!potenciador.actualizarMovimiento(tarro,balas)) {
		    	  //actualizar HigherScore
		    	  
		    	  //ir a la ventana de finde juego y destruir la actual
	    	   	  director.constructGameOverScreen(builder, game);
		  		  GeneralScreen pantallaGameOver = this.builder.getResult();	
		    	  game.setScreen(pantallaGameOver);
		    	  dispose();
		   }
		}
		
		for (Proyectil b : balas) { //cambio de bullet a interfaz Proyectil   
	          if(!b.isDestroyed())
	          {
	        	  b.draw(batch);
	          }
	    }
		
		tarro.dibujar(batch, this,amungusazul);//instancia nueva amungusazul
		
		amungusrojo.actualizarDibujoamungus(batch);
		amungusazul.actualizarDibujoamungus(batch);
		potenciador.actualizarDibujoPotenciador(batch);
		
		
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	public boolean agregarBala(Proyectil bb) {//cambio de clase bullet a interface Proyectil
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
		director.constructPauseScreen(builder, game);
		GeneralScreen pantallaPausa = builder.getResult();	
		game.setScreen(pantallaPausa); 
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
