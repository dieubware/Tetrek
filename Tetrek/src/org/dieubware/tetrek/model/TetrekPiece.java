package org.dieubware.tetrek.model;


public class TetrekPiece extends BlockPiece {

	public enum Type {
		EMPTY,L, RL, Z, RZ, T, SQUARE, BAR;
		private static Type[] VALUES = {L, RL, Z, RZ, T,SQUARE, BAR};
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

			L.color = BlockFallGrid.ORANGE;
			RL.color = BlockFallGrid.BLUE;
			Z.color = BlockFallGrid.RED;
			RZ.color = BlockFallGrid.LIME;
			T.color = BlockFallGrid.MAGENTA;
			SQUARE.color = BlockFallGrid.YELLOW;
			BAR.color = BlockFallGrid.CYAN;
		}
		/**
		 * Select a random piece
		 * @return a random piece
		 */
		public static Type random()  {
			return VALUES[(int) ((double)VALUES.length*Math.random())];

		}
	}
	
	private Type type;
	@Override
	public void initRandomPiece(int originX, int height) {
		initPiece(Type.random(), originX, height);
	}
	@Override
	public void initNextPiece(BlockPiece next, int originX, int height) {
		setTypeOf(next);
		initPiece(type, originX, height);
	}
	
	
	public void initPiece(Type t,int originX, int height) {
		System.out.println("init to : "+originX + ","+height);
		this.type = t;
		this.init();
		switch(type) {
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
	
	public void rotate(int direction) {

		rotation = Math.abs((rotation + direction)%type.rotations);
		int centerX = points[1].x;
		int centerY = points[1].y;
		switch(type) {

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

	@Override
	public int getPieceColor() {
		return type.color;
	}

	@Override
	public void setTypeOf(BlockPiece bp) {
		this.type = ((TetrekPiece)bp).type;
	}

	

}
