package com.mygdx.game;


import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public abstract class AmongUs {
	// pal abtrac private Array<Rectangle> posAmong;
	//private Array<Integer> rainDropsType;
    // abtrac private long ultimoAmong;
    protected Texture AmongUsColor;
    protected Sound KillAmong;
    protected Music AmongDrip;
    
    public AmongUs(Texture AmongUsColor,Sound cc,Music ll)
    {
    	KillAmong=cc;
    	AmongDrip=ll;
    	this.AmongUsColor=AmongUsColor;
    }
    public abstract void CrearAmongus();
    public abstract void Crear();
    
    public void destruir() {
    	KillAmong.dispose();
    	AmongDrip.dispose();
     }
     public void pausar() {
    	AmongDrip.stop();
     }
     public void continuar() {
    	 AmongDrip.play();
     }	
     public abstract boolean actualizarMovimiento(Tarro tarro);
     public abstract void actualizarDibujoamungus(SpriteBatch batch);
     
     
  	 

}