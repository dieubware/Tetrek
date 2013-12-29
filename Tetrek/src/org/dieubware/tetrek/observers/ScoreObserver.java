package org.dieubware.tetrek.observers;

import java.util.Observable;
import java.util.Observer;

import org.dieubware.jbrik.ScoreManager;
import org.dieubware.tetrek.actors.HUDActor;

public class ScoreObserver implements Observer {

	HUDActor hudActor;
	
	public ScoreObserver(HUDActor hudActor) {
		this.hudActor = hudActor;
	}
	
	@Override
	public void update(Observable o, Object arg1) {
		ScoreManager sm = (ScoreManager)o;
		hudActor.setScore(sm.getScore());
		hudActor.setLines(sm.getOtherScore("lines"));
		
	}

}
