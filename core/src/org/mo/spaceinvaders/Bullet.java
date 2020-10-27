package org.mo.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject
{
    public Vector2 vel;

    BulletAnimation animation;

    public Bullet(Vector2 pos, Vector2 vel, Texture t) {
        super(pos, t);
        this.vel = vel;
        this.animation = new BulletAnimation(pos);
    }

    @Override
    public void update() {
        this.move(vel);
        this.animation.move(vel);
    }

    @Override
    public void render(SpriteBatch batch) {
        animation.render(batch);
    }
}
