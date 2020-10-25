package org.mo.spaceinvaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class SpaceInvadersGame extends ApplicationAdapter {
    SpriteBatch batch;

    AssetManager assetManager = new AssetManager();
    AssetDescriptor<Texture> playerTextureAssetDesc, bulletTextureAssetDesc;

    OrthographicCamera camera;

    Player player;
    List<Bullet> bullets;

    Random rnd;

    float spawnTimer = 0,
            spawnInterval = 3f;

    public static float WIDTH = 800;
    public static float HEIGHT = 1800;

    public static Matrix3 BOUNCE_HORIZONTAL = new Matrix3(new float[]
            {
                    -1, 0, 0,
                    0, 1, 0,
                    0, 0, 0
            }
    );

    public static Matrix3 BOUNCE_VERTICAL = new Matrix3(new float[]
            {
                    1, 0, 0,
                    0, -1, 0,
                    0, 0, 0,
            }
    );

    public static float BOUNCE_COEF = 0.75f;

    @Override
    public void create() {
        WIDTH = Gdx.graphics.getHeight();
        HEIGHT = Gdx.graphics.getWidth();

        playerTextureAssetDesc = new AssetDescriptor<>("badlogic.jpg", Texture.class);
        bulletTextureAssetDesc = new AssetDescriptor<>("badlogic.jpg", Texture.class);
        batch = new SpriteBatch();

        Texture player_txt;
        assetManager.load(playerTextureAssetDesc);
        assetManager.load(bulletTextureAssetDesc);
        assetManager.finishLoading();
        player_txt = assetManager.get(playerTextureAssetDesc);
        this.player = new Player(new Vector2(HEIGHT - player_txt.getHeight(), WIDTH / 2),
                                player_txt);


        camera = new OrthographicCamera();
        camera.setToOrtho(false, WIDTH, WIDTH * (HEIGHT/WIDTH));

        // set input
        //Gdx.input.setInputProcessor(new TouchInputProcessor(this));
        Gdx.input.setInputProcessor(new PlayerInputProcessor(player));
        rnd = new Random();


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Texture bullet_txt, player_txt;

        assetManager.load(playerTextureAssetDesc);
        assetManager.load(bulletTextureAssetDesc);
        assetManager.finishLoading();
        player_txt = assetManager.get(playerTextureAssetDesc);
        bullet_txt = assetManager.get(bulletTextureAssetDesc);

        /*
        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer >= spawnInterval)
        {
            spawnTimer = 0;

            this.bullets.add(new Bullet(player.position, Vector2.X.scl(-10f), bullet_txt));
        }

         */


        batch.begin();
        batch.setProjectionMatrix(camera.combined);
        /*
        Bullet b;
        for (Iterator<Bullet> it = this.bullets.iterator(); it.hasNext();)
        {
            b = it.next();
            b.update();
            b.render(batch);


        }*/
        player.update();
        player.render(batch);
        //batch.draw(img, this.pos.x, this.pos.y);

        //assetManager.load(playerTextureAssetDesc);
        //txt = assetManager.get(playerTextureAssetDesc);
//		batch.draw(txt, 0, 0);
        batch.end();

        Gdx.gl.glLineWidth(1);
    }

    @Override
    public void dispose() {
        batch.dispose();
    }

    public boolean overlaps(float x, float y, float w, float h)
    {
        //return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
        return false;

    }

}
