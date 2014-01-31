package org.dieubware.tetrek;

import java.util.Arrays;

import org.dieubware.jbrik.Grid;
import org.dieubware.tetrek.model.BlockFallGrid;

public class TimeManager {

	private BlockFallGrid model;
	
	private float timeSpent = 0;
	private float second = 0;
	private float timePressed = 0;
	private float deltaTime = 0;
	private int level = 1;
	
	public TimeManager(BlockFallGrid model) {
		this.model = model;
		this.level = model.getLevel();
	}
	
	public void addTime(float time) {
		timeSpent += time;
		second += time;
		deltaTime = 1f-( (float)level/10f);
		if(second >= deltaTime) {
			model.fall();
			second = 0;
		} 
	}
	
	public void timeKeyPressed(float time) {
		timePressed += time;
		//System.out.println((((TetrisGrid) model).getMoveFirst()));
		if(timePressed >= 0.05 && model.getMoveFirst()) {
			
			((BlockFallGrid) model).act();
			timePressed = 0;
		}
		else if (timePressed >= 0.5 && !model.getMoveFirst()){
			
			model.act();
			model.setMoveFirst(true);
			timePressed = 0;
			
		}
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
}
