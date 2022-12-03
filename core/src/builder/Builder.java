package builder;

import com.mygdx.game.GameScreen;
import com.mygdx.game.JuegoCr7;

public interface Builder {
	void setGame(JuegoCr7 game);
	void setGameScreen(GameScreen juego);
	void setMsg1(String msg1);
	void setMsg2(String msg2);
}

/*
 private final JuegoCr7 game;
	private GameScreen juego;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private OrthographicCamera camera;
 */