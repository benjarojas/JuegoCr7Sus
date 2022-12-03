package strategyPotenciadores;

import java.util.ArrayList;



import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.Jugador;

import templateMethod.Proyectil;

public interface Potenciador {
	
	    public  void CrearPotenciador();
	    public  void Crear();
	    public  boolean actualizarMovimiento(Jugador tarro,ArrayList<Proyectil> balas);
	    public  void actualizarDibujoPotenciador(SpriteBatch batch);

}
