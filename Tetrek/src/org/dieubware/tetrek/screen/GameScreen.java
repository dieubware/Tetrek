package org.dieubware.tetrek.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

import org.dieubware.tetrek.TetrekGame;
import org.dieubware.tetrek.TimeManager;
import org.dieubware.tetrek.actors.GridActor;
import org.dieubware.tetrek.actors.HUDActor;
import org.dieubware.tetrek.actors.MenuActor;

public class GameScreen implements Screen {


	private Texture background;
	private Button[] buttons;
	float w,h;
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Stage stage;
	private TetrekGame game;
	private TimeManager timeManager;
	private GridActor gridActor;
	private HUDActor hudActor;
	private MenuActor menuActor;
	private int hudWidth;
	private boolean gameStarted;
	
	public GameScreen(TetrekGame blokennGame) {
		this.game = blokennGame;

		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		gridActor = new GridActor(10, 22, 10*(h/22), h);
		int cellSize = (int)h/22;
		int hudSize = (int)w-(10*(cellSize));
		if(cellSize < hudSize/4)
			hudSize = cellSize*4;
		hudActor = new HUDActor(4,4,hudSize, hudSize);
		menuActor = new MenuActor((int)w, (int)h);
	}

	@Override
	public void render(float delta) {
		if(gameStarted) {
			timeManager.addTime(delta);
			if(Gdx.input.isKeyPressed(Keys.DOWN)
					|| Gdx.input.isKeyPressed(Keys.RIGHT)
					|| Gdx.input.isKeyPressed(Keys.LEFT)
					|| Gdx.input.isTouched()) {
				timeManager.timeKeyPressed(delta);
			}
		}
		Gdx.gl.glClearColor(0f, 0f, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glEnable(GL10.GL_BLEND);
		batch.setProjectionMatrix(camera.combined);

		batch.begin();
		
		//batch.draw(background, w/2 - background.getWidth()/2, h/2 - background.getHeight()/2);
		batch.end();

		stage.act(delta);
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		w = Gdx.graphics.getWidth();
		h = Gdx.graphics.getHeight();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		
		batch = new SpriteBatch();
		
		stage = new Stage();
		

		stage.addActor(gridActor);
		stage.addActor(hudActor);
		stage.addActor(menuActor);
		hudActor.setX(gridActor.getWidth());
		hudActor.setY(h-hudActor.getHeight());

		 Gdx.input.setInputProcessor(stage);
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	public GridActor getGridActor() {
		return gridActor;
	}
	public Stage getStage() {
		return stage;
	}
	public void setTimeManager(TimeManager tm) {
		timeManager = tm;
	}

	public int getWidth() {
		return (int)w;
	}
	public int getHeight() {
		return (int)h;
	}

	public HUDActor getHudActor() {
		// TODO Auto-generated method stub
		return hudActor;
	}
}
