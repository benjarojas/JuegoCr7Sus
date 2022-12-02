package strategyPotenciadores;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Jugador;

import templateMethod.Proyectil;

public class StrategyPotenciador {
	private Potenciador potenciador;
	public void setPotenciador(Potenciador potenciador)
	{
		this.potenciador=potenciador;
	}
    public  void CrearPotenciador()
    {
    	potenciador.CrearPotenciador();
    }
    public  void Crear()
    {
    	potenciador.Crear();
    }
    public  boolean actualizarMovimiento(Jugador tarro,ArrayList<Proyectil> balas) {
    	return  potenciador.actualizarMovimiento(tarro, balas);
    }
    public  void actualizarDibujoPotenciador(SpriteBatch batch) {
    	potenciador.actualizarDibujoPotenciador(batch);
    	
    }
	
	

}
