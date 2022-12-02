package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public abstract class Pantalla {
	
	private SpriteBatch batch;
	private OrthographicCamera camera;
	
	Pantalla(){}
	
	public void render(float delta) {
		ScreenUtils.clear(0,0,1.0f,0.5f);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.end();
		
	}
	
	abstract void show();
	abstract void pause();
	abstract void resume();
	abstract void dispose();

}