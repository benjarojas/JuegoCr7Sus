package builder;

import com.mygdx.game.GameScreen;
import com.mygdx.game.JuegoCr7;

public class ScreenBuilder implements Builder{

	private JuegoCr7 game;
	private GameScreen juego;
	private String msg1;
	private String msg2;
	
	@Override
	public void setGame(JuegoCr7 game) {
		this.game = game;
	}

	@Override
	public void setGameScreen(GameScreen juego) {
		this.juego = juego;
	}

	@Override
	public void setMsg1(String msg1)
	{
		this.msg1 = msg1;
	}
	
	public void setMsg2(String msg2)
	{
		this.msg2 = msg2;
	}
	
	public GeneralScreen getResult() {
        return new GeneralScreen(game, juego, msg1, msg2);
    }

}
