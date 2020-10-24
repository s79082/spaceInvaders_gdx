package org.mo.spaceinvaders;

import com.badlogic.gdx.math.Vector2;

public class Bullet
{
    public Vector2 pos, vel;

    public Bullet(Vector2 pos, Vector2 vel) {
        this.pos = pos;
        this.vel = vel;
    }

    public void move()
    {
        this.pos = this.pos.add(this.vel);
    }
}
