package org.dieubware.tetrek.model;

import org.dieubware.jbrik.Point;
import org.dieubware.jbrik.ScoreManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dieubware.jbrik.Grid;
import org.dieubware.tetrek.model.TetrisPiece.PieceType;

import com.badlogic.gdx.Gdx;

/**
 * 
 * @author gwenn
 *
 *
 * Grid is represented this way :
 * X : 0 is left, 9 is right
 * Y : 21 is up, 0 is bottom
 */
public class TetrisGrid extends Grid {



	public static final int WIDTH = 10;
	public static final int HEIGHT = 22;

	public static final int EMPTY = 0;
	public static final int CYAN = 1;
	public static final int BLUE = 2;
	public static final int ORANGE = 3;
	public static final int YELLOW = 4;
	public static final int LIME = 5;
	public static final int MAGENTA = 6;
	public static final int RED = 7;
	

	public TetrisPiece currentPiece;
	public boolean	setMoveRight = false;
	public boolean	setMoveLeft = false;
	public boolean	setFall = false;
	private boolean	moveFirst = false;
	private boolean pieceChanged = true;
	
	private ScoreManager scoreManager;
	private int multiplier= 1;
	
	private List<PieceType> nextPieces;

	public TetrisGrid() {
		super(1, WIDTH, HEIGHT, false);
		currentPiece = new TetrisPiece();
		nextPieces = new ArrayList<PieceType>();
		scoreManager = new ScoreManager();
	}

	public void act() {
		if(setMoveRight)
			movePieceRight();
		else if(setMoveLeft)
			movePieceLeft();
		else if(setFall)
			fall(2);
		
	}
	
	/**
	 * Initializes the model when a game starts
	 */
	public void startGame() {
		addPiece(PieceType.random());
		nextPieces.add(PieceType.random());
	}

	/**
	 * Adds a piece at the top of the screen
	 * @param piece piece type
	 */
	public void addPiece(PieceType piece) {
		int originX = WIDTH/2;
		currentPiece.initPiece(piece, originX, HEIGHT);

		//Put the piece in the grid
		for(int i = 0; i<currentPiece.points.length; i++) {
			if(grid[getIndex(currentPiece.points[i])] != EMPTY) {
				lost();
			}
			grid[getIndex(currentPiece.points[i])] = currentPiece.getPieceColor();
			
		}
	}

	private void lost() {
		Gdx.app.exit();
	}
	
	/**
	 * Let the current piece fall directly
	 */
	public void directFall() {
		//delete old piece
		for(Point p : currentPiece.points) {
			grid[getIndex(p)] = EMPTY;
		}
		while(canFall(currentPiece)) {
			currentPiece.fall();
		}
		//Re fill
		for(Point p : currentPiece.points) {
			grid[getIndex(p)] = currentPiece.getPieceColor();
		}
		endOfPiece();
		setChanged();
		notifyObservers();

	}

	public void fall() {
		fall(1);
	}
	
	/**
	 * Move the current piece to a square down
	 * 
	 */
	public void fall(int number) {
		while(number > 0) {
			if(canFall(currentPiece)){
				//delete old piece
				for(Point p : currentPiece.points) {
					if(getIndex(p) != -1)
						grid[getIndex(p)] = EMPTY;
				}
				//Fall
				
				currentPiece.fall();
				
				//Re fill
				for(Point p : currentPiece.points) {
					if(getIndex(p) != -1)
						grid[getIndex(p)] = currentPiece.getPieceColor();
				}
				number--;
			}
			else {
				endOfPiece();
				number=0;
			}
		}
		setChanged();
		notifyObservers();

	}

	private void endOfPiece() {

		int nbLines = this.checkForLines();
		if(nbLines == 0) {
			multiplier = 1;
		}
		else {
			multiplier++;
			scoreManager.addOtherScore("lines", nbLines);
			scoreManager.addScore(10*nbLines*multiplier);
		}
		addPiece(nextPieces.get(0));
		nextPieces.add(0, PieceType.random());
		pieceChanged = true;
		setFall= false;
		
	}

