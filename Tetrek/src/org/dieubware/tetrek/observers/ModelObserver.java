package org.dieubware.tetrek.observers;

import java.util.Observable;
import java.util.Observer;

import org.dieubware.tetrek.TimeManager;
import org.dieubware.tetrek.actors.GridActor;
import org.dieubware.tetrek.actors.HUDActor;
import org.dieubware.tetrek.actors.MenuActor;
import org.dieubware.tetrek.model.TetrisGrid;
import org.dieubware.tetrek.model.TetrisPiece;
import org.dieubware.tetrek.screen.GameScreen;
import org.dieubware.jbrik.Grid;
import org.dieubware.jbrik.Point;
import org.dieubware.jbrik.ScoreManager;

public class ModelObserver implements Observer {

	GridActor gridActor;
	HUDActor hudActor;
	GameScreen gameScreen;
	TimeManager timeManager;
	private int level;
	public ModelObserver(TimeManager tm) {
		timeManager = tm;
		
	}
	
	public void setGridActor(GridActor gridActor, HUDActor hudActor, GameScreen gameScreen) {
		this.gridActor = gridActor;
		this.hudActor = hudActor;
		this.gameScreen = gameScreen;
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		Grid grid = (Grid)o;
		gridActor.setGrid(grid.getArray());
		if(((TetrisGrid)grid).isGameRunning() != gameScreen.isGameRunning()) {
			
			gameScreen.setGameRunning(((TetrisGrid)grid).isGameRunning());
			gameScreen.getMenuActor().setVisible(!gameScreen.isGameRunning());
			if(((TetrisGrid)grid).isStarted()) {
				gameScreen.getMenuActor().setCurrentState(MenuActor.State.PAUSE);
			}
			else {
				System.out.println("Is lost ? " + ((TetrisGrid)grid).isLost());
				if(((TetrisGrid)grid).isLost()) {
					ScoreManager sm = ((TetrisGrid)grid).getScoreManager();
					gameScreen.getMenuActor().setScore(sm.getScore(), sm.getOtherScore("level"), sm.getOtherScore("lines"), ((TetrisGrid)grid).getHighScoreManager().getScore());
					gameScreen.getMenuActor().setCurrentState(MenuActor.State.LOST);
				}
				else
					gameScreen.getMenuActor().setCurrentState(MenuActor.State.NEW_GAME);
				
			}
		}
		if(((TetrisGrid)grid).isPieceChanged()) {
			
			int[] intGrid = new int[16];
			TetrisPiece piece = new TetrisPiece();
			piece.initPiece(((TetrisGrid)grid).getNextPieces().get(0), 1, 4);
			for(Point p : piece.points) {
				intGrid[p.y*4 + p.x] = piece.getPieceColor();
			}
			hudActor.setGrid(intGrid);
			((TetrisGrid)grid).setPieceChanged(false);
			level = ((TetrisGrid)grid).getLevel();
			if(level < 10) {
				timeManager.setLevel(level);
			}
		}
		
	}

}
