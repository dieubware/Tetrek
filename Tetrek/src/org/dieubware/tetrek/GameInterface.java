package org.dieubware.tetrek;

import org.dieubware.tetrek.actors.MenuActor;
import org.dieubware.tetrek.listeners.MenuButtonListener;
import org.dieubware.tetrek.listeners.StageKeyListener;
import org.dieubware.tetrek.model.BlockFallGrid;
import org.dieubware.tetrek.model.TetrekGrid;
import org.dieubware.tetrek.observers.ModelObserver;
import org.dieubware.tetrek.observers.ScoreObserver;
import org.dieubware.tetrek.screen.GameScreen;

public class GameInterface {

	private GameScreen gameScreen;
	private BlockFallGrid model;
	private TimeManager timeManager; 
	
	//Observers
	private ModelObserver modelObserver;
	private ScoreObserver scoreObserver;
	
	private StageKeyListener stageListener;
	private MenuButtonListener menuListener;
	
	public void initGame(GameScreen game) {
		
		gameScreen = game;
		model = new TetrekGrid();

		game.setMenuActor(new MenuActor(game.getWidth(), game.getHeight(), model.getHighScoreManager().getScore()));
		timeManager = new TimeManager(model);
		
		game.setTimeManager(timeManager);
		 
		createObservers();
		model.addObserver(modelObserver);
		model.getScoreManager().addObserver(scoreObserver);
		
		stageListener = new StageKeyListener(model, game.getWidth(), game.getHeight());
		menuListener = new MenuButtonListener(model, game.getMenuActor());
		
		game.getStage().addListener(stageListener);
		game.getMenuActor().setListeners(menuListener);
	}
	
	
	public void createObservers() {
		modelObserver = new ModelObserver(timeManager);
		scoreObserver = new ScoreObserver(gameScreen.getHudActor());
		modelObserver.setGridActor(gameScreen.getGridActor(), gameScreen.getHudActor(), gameScreen);
	}
	
}
