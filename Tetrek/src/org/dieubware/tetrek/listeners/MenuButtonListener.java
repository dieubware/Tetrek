package org.dieubware.tetrek.listeners;

import org.dieubware.tetrek.actors.MenuActor;
import org.dieubware.tetrek.model.BlockFallGrid;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class MenuButtonListener extends ClickListener {

	private BlockFallGrid model;
	private MenuActor actor;
	
	public MenuButtonListener(BlockFallGrid model, MenuActor actor) {
		super();
		this.model = model;
		this.actor = actor;
	}

	@Override
	public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
		
		event.handle();
		return true;
	}

	@Override
	public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
		System.out.println("clicked");
		Button b = (Button)event.getTarget();
		if(b == actor.getNewGame()) {
			model.startGame();
		}
		else if(b == actor.getResume()) {
			model.setPause(false);
		}
		else if(b == actor.getSettings()) {
			actor.setVisible(false);
		}
	}
	
	@Override
	public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
		// TODO Auto-generated method stub
		//super.enter(event, x, y, pointer, fromActor);
		System.out.println("ENTER BUTTON");
	}

	@Override
	public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
		// TODO Auto-generated method stub
		//super.exit(event, x, y, pointer, toActor);
		System.out.println("EXIT BUTTON");
	}
	
}
