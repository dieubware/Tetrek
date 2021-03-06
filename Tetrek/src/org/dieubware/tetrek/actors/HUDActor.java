package org.dieubware.tetrek.actors;

import org.dieubware.tetrek.model.BlockFallGrid;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HUDActor extends Actor {

	private Color[] 		grid;
	private int 			gridWidth;
	private int 			gridHeight;

	private int 			cellWidth;
	private int 			cellHeight;
	private ShapeRenderer	shapeRenderer;
	private BitmapFont		font;
	private int score, lines, level;
	

	public HUDActor(int gridWidth, int gridHeight, float width, float height) {
		super();
		this.gridWidth  = gridWidth;
		this.gridHeight = gridHeight;
		this.setWidth(width);
		this.setHeight(height);

		this.cellWidth = (int) (this.getWidth()/gridWidth);
		this.cellHeight = (int) (this.getHeight()/gridHeight);
		

		shapeRenderer = new ShapeRenderer();
		grid = new Color[gridWidth*gridHeight];
			for(int i = 0; i< grid.length; i++)
				grid[i] = Color.GRAY;
		font = new BitmapFont();
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
							getX() +(i%gridWidth)*cellWidth, //X
							getY() +(i/gridWidth)*cellHeight, //Y
							cellWidth, cellHeight);
				}
			}
		}
		shapeRenderer.end();
		batch.begin();
		font.draw(batch, "Score : " , getX()+10, 200);
		font.draw(batch, String.valueOf(score), getX()+10, 175);
		font.draw(batch, "Level : ", getX()+10, 150);
		font.draw(batch, String.valueOf(level), getX()+10, 125);
		font.draw(batch, "Lines : ", getX()+10, 100);
		font.draw(batch, String.valueOf(lines), getX()+10, 75);


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
			case BlockFallGrid.BLUE:
				realColor = Color.BLUE;
				break;
			case BlockFallGrid.CYAN:
				realColor = Color.CYAN;
				break;
			case BlockFallGrid.EMPTY:
				realColor= Color.GRAY;
				break;
			case BlockFallGrid.LIME:
				realColor = Color.GREEN;
				break;
			case BlockFallGrid.MAGENTA:
				realColor = Color.MAGENTA;
				break;
			case BlockFallGrid.ORANGE:
				realColor = Color.ORANGE;
				break;
			case BlockFallGrid.RED:
				realColor = Color.RED;
				break;
			case BlockFallGrid.YELLOW:
				realColor = Color.YELLOW;
				break;
		}
		return realColor;
	}

	public void setScore(int score) {
		this.score = score;
	}
	public void setLines(int lines) {
		this.lines = lines;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
}
