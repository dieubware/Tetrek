package org.dieubware.tetrek.model;

import java.util.ArrayList;
import java.util.List;

import org.dieubware.jbrik.Point;

public class TetrisPiece {

	private int rotation;

	/**
	 * 
	 * @author gwenn
	 *
	 */
	public enum PieceType {
		EMPTY,L, RL, Z, RZ, T, SQUARE, BAR;
		private static PieceType[] VALUES = {L, RL, Z, RZ, T,SQUARE, BAR};
		public int rotations;
		public int color;
		static {
			L.rotations = 4;
			RL.rotations = 4;
			Z.rotations = 2;
			RZ.rotations = 2;
			T.rotations = 4;
			SQUARE.rotations = 1;
			BAR.rotations = 2;

			L.color = TetrisGrid.ORANGE;
			RL.color = TetrisGrid.BLUE;
			Z.color = TetrisGrid.RED;
			RZ.color = TetrisGrid.LIME;
			T.color = TetrisGrid.MAGENTA;
			SQUARE.color = TetrisGrid.YELLOW;
			BAR.color = TetrisGrid.CYAN;
		}

		/**
		 * Select a random piece
		 * @return a random piece
		 */
		public static PieceType random()  {
			return VALUES[(int) ((double)VALUES.length*Math.random())];

		}
	}

	private PieceType pieceType;

	/**
	 * Array of points
	 */
	public Point[] points = new Point[4];

