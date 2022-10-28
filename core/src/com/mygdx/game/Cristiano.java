package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;


public interface Cristiano {
	public int getVidas();
	public int getPuntos();
	public Rectangle getArea();
    public void sumarPuntos(int pp);
	public void crear();
	public void dañar();
	public void dibujar(SpriteBatch batch, GameScreen game);
	public void actualizarMovimiento();
	public void destruir();
    public boolean estaHerido();	   
}
