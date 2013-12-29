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
	
	@Override
	public void create() {
		//UnitGenerator.generate();
		TetrisGrid model = new TetrisGrid();

		game = new GameScreen(this);
		game.setTimeManager(new TimeManager(model));
		ModelObserver mObs = new ModelObserver();
		ScoreObserver sObs = new ScoreObserver(game.getHudActor());
		mObs.setGridActor(game.getGridActor(), game.getHudActor());
		model.addObserver(mObs);
		model.getScoreManager().addObserver(sObs);
		setScreen(game);
		game.getStage().addListener(new StageKeyListener(model, game.getWidth(), game.getHeight()));
		model.startGame();
		
	}

	
	
	
	public void exit() {
		Gdx.app.exit();
	}

	

	
	
}
