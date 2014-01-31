package org.dieubware.tetrek.model;

import java.util.ArrayList;
import java.util.List;

import org.dieubware.jbrik.Point;
import org.dieubware.tetrek.model.TetrekPiece.Type;

public abstract class BlockPiece {

	protected int rotation;

	/**
	 * Array of points
	 */
	public Point[] points = new Point[4];

	public BlockPiece() {
		rotation = 0;
	}


	/**
	 * Check if the Point id contained on the piece 
	 * @param p Point 
	 * @return true id the point s in the piece, false otherwise
	 */
	public boolean contains(Point p) {
		for(Point pi : points) {
			if(p.equals(pi))
				return true;
		}
		return false;
	}

	/**
	 * Restore the piece from an array of points
	 * @param newPoints Array of points containing the new position
	 */
	public void restore(Point[] newPoints) {
		if(newPoints.length == points.length ) {
			for(int i = 0; i< points.length; i++) {
				points[i].setCoord(newPoints[i]);
			}
		}
	}

	/**
	 * Check if the piece contains points that are out of the left bound
	 * @param bound
	 * @return
	 */
	public boolean isOutOfLeftBound(int bound) {
		boolean ret = false;
		for(Point p : points) {
			if(p.x < bound) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * Check if the piece contains points that are out of the right bound
	 * @param bound
	 * @return
	 */
	public boolean isOutOfRightBound(int bound) {
		boolean ret = false;
		for(Point p : points) {
			if(p.x > bound) {
				ret = true;
			}
		}
		return ret;
	}

	/**
	 * Check if the piece contains points that are out of the bottom bound
	 * @param bound
	 * @return
	 */
	public boolean isOutOfBottomBound(int bound) {
		boolean ret = false;
		for(Point p : points) {
			if(p.y < bound) {
				ret = true;
			}
		}
		return ret;
	}


	/**
	 * set the piece to a new type and initializes its position
	 * @param piece
	 */
	public void init() {
		rotation = 0;
		for(int i =0; i< 4; i++) {
			points[i] = new Point(0,0);
		}
	}

	/**
	 * Intializes the piece position from the piece type
	 * @param piece
	 * @param originX
	 * @param height
	 */
	public abstract void initRandomPiece(int originX, int height);

	
	public abstract void initNextPiece(BlockPiece next, int originX, int height);
	
	
	
	/**
	 * Move the piece to one square down
	 */
	public void fall() {
		for(Point p : points) {
			p.y--;
		}
	}

	/**
	 * Returns the minimum Y position contained in the piece
	 * @return
	 */
	public int minY() {
		int minY = points[0].y;
		for(Point p : points) {
			if(p.y < minY) minY = p.y;
		}
		return minY;
	}

	/**
	 * Returns the maximum Y position contained in the piece
	 * @return
	 */
	public int maxX() {
		int maxX = 0;
		for(Point p : points) {
			if(p.x > maxX) maxX = p.x;
		}
		return maxX;
	}

	/**
	 * Returns the minimum X position contained in the piece
	 * @return
	 */
	public int minX() {
		int minX = points[0].x;
		for(Point p : points) {
			if(p.x < minX) minX = p.x;
		}
		return minX;
	}

	/**
	 * Returns the points that does'nt have any piece block below them
	 * @return
	 */
	public List<Point> minYPoints() {
		List<Point> minPoints = new ArrayList<Point>(4);
		for(Point p : points) {
			if(!this.contains(new Point(p.x,p.y -1))) {
				minPoints.add(p);
			}
		}
		return minPoints;
	}

	/**
	 * Move the piece horizontaly
	 * @param direction
	 */
	public void move(int direction) {
		for(Point p : points) {
			p.x += direction;
		}

	}

	/**
	 * Move the piece verticaly
	 * @param direction
	 */
	public void vertMove(int direction) {
		for(Point p : points) {
			p.y += direction;
		}

	}

	/**
	 * Rotate the piece
	 * @param direction 
	 */
	public abstract void rotate(int direction);
	
	public abstract int getPieceColor();
	
	public abstract void setTypeOf(BlockPiece bp);
}
