package org.dieubware.tetrek.actors;


import java.awt.Event;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class MenuActor extends Table {

	private ShapeRenderer shapeRenderer;
	private Button resume, newGame, settings;
	private Image lost;
	public enum State {NEW_GAME, LOST, PAUSE}
	


	public MenuActor(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
		shapeRenderer = new ShapeRenderer();
		Drawable resumeDrawable = new SpriteDrawable(new Sprite(new Texture("resume.png")));
		Drawable newGameDrawable = new SpriteDrawable(new Sprite(new Texture("newgame.png")));
		Drawable settingsDrawable = new SpriteDrawable(new Sprite(new Texture("settings.png")));
		Drawable lostDrawable = new SpriteDrawable(new Sprite(new Texture("youlost.png")));

		
		resume = new Button(resumeDrawable);
		newGame = new Button(newGameDrawable);
		settings = new Button(settingsDrawable);
		lost = new Image(lostDrawable);
		
		int i = 0;
		resume.setPosition(width/2-resume.getWidth()/2, height/2 - i*resume.getHeight());
		i++;
		newGame.setPosition(width/2-newGame.getWidth()/2, height/2 - i*newGame.getHeight());
		i++;
		settings.setPosition(width/2-settings.getWidth()/2, height/2 - i*settings.getHeight());
		this.add(lost);
		row();
		this.add(resume);
		row();
		this.add(newGame);
		row();
		this.add(settings);
		setCurrentState(State.NEW_GAME);
	}
	

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		
	}
	
	


	public void setListeners(ClickListener listener) {
		System.out.println("Listeners set");
		resume.addListener(listener);
		newGame.addListener(listener);
		settings.addListener(listener);
		
	}
	
	public Button getResume() {
		return resume;
	}


	public Button getNewGame() {
		return newGame;
	}


	public Button getSettings() {
		return settings;
	}

	public void setCurrentState(State currentState) {
		if(currentState == State.PAUSE) {
			resume.setVisible(true);
			newGame.setVisible(true);
			settings.setVisible(true);
			lost.setVisible(false);
		}
		else {
			resume.setVisible(false);
			newGame.setVisible(true);
			settings.setVisible(true);
			if(currentState == State.LOST) {
				lost.setVisible(true);
			}
			else {
				lost.setVisible(false);
			}
		}
	}


	
}
