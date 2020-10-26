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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

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
    List<Enemie> enemies;

    Random rnd;

    ShapeRenderer renderer;

    

    float spawnTimer = 0,
            spawnInterval = 1f;

    //public static float WIDTH = 800;
    //public static float HEIGHT = 1800;
    public static float WIDTH, HEIGHT;

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
        WIDTH = Gdx.graphics.getWidth();
        HEIGHT = Gdx.graphics.getHeight();

        playerTextureAssetDesc = new AssetDescriptor<>("badlogic.jpg", Texture.class);
        bulletTextureAssetDesc = new AssetDescriptor<>("bullet.jpg", Texture.class);
        batch = new SpriteBatch();

        Texture player_txt;
        assetManager.load(playerTextureAssetDesc);
        assetManager.load(bulletTextureAssetDesc);
        assetManager.finishLoading();
        player_txt = assetManager.get(playerTextureAssetDesc);
        this.player = new Player(new Vector2( WIDTH - player_txt.getHeight(),  (HEIGHT / 2)
                - (float) (player_txt.getWidth() / 2)), player_txt);

        bullets = new ArrayList<>();
        enemies = new ArrayList<>();
        camera = new OrthographicCamera();
        //camera = new OrthographicCamera(3000, 3000 * (HEIGHT / WIDTH));
        Gdx.app.log("h" , String.valueOf(WIDTH*HEIGHT/WIDTH));
        camera.setToOrtho(false, WIDTH, WIDTH * (HEIGHT / WIDTH));

        camera.update();

        // set input
        //Gdx.input.setInputProcessor(new TouchInputProcessor(this));
        Gdx.input.setInputProcessor(new PlayerInputProcessor(player));
        rnd = new Random();

        renderer = new ShapeRenderer();

    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 1, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Texture bullet_txt, player_txt;

        assetManager.load(playerTextureAssetDesc);
        assetManager.load(bulletTextureAssetDesc);
        assetManager.finishLoading();
        player_txt = assetManager.get(playerTextureAssetDesc);

        Bullet b;

        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer >= spawnInterval)
        {
            Gdx.app.log("spawn", "jh");
            spawnTimer = 0;
            bullet_txt = assetManager.get(bulletTextureAssetDesc);

            Vector2 center = player.getCenter();
            center.y -= bullet_txt.getWidth() / 2;
            b = new Bullet(center, new Vector2(Vector2.X).scl(-10f), bullet_txt);
            b.rotate(90);
            this.bullets.add(b);

            this.enemies.add(new Meteor(new Vector2(0, HEIGHT / 2), player_txt));

        }
        for (Bullet bullet: bullets) {
            bullet.update();

        }

        for (Enemie enemie: enemies)
            enemie.update();

        player.update();


        batch.begin();
        batch.setProjectionMatrix(camera.combined);


        // iterator for element removal
        for (Iterator<Bullet> it = this.bullets.iterator(); it.hasNext();)
        {
            b = it.next();

            if (b.position.x < 0)
                it.remove();


        }

        Enemie e;
        for (Iterator<Enemie> it = this.enemies.iterator(); it.hasNext();)
        {
            e = it.next();

            if (e.position.x > WIDTH - player_txt.getHeight())
                it.remove();
            /*else

            for (Iterator<Bullet> bullet_it = this.bullets.iterator(); bullet_it.hasNext();)
            {
                b = bullet_it.next();
                if (b.sprite.getBoundingRectangle().overlaps(e.sprite.getBoundingRectangle()))
                {
                    it.remove();
                    bullet_it.remove();
                }
            }*/



        }

        List<Integer> remove_enemies = new ArrayList<>();
        for (Iterator<Bullet> it = this.bullets.iterator(); it.hasNext();) {
            b = it.next();
            int idx = 0;
            for (Enemie enemie: enemies)
            {
                if (enemie.rectangle.overlaps(b.rectangle))
                {
                    it.remove();
                    remove_enemies.add(idx);

                }
                idx++;
            }
        }

        for (int i: remove_enemies)
            enemies.remove(i);

        for (Bullet bullet: bullets)
            bullet.render(batch);

        for (Enemie enemie: enemies)
            enemie.render(batch);


        player.render(batch);
        //batch.draw(img, this.pos.x, this.pos.y);

        //assetManager.load(playerTextureAssetDesc);
        //txt = assetManager.get(playerTextureAssetDesc);
//		batch.draw(txt, 0, 0);
        batch.end();

        /*renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.line(player.getPosition(), player.getPosition().sub(Vector2.X.scl(-HEIGHT)));
        renderer.end();*/

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
