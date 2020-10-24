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
    Texture img, txt, powerup;

    Sprite playerSprite;

    AssetManager assetManager = new AssetManager();
    AssetDescriptor<Texture> playerTextureAssetDesc, bulletTextureAssetDesc;

    OrthographicCamera camera;

    Vector2 pos, touch, dist, dpos;

    List<Vector2> powerup_pos;
    List<PowerUp> powerUps;

    Random rnd;

    float spawnTimer = 0,
            spawnInterval = 3f;

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

        WIDTH = Gdx.graphics.getHeight();
        HEIGHT = Gdx.graphics.getWidth();
        Gdx.app.log("getHeight", String.valueOf(HEIGHT));
        Gdx.app.log("getWidth", String.valueOf(WIDTH));
        playerTextureAssetDesc = new AssetDescriptor<>("badlogic.jpg", Texture.class);
        bulletTextureAssetDesc = new AssetDescriptor<>("badlogic.jpg", Texture.class);
        batch = new SpriteBatch();
        //img = new Texture("badlogic.jpg");
        assetManager.load(playerTextureAssetDesc);
        assetManager.load(bulletTextureAssetDesc);
        assetManager.finishLoading();
        img = assetManager.get(playerTextureAssetDesc);


        camera = new OrthographicCamera(HEIGHT, WIDTH);
        //camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.setToOrtho(false, HEIGHT, HEIGHT * (WIDTH/HEIGHT));
        //camera.setToOrtho(false, WIDTH, WIDTH* (HEIGHT/WIDTH));
        camera.update();
        //assetManager.load(playerTextureAssetDesc);
        //assetManager.load("powerup.jpg", Texture.class);
        //assetManager.get(playerTextureAssetDesc);
        //assetManager.get("powerup.jpg", Texture.class);

        this.pos = new Vector2(HEIGHT - img.getHeight(), 0);
        this.touch = Vector2.Zero;
        this.dist = Vector2.Zero;
        this.dpos = Vector2.Zero;

        playerSprite = new Sprite(img, (int) img.getWidth(), (int)img.getHeight(), (int) this.pos.x, (int) this.pos.y);


        Gdx.input.setInputProcessor(new TouchInputProcessor(this));

        powerup_pos = new ArrayList<>();
        powerUps = new ArrayList<>();
        rnd = new Random();


    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //assetManager.update();

        assetManager.load(playerTextureAssetDesc);
        assetManager.load(bulletTextureAssetDesc);
        assetManager.finishLoading();
        img = assetManager.get(playerTextureAssetDesc);
        powerup = assetManager.get(bulletTextureAssetDesc);

        spawnTimer += Gdx.graphics.getDeltaTime();
        if (spawnTimer >= spawnInterval)
        {
            spawnTimer = 0;

            //powerup_pos.add(new Vector2(rnd.nextInt((int) HEIGHT), rnd.nextInt((int) WIDTH)));
            powerUps.add(new PowerUp(new Vector2(rnd.nextInt((int) HEIGHT), rnd.nextInt((int) WIDTH)), powerup));
        }

        if (this.pos.x + this.img.getHeight() > Gdx.graphics.getWidth()
                || this.pos.x < 0) {

            this.dpos = this.dpos.mul(SpaceInvadersGame.BOUNCE_HORIZONTAL).scl(SpaceInvadersGame.BOUNCE_COEF);
            if(this.pos.x < 0)
                this.pos.x = 0;
            else
                this.pos.x = HEIGHT - img.getHeight();
        }

        if (this.pos.y + img.getWidth() > WIDTH || this.pos.y < 0) {
            this.dpos = this.dpos.mul(SpaceInvadersGame.BOUNCE_VERTICAL).scl(SpaceInvadersGame.BOUNCE_COEF);

            if(this.pos.y < 0)
                this.pos.y = 0;
            else
                this.pos.y = WIDTH - img.getWidth();
        }

        this.pos = this.pos.add(this.dpos);




        batch.begin();
        batch.setProjectionMatrix(camera.combined);

        Rectangle playerRectangle = this.playerSprite.getBoundingRectangle();
        playerRectangle.setX(pos.x);
        playerRectangle.setY(pos.y);

        //for (PowerUp p: this.powerUps)
        PowerUp p;
        for (Iterator<PowerUp> it = this.powerUps.iterator(); it.hasNext();)
        {
            p = it.next();
            powerup = assetManager.get(playerTextureAssetDesc);

            Rectangle rect = p.rectangle;
            if (rect.overlaps(playerRectangle))
            {
                Gdx.app.log("col", "collisoion");
                // power up collected
                it.remove();
                this.dpos = this.dpos.scl(5f);
            }else
                batch.draw(powerup, p.position.x, p.position.y);
            //batch.draw(p.getTexture(), p.getX(), p.getY());

        }
        batch.draw(img, this.pos.x, this.pos.y);

        //assetManager.load(playerTextureAssetDesc);
        //txt = assetManager.get(playerTextureAssetDesc);
//		batch.draw(txt, 0, 0);
        batch.end();

        Gdx.gl.glLineWidth(1);
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }

    public boolean overlaps(float x, float y, float w, float h)
    {
        //return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
        return false;

    }

    public void calcMovement(float x, float y) {
        Gdx.app.log("x", String.valueOf(x));
        Gdx.app.log("y", String.valueOf(y));
        Gdx.app.log("xpos", String.valueOf(this.pos.x));
        Gdx.app.log("ypos", String.valueOf(this.pos.y));



        this.touch = new Vector2(x, WIDTH - y);
        //this.touch = new Vector2(x, y);

        this.dist = this.touch.sub(this.pos);
        this.dpos = this.dist.scl(0.05f);


    }
}
