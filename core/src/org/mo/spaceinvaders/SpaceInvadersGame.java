package org.mo.spaceinvaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Vector2;

public class SpaceInvadersGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img, txt;

	AssetManager assetManager = new AssetManager();
	AssetDescriptor<Texture> bulletTextureAssetDesc;

	OrthographicCamera camera;

	Vector2 pos, touch, dist, dpos;

	public static Matrix3 BOUNCE = new Matrix3(new float[]
			{
					-1, 0, 0,
					0, 1, 0,
					0, 0, 0
			}
	);

	public static float BOUNCE_COEF = 0.75f;
	
	@Override
	public void create () {

		bulletTextureAssetDesc = new AssetDescriptor<>("badlogic.jpg", Texture.class);
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		//assetManager.load(bulletTextureAssetDesc);
        //assetManager.load("bullet.jpg", Texture.class);
		//assetManager.get(bulletTextureAssetDesc);
        //assetManager.get("bullet.jpg", Texture.class);

		this.pos = new Vector2(0, Gdx.graphics.getHeight() - (img.getHeight() / 2));
		this.touch = Vector2.Zero;
		this.dist = Vector2.Zero;
		this.dpos = Vector2.Zero;

		Gdx.input.setInputProcessor(new TouchInputProcessor(this));
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//assetManager.update();

		assetManager.load(bulletTextureAssetDesc);
		assetManager.finishLoading();
		img = assetManager.get(bulletTextureAssetDesc);

		if (this.pos.x + this.img.getHeight() > Gdx.graphics.getWidth()
			|| this.pos.x < 0)

		    this.dpos = this.dpos.mul(SpaceInvadersGame.BOUNCE).scl(SpaceInvadersGame.BOUNCE_COEF);

		this.pos = this.pos.add(this.dpos);

		batch.begin();
		batch.setProjectionMatrix(camera.combined);

		batch.draw(img, this.pos.x, this.pos.y);
		//assetManager.load(bulletTextureAssetDesc);
		//txt = assetManager.get(bulletTextureAssetDesc);
//		batch.draw(txt, 0, 0);
		batch.end();

		Gdx.gl.glLineWidth(1);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	public void calcMovement(float x, float y)
	{
	    Gdx.app.log("x", String.valueOf(x));
        Gdx.app.log("y", String.valueOf(y));
		Gdx.app.log("xpos", String.valueOf(this.pos.x));
		Gdx.app.log("ypos", String.valueOf(this.pos.y));

		this.touch = new Vector2(x, Gdx.graphics.getHeight() - y);
		//this.touch = new Vector2(x, y);

		this.dist = this.touch.sub(this.pos);
		this.dpos = this.dist.scl(0.05f);


	}
}
