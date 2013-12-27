package org.dieubware.tetrek;

import java.util.Arrays;

import org.dieubware.jbrik.Grid;
import org.dieubware.tetrek.model.TetrisGrid;

public class TimeManager {

	private Grid model;
	
	private float timeSpent = 0;
	private float second = 0;
	private float timePressed = 0;
	
	public TimeManager(Grid model) {
		this.model = model;
	}
	
	public void addTime(float time) {
		timeSpent += time;
		second += time;
		
		if(second >= 0.5) {
			((TetrisGrid)model).fall();
			second = 0;
		} 
	}
	
	public void timeKeyPressed(float time) {
		timePressed += time;
		//System.out.println((((TetrisGrid) model).getMoveFirst()));
		if(timePressed >= 0.05 && ((TetrisGrid)model).getMoveFirst()) {
			
			((TetrisGrid) model).act();
			timePressed = 0;
		}
		else if (timePressed >= 0.5 && !((TetrisGrid)model).getMoveFirst()){
			
			((TetrisGrid) model).act();
			((TetrisGrid)model).setMoveFirst(true);
			timePressed = 0;
			
		}
	}
	
}
