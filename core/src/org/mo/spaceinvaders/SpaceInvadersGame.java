package org.mo.spaceinvaders;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import org.w3c.dom.ls.LSInput;

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

    List<Explosion> explosions;


    float spawnTimer = 0,
            spawnInterval = 1f,
    bulletTimer = 0,
    bulletInterval = 0.5f;

    //public static float WIDTH = 800;
    //public static float HEIGHT = 1800;
    public static float WIDTH, HEIGHT;

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

        explosions = new ArrayList<>();
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0, 0, 1, 1);
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
            spawnTimer = 0;
            this.enemies.add(new Meteor(new Vector2(0, rnd.nextInt((int) HEIGHT)), player_txt));

        }

        bulletTimer += Gdx.graphics.getDeltaTime();
        if (bulletTimer >= bulletInterval)
        {
            bulletTimer = 0;
            bullet_txt = assetManager.get(bulletTextureAssetDesc);
            Vector2 center = player.getCenter();
            center.y -= bullet_txt.getWidth() / 2;
            b = new Bullet(center, new Vector2(Vector2.X).scl(-15f), bullet_txt);
            b.rotate(90);
            b.scale(3, 3);
            this.bullets.add(b);
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
        }

        // check if enemy hits a bullet, remove bullet, add hit enemy
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

        // remove hit enemies, add Explosion
        for (int i: remove_enemies) {
            explosions.add(new Explosion(enemies.get(i).position));
            enemies.remove(i);

        }
        for (Bullet bullet: bullets)
            bullet.render(batch);

        for (Enemie enemie: enemies)
            enemie.render(batch);


        player.render(batch);

        Explosion explosion;
        for(Iterator<Explosion> explosionIterator = explosions.iterator();
            explosionIterator.hasNext();)
        {
            explosion = explosionIterator.next();

            explosion.update();

            if(!explosion.toDraw)
                explosionIterator.remove();
        }

        for(Explosion ex: explosions)
        {
            ex.render(batch);
        }

        //explosion.render(batch);

        batch.end();

        //Gdx.gl.glLineWidth(1);
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
