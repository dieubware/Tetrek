package org.dieubware.tetrek.observers;

import java.util.Observable;
import java.util.Observer;

import org.dieubware.tetrek.TimeManager;
import org.dieubware.tetrek.actors.GridActor;
import org.dieubware.tetrek.actors.HUDActor;
import org.dieubware.tetrek.model.TetrisGrid;
import org.dieubware.tetrek.model.TetrisPiece;
import org.dieubware.jbrik.Grid;
import org.dieubware.jbrik.Point;

public class ModelObserver implements Observer {

	GridActor gridActor;
	HUDActor hudActor;
	TimeManager timeManager;
	private int level;
	public ModelObserver(TimeManager tm) {
		timeManager = tm;
		
	}
	
	public void setGridActor(GridActor gridActor, HUDActor hudActor) {
		this.gridActor = gridActor;
		this.hudActor = hudActor;
		
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		Grid grid = (Grid)o;
		gridActor.setGrid(grid.getArray());
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