	public TetrisPiece() {
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
	public void setPieceType(PieceType piece) {
		pieceType = piece;
		rotation = 0;
		for(int i =0; i< 4; i++) {
			points[i] = new Point(0,0);
		}
	}

	public PieceType getPieceType() {
		return pieceType;
	}
	public int getPieceColor() {
		return pieceType.color;
	}

	/**
	 * Intializes the piece position from the piece type
	 * @param piece
	 * @param originX
	 * @param height
	 */
	public void initPiece(PieceType piece, int originX, int height) {
		this.setPieceType(piece);
		switch(piece) {
			case L :
				/* X
				 * 0
				 * 1
				 * 23
				 */
				points[0].setCoord(originX, height-1);
				points[1].setCoord(originX, height-2);
				points[2].setCoord(originX, height-3);
				points[3].setCoord(originX+1, height-3);
				break;
			case RL :
				/*  X
				 *  0
				 *  1
				 * 32
				 */
				points[0].setCoord(originX, height-1);
				points[1].setCoord(originX, height-2);
				points[2].setCoord(originX, height-3);
				points[3].setCoord(originX-1, height-3);
				break;
			case Z :
				/*  X
				 * 01
				 *  23
				 */
				points[0].setCoord(originX-1, height-1);
				points[1].setCoord(originX, height-1);
				points[2].setCoord(originX, height-2);
				points[3].setCoord(originX+1, height-2);
				break;
			case RZ :
				/*  X
				 *  23
				 * 01
				 */
				points[0].setCoord(originX-1, height-2);
				points[1].setCoord(originX, height-2);
				points[2].setCoord(originX, height-1);
				points[3].setCoord(originX+1, height-1);
				break;
			case T :
				/*  X
				 * 012
				 *  3
				 */
				points[0].setCoord(originX-1, height-1);
				points[1].setCoord(originX, height-1);
				points[2].setCoord(originX+1, height-1);
				points[3].setCoord(originX, height-2);
				break;
			case SQUARE :
				/*
				 * OO
				 * OO
				 */
				points[0].setCoord(originX, height-1);
				points[1].setCoord(originX+1, height-1);
				points[2].setCoord(originX, height-2);
				points[3].setCoord(originX+1, height-2);
				break;
			case BAR :
				/*
				 * O
				 * O
				 * O
				 * O
				 */
				points[0].setCoord(originX, height-1);
				points[1].setCoord(originX, height-2);
				points[2].setCoord(originX, height-3);
				points[3].setCoord(originX, height-4);
				break;
			default:
				break;
		}

	}

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
	 */
	public void rotate() {

		rotation = (rotation + 1)%pieceType.rotations;
		int centerX = points[1].x;
		int centerY = points[1].y;
		switch(pieceType) {

			case L :
				switch(rotation) {
					case 0:
						/* X
						 * 0
						 *Y1
						 * 23
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX, centerY+1);
						points[2].setCoord(centerX, centerY-1);
						points[3].setCoord(centerX+1, centerY-1);
						break;
					case 1:
						/*  X
						 *Y210
						 * 3
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX+1, centerY);
						points[2].setCoord(centerX-1, centerY);
						points[3].setCoord(centerX-1, centerY-1);
						break;
					case 2:
						/*  X
						 * 32
						 *Y 1
						 *  0
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX, centerY-1);
						points[2].setCoord(centerX, centerY+1);
						points[3].setCoord(centerX-1, centerY+1);
						break;
					case 3:
						/*  X
						 *   3
						 *Y012
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX-1, centerY);
						points[2].setCoord(centerX+1, centerY);
						points[3].setCoord(centerX+1, centerY+1);
						break;

				}
				break;
			case RL :
				switch(rotation) {
					case 0:
						/*  X
						 *  0
						 *Y 1
						 * 32
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX, centerY+1);
						points[2].setCoord(centerX, centerY-1);
						points[3].setCoord(centerX-1, centerY-1);
						break;
					case 1:
						/*  X
						 * 3  
						 *Y210
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX+1, centerY);
						points[2].setCoord(centerX-1, centerY);
						points[3].setCoord(centerX-1, centerY+1);
						break;
					case 2:
						/* X
						 * 23
						 *Y1
						 * 0
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX, centerY-1);
						points[2].setCoord(centerX, centerY+1);
						points[3].setCoord(centerX+1, centerY+1);
						break;
					case 3:
						/*  X
						 *Y012
						 *   3
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX-1, centerY);
						points[2].setCoord(centerX+1, centerY);
						points[3].setCoord(centerX+1, centerY-1);
						break;

				}
				break;
			case Z :

				switch(rotation) {
					case 0:
						/*  X
						 *Y01
						 *  23
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX-1, centerY);
						points[2].setCoord(centerX, centerY-1);
						points[3].setCoord(centerX+1, centerY-1);

						break;
					case 1:
						/* X
						 *  3
						 *Y12 
						 * 0
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX, centerY-1);
						points[2].setCoord(centerX+1, centerY);
						points[3].setCoord(centerX+1, centerY+1);
						break;

				}
				break;
			case RZ :
				switch(rotation) {
					case 0:
						/*  X
						 *  23
						 *Y01 
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX-1, centerY);
						points[2].setCoord(centerX, centerY+1);
						points[3].setCoord(centerX+1, centerY+1);
						break;
					case 1:
						/*  X
						 * 3
						 *Y21
						 *  0
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX, centerY-1);
						points[2].setCoord(centerX-1, centerY);
						points[3].setCoord(centerX-1, centerY+1);
						break;

				}
				break;
			case T :
				switch(rotation) {
					case 0:
						/*  X
						 *Y012
						 *  3
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX-1, centerY);
						points[2].setCoord(centerX+1, centerY);
						points[3].setCoord(centerX, centerY-1);
						break;
					case 1:
						/*  X
						 *  0
						 *Y31
						 *  2
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX, centerY+1);
						points[2].setCoord(centerX, centerY-1);
						points[3].setCoord(centerX-1, centerY);
						break;
					case 2:
						/*  X
						 *  3
						 *Y210 
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX+1, centerY);
						points[2].setCoord(centerX-1, centerY);
						points[3].setCoord(centerX, centerY+1);
						break;
					case 3:
						/* X
						 * 2
						 *Y13
						 * 0
						 */
						centerX = points[1].x;
						centerY = points[1].y;

						points[0].setCoord(centerX, centerY-1);
						points[2].setCoord(centerX, centerY+1);
						points[3].setCoord(centerX+1, centerY);
						break;

				}
				break;
			case BAR :
				switch(rotation) {
					case 0:
						/* X
						 * O
						 * O
						 *YO
						 * O
						 */
						centerX = points[2].x;
						centerY = points[2].y;
						for(int i = 0; i < points.length; i++){
							points[i].x = centerX;

						}
						points[0].y = centerY +2;
						points[1].y = centerY +1;
						points[2].y = centerY;
						points[3].y = centerY -1;
						break;
					case 1:
						/*   X
						 *YOOOO
						 */
						centerX = points[2].x;
						centerY = points[2].y;
						for(int i = 0; i < points.length; i++){
							points[i].y = centerY;

						}
						points[0].x = centerX +2;
						points[1].x = centerX +1;
						points[2].x = centerX;
						points[3].x = centerX -1;
						break;

				}
				break;
			default:
				//Do nothing for the square
				break;
		}
	}
}
