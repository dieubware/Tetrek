package org.dieubware.tetrek.actors;

import org.dieubware.tetrek.model.TetrisGrid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GridActor extends Actor {

	private Color[] 		grid;
	private int 			gridWidth;
	private int 			gridHeight;

	private int 			cellWidth;
	private int 			cellHeight;
	private ShapeRenderer	shapeRenderer;

	public GridActor(int gridWidth, int gridHeight, float width, float height) {
		super();
		this.gridWidth  = gridWidth;
		this.gridHeight = gridHeight;
		this.setWidth(width);
		this.setHeight(height);

		this.cellWidth = (int) (this.getWidth()/gridWidth);
		this.cellHeight = (int) (this.getHeight()/gridHeight);

		shapeRenderer = new ShapeRenderer();
		grid = new Color[gridWidth*gridHeight];

	}

	public void makeTest() {
		int[] gridTest = new int[10*22];
		int idx = 0;
		while(idx < 3) {
			gridTest[idx] = 1;
			idx++;
		}
		while(idx < 10){ 
			gridTest[idx] = 0;
			idx++;
		}
		while(idx < 12) {
			gridTest[idx] = 1;
			idx++;
		}
		while(idx < gridTest.length) {
			gridTest[idx] = 0;
			idx++;
		}
		setGrid(gridTest);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha) {
		super.draw(batch, parentAlpha);

		batch.end();
		shapeRenderer.begin(ShapeType.FilledRectangle);
		if(grid != null) {
			for(int i = 0; i < grid.length; i++) {
				if(grid[i]!=null) {
					shapeRenderer.setColor(grid[i]);
					shapeRenderer.filledRect(
							(i%gridWidth)*cellWidth, //X
							(i/gridWidth)*cellHeight, //Y
							cellWidth, cellHeight);
				}
			}
		}
		shapeRenderer.end();
		batch.begin();


	}

	public void setGrid(int[] grid) {
		if(grid.length == this.grid.length) {
			for(int i = 0; i < grid.length; i++) {
				this.grid[i] = getColor(grid[i]);
			}
		}
		else {
			//TODO throw exception
		}
	}

	public Color getColor(int gridColor) {
		Color realColor = null;
		switch(gridColor) {
			case TetrisGrid.BLUE:
				realColor = Color.BLUE;
				break;
			case TetrisGrid.CYAN:
				realColor = Color.CYAN;
				break;
			case TetrisGrid.EMPTY:
				realColor= Color.BLACK;
				break;
			case TetrisGrid.LIME:
				realColor = Color.GREEN;
				break;
			case TetrisGrid.MAGENTA:
				realColor = Color.MAGENTA;
				break;
			case TetrisGrid.ORANGE:
				realColor = Color.ORANGE;
				break;
			case TetrisGrid.RED:
				realColor = Color.RED;
				break;
			case TetrisGrid.YELLOW:
				realColor = Color.YELLOW;
				break;
		}
		return realColor;
	}





}
