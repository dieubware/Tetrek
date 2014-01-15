package org.dieubware.tetrek.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class MenuActor extends Actor {

	private ShapeRenderer shapeRenderer;
	private Button resume, newGame, settings;
	
	public MenuActor(int width, int height) {
		this.setWidth(width);
		this.setHeight(height);
		shapeRenderer = new ShapeRenderer();
		Drawable resumeDrawable = new SpriteDrawable(new Sprite(new Texture("resume.png")));
		Drawable newGameDrawable = new SpriteDrawable(new Sprite(new Texture("newgame.png")));
		Drawable settingsDrawable = new SpriteDrawable(new Sprite(new Texture("settings.png")));
		
		resume = new Button(resumeDrawable);
		newGame = new Button(newGameDrawable);
		settings = new Button(settingsDrawable);
		int i = 0;
		resume.setPosition(width/2-resume.getWidth()/2, height/2 - i*resume.getHeight());
		i++;
		newGame.setPosition(width/2-newGame.getWidth()/2, height/2 - i*newGame.getHeight());
		i++;
		settings.setPosition(width/2-settings.getWidth()/2, height/2 - i*settings.getHeight());
		
	
	}
	

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);
		//batch.enableBlending();
		resume.draw(batch, parentAlpha);
		newGame.draw(batch, parentAlpha);
		settings.draw(batch, parentAlpha);
		


	}
	
}
