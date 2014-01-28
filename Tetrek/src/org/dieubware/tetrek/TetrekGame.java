package org.dieubware.tetrek;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Scaling;

import org.dieubware.tetrek.listeners.StageKeyListener;
import org.dieubware.tetrek.model.TetrisGrid;
import org.dieubware.tetrek.observers.ModelObserver;
import org.dieubware.tetrek.observers.ScoreObserver;
import org.dieubware.tetrek.screen.GameScreen;


public class TetrekGame extends Game {

	private GameScreen game;
	//private TitleScreen title;
	//private MapSelectionScreen mapSelection;
	private GameInterface gameInterface;
	
	public TetrekGame() {
		gameInterface = new GameInterface();
	}
	
	@Override
	public void create() {
		//UnitGenerator.generate();
		Gdx.input.setCatchBackKey(true);
		game = new GameScreen(this);
		gameInterface.initGame(game);
		//model.startGame();
		setScreen(game);
		
	}

	
	
	
	public void exit() {
		Gdx.app.exit();
	}

	

	
	
}
