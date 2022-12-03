package builder;

import com.mygdx.game.JuegoCr7;

public class Director {

	public void constructPauseScreen(Builder builder, JuegoCr7 game)
	{
		builder.setMsg1("Juego en Pausa ");
		builder.setMsg2("Toca en cualquier lado para continuar! ");
		builder.setGame(game);
	}
	
	public void constructGameOverScreen(Builder builder, JuegoCr7 game)
	{
		builder.setGame(game);
		builder.setMsg1("GAME OVER");
		builder.setMsg2("Toca en cualquier lado para reiniciar.");
	}
}
