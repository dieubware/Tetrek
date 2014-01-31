package org.dieubware.tetrek.model;



public class TetrekGrid extends BlockFallGrid {

	public TetrekGrid() {
		super();
		currentPiece = new TetrekPiece();
		
		
	}
	
	protected void endOfPiece() {

		int nbLines = this.checkForLines();
		if(nbLines == 0) {
			multiplier = 1;
		}
		else {
			multiplier+=nbLines;
			scoreManager.addOtherScore("lines", nbLines);
			scoreManager.addScore(10*nbLines*multiplier);
		
			int level = scoreManager.getOtherScore("level");
			if(scoreManager.getOtherScore("lines") > level*(10+level)) {
				scoreManager.addOtherScore("level", 1);
			}
			
		}
		addPiece();
		
		pieceChanged = true;
		setFall= false;
		
	}
	
	@Override
	public void initGame() {
		super.initGame();
		BlockPiece nextPiece = new TetrekPiece();
		nextPiece.initRandomPiece(1, 4);
		nextPieces.add(nextPiece);
	}
}
