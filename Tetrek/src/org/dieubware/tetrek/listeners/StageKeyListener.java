package org.dieubware.tetrek.listeners;

import org.dieubware.tetrek.model.TetrisGrid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class StageKeyListener extends InputListener {

	private TetrisGrid model;
	private int width, height;
	private boolean activated;

	public StageKeyListener(TetrisGrid model, int width, int height) {
		this.model = model;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		if(model.isGameRunning()){
			if(keycode == Input.Keys.RIGHT)
				model.setMoveRight = true;
			else if(keycode == Input.Keys.LEFT)
				model.setMoveLeft = true;
			else if(keycode == Input.Keys.SPACE)
				model.directFall();
			else if(keycode == Input.Keys.UP)
				model.rotate(1);
			else if(keycode == Input.Keys.DOWN)
				model.setFall = true;
			else if(keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK) {
				model.setPause(true);
			}
			model.act();
		}
		else {
			if(keycode == Input.Keys.ESCAPE || keycode == Input.Keys.BACK) {
				if(!model.isStarted())
					Gdx.app.exit();
				else
					model.setPause(false);
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		//if(model.isGameRunning()){
			if(keycode == Input.Keys.RIGHT)
				model.setMoveRight = false;
			else if(keycode == Input.Keys.LEFT)
				model.setMoveLeft = false;
			else if(keycode == Input.Keys.DOWN)
				model.setFall = false;
			model.setMoveFirst(false);
		//}
		return false;
	}


	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		System.out.println("clicked on : " + event.getListenerActor());
		if(model.isGameRunning()){
			if(y > (2*height)/3) {
				if(x < width/2)
					model.rotate(-1);
				else
					model.rotate(1);
			}
			else if(y < height/6)
				model.setFall = true;
			else if(x < width/2)
				model.setMoveLeft = true;
			else
				model.setMoveRight = true;
			model.act();
		}
		return false;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		if(model.isStarted()){
			if(y < height/6)
				model.setFall = false;
			else if(x > width/2)
				model.setMoveRight = false;
			else 
				model.setMoveLeft = false;
	
			model.setMoveFirst(false);
		}
	}

	public boolean isActivated() {
		return activated;
	}

	public void setActivated(boolean activated) {
		this.activated = activated;
	}







}
