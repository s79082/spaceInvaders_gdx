package org.mo.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Bullet extends GameObject
{
    public Vector2 vel;

    public Bullet(Vector2 pos, Vector2 vel, Texture t) {
        super(pos, t);
        this.vel = vel;
    }

    @Override
    public void update() {
        this.move(vel);
    }
}