	/**
	 * Determines if the given piece can fall down
	 * @param piece chosen piece
	 * @return true if the piece can fall, false otherwise
	 */
	private boolean canFall(TetrisPiece piece) {
		boolean canFall = true;
		for(Point p : piece.minYPoints()) {
			int pointIndex = getIndex(p.x, p.y-1);
			//System.out.println("Index du bas : " + pointIndex);
			if(pointIndex == -1 || grid[pointIndex] != EMPTY) {
				canFall = false;
			}
		}

		return canFall;
	}
	


	/**
	 * Moves the current piece to the right
	 * @return
	 */
	public boolean movePieceRight() {
		return movePiece(currentPiece, 1);
	}

	/**
	 * Move the current piece to the left
	 * @return
	 */
	public boolean movePieceLeft() {
		return movePiece(currentPiece, -1);
	}

	/**
	 * Move the given piece horizontaly by the number given in parameter
	 * @param piece piece to move
	 * @param direction distance to move. Negative : left, positive : right
	 * @return
	 */
	private boolean movePiece(TetrisPiece piece, int direction) {
		boolean ret = true;
		if(piece.maxX() + direction >= WIDTH
				|| piece.minX() + direction < 0)
			ret = false;
		else {
			for(Point p : piece.points) {

				if(getIndex(p.x + direction,p.y) != -1 &&
						grid[getIndex(p.x + direction,p.y)] != EMPTY
						&& !piece.contains(new Point(p.x + direction, p.y))) {
					ret = false;
				}
			}
			if(ret) {
				//delete old piece
				for(Point p : piece.points) {
					if(getIndex(p.x + direction,p.y) != -1)
						grid[getIndex(p)] = EMPTY;
				}
				piece.move(direction);
				//Re fill
				for(Point p : piece.points) {
					if(getIndex(p.x,p.y) != -1)
						grid[getIndex(p)] = currentPiece.getPieceColor();
				}
			}
		}
		setChanged();
		notifyObservers();
		return ret;


	}

	/**
	 * Rotates the current PIece if it is possible
	 */
	public void rotate() {
		//Copy position array
		Point[] originalPosition = new Point[currentPiece.points.length];
		for(int i = 0; i< originalPosition.length; i++)
			originalPosition[i] = new Point(currentPiece.points[i]);

		//delete old piece
		for(Point p : currentPiece.points) {
			if(getIndex(p) != -1)
				grid[getIndex(p)] = EMPTY;
		}
		currentPiece.rotate();
		//Check if it's in the left border
		while(currentPiece.isOutOfLeftBound(0)) {
			currentPiece.move(1);
		}
		//Right bound
		while(currentPiece.isOutOfRightBound(WIDTH-1)) {
			currentPiece.move(-1);
		}
		//Bottom bound
		while(currentPiece.isOutOfBottomBound(0)) {
			currentPiece.vertMove(1);
		}
		//Check the other pieces
		boolean isOnOtherPiece = false;
		int upCount = 0;
		do {
			upCount++; 
			int check = 0;
			for(Point p: currentPiece.points) {
				if(getIndex(p) != -1 && grid[getIndex(p)] != EMPTY) {
					isOnOtherPiece = true;
					currentPiece.vertMove(1);
				}
				else {
					check++;
				}
			}
			if(check == currentPiece.points.length) {
				isOnOtherPiece = false;
			} 
			if(upCount > 2) {
				//Rotation fails
				isOnOtherPiece = false;

				System.out.println("Restoring from :");
				System.out.println(Arrays.toString(currentPiece.points));
				currentPiece.restore(originalPosition);
				System.out.println("to ");
				System.out.println(Arrays.toString(originalPosition));
			}
		}
		while(isOnOtherPiece);


		//Re fill
		for(Point p : currentPiece.points) {
			if(getIndex(p) != -1)
				grid[getIndex(p)] = currentPiece.getPieceColor();
		}
		setChanged();
		notifyObservers();
	}
	
	public void setMoveFirst(boolean b) {
		moveFirst  = b;
		
	}

	public boolean getMoveFirst() {
		return moveFirst;
	}

	public boolean isPieceChanged() {
		return pieceChanged;
	}

	public void setPieceChanged(boolean pieceChanged) {
		this.pieceChanged = pieceChanged;
	}

	public List<PieceType> getNextPieces() {
		// TODO Auto-generated method stub
		return nextPieces;
	}

	public ScoreManager getScoreManager() {
		return scoreManager;
	}

}
