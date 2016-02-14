package render;

import simulate.SwarmMain;
import simulate.Unit;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GDXMain extends ApplicationAdapter {
	
	ShapeRenderer batch;
	OrthographicCamera cam;
	
	@Override
	public void create () {
		batch = new ShapeRenderer();
		cam = new OrthographicCamera(SwarmMain.MAP_SIZE, SwarmMain.MAP_SIZE);
		cam.position.set(0, 0, 0);
		cam.update();
	}
	
	@Override
	public void render () {
		SwarmMain.updateSim();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(cam.combined);
		batch.begin(ShapeType.Filled);
		for(Unit u:SwarmMain.units)
		{
			batch.setColor(1, 1, 1, 1);
			batch.rect(u.position.x - 0.5f, u.position.y - 0.5f, 0.5f, 0.5f, 1f, 1f, 2f, 2f, (float)Math.toDegrees(u.direction));
		}
		batch.end();
	}
}
