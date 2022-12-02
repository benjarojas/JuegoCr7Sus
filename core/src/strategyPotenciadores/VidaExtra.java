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

public class VidaExtra implements Potenciador{
	private Array<Rectangle> posVida;
	private long ultimaVida;
	private Texture texturaVida;
	
	public VidaExtra(Texture texturaPotenciador)
	{
		texturaVida=texturaPotenciador;
	}

	@Override
	public void CrearPotenciador() {
		int x,y;
		Rectangle newVida = new Rectangle();
		x=MathUtils.random(0, 800-80);
		y=480;
		newVida.x=x;
		newVida.y=y;
		newVida.height=105;
		newVida.width=80;
		ultimaVida=TimeUtils.millis();
		posVida.add(newVida);
		
	}

	@Override
	public void Crear() {
		posVida = new Array<Rectangle>();
		CrearPotenciador();
		
		
	}

	@Override
	public boolean actualizarMovimiento(Jugador tarro, ArrayList<Proyectil> balas) {
		
	
		if(TimeUtils.millis() - ultimaVida > 50000) CrearPotenciador();
		 for (int i=0; i < posVida.size; i++ ) {
			  Rectangle vida = posVida.get(i);
		      vida.y -= 100 * Gdx.graphics.getDeltaTime();//velocidad de caida
		      
		      if(vida.overlaps(tarro.getArea())) {
		    	  tarro.curar();
		    	  posVida.removeIndex(i);
		    	  
		      }
		      
		    	  
		      
		 }    
		      
		return true;
	}

	@Override
	public void actualizarDibujoPotenciador(SpriteBatch batch) {
		
		 for (int i=0; i < posVida.size; i++ ) {
			  Rectangle raindrop = posVida.get(i);
			  
		      batch.draw(texturaVida, raindrop.x, raindrop.y); 

		   }
	
		
	}
	

}
