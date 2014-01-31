package org.dieubware.tetrek.observers;

import java.util.Observable;
import java.util.Observer;

import org.dieubware.tetrek.TimeManager;
import org.dieubware.tetrek.actors.GridActor;
import org.dieubware.tetrek.actors.HUDActor;
import org.dieubware.tetrek.actors.MenuActor;
import org.dieubware.tetrek.model.BlockFallGrid;
import org.dieubware.tetrek.model.BlockPiece;
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
		if(((BlockFallGrid)grid).isGameRunning() != gameScreen.isGameRunning()) {
			
			gameScreen.setGameRunning(((BlockFallGrid)grid).isGameRunning());
			gameScreen.getMenuActor().setVisible(!gameScreen.isGameRunning());
			if(((BlockFallGrid)grid).isStarted()) {
				gameScreen.getMenuActor().setCurrentState(MenuActor.State.PAUSE);
			}
			else {
				System.out.println("Is lost ? " + ((BlockFallGrid)grid).isLost());
				if(((BlockFallGrid)grid).isLost()) {
					ScoreManager sm = ((BlockFallGrid)grid).getScoreManager();
					gameScreen.getMenuActor().setScore(sm.getScore(), sm.getOtherScore("level"), sm.getOtherScore("lines"), ((BlockFallGrid)grid).getHighScoreManager().getScore());
					gameScreen.getMenuActor().setCurrentState(MenuActor.State.LOST);
				}
				else
					gameScreen.getMenuActor().setCurrentState(MenuActor.State.NEW_GAME);
				
			}
		}
		if(((BlockFallGrid)grid).isPieceChanged()) {
			
			int[] intGrid = new int[16];
			BlockPiece nextPiece = ((BlockFallGrid)grid).getNextPieces().get(0);
			for(Point p : nextPiece.points) {
				intGrid[p.y*4 + p.x] = nextPiece.getPieceColor();
			}
			hudActor.setGrid(intGrid);
			((BlockFallGrid)grid).setPieceChanged(false);
			level = ((BlockFallGrid)grid).getLevel();
			if(level < 10) {
				timeManager.setLevel(level);
			}
		}
		
	}

}
