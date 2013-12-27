package org.dieubware.tetrek.listeners;

import org.dieubware.tetrek.model.TetrisGrid;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class StageKeyListener extends InputListener {

	private TetrisGrid model;
	private int width, height;
	
	public StageKeyListener(TetrisGrid model, int width, int height) {
		this.model = model;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		if(keycode == Input.Keys.RIGHT)
			model.setMoveRight = true;
		else if(keycode == Input.Keys.LEFT)
			model.setMoveLeft = true;
		else if(keycode == Input.Keys.SPACE)
			model.directFall();
		else if(keycode == Input.Keys.UP)
			model.rotate();
		else if(keycode == Input.Keys.DOWN)
			model.setFall = true;
		model.act();
		return true;
	}
	
	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		if(keycode == Input.Keys.RIGHT)
			model.setMoveRight = false;
		else if(keycode == Input.Keys.LEFT)
			model.setMoveLeft = false;
		else if(keycode == Input.Keys.DOWN)
			model.setFall = false;
		model.setMoveFirst(false);
		return true;
	}
	

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		if(y > (2*height)/3)
			model.rotate();
		else if(y < height/6)
			model.setFall = true;
		else if(x < width/2)
			model.setMoveLeft = true;
		else
			model.setMoveRight = true;
		model.act();
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		if(y < height/6)
			model.setFall = false;
		else if(x > width/2)
			model.setMoveRight = false;
		else 
			model.setMoveLeft = false;
		
		model.setMoveFirst(false);
	}
	
	

	

	

}
