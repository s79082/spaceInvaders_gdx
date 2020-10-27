package org.mo.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Enemie extends GameObject {

    public abstract MovementSequence getMovementSequence();
    public MovementSequence movementSequence;

    Animator idle;
    public Enemie(Vector2 pos, Texture t) {
        super(pos, t);

    }

    @Override
    public void update() {

        Vector2 movement = getMovementSequence().getNextMove();
        this.move(movement);
        idle.move(movement);

    }

    @Override
    public void render(SpriteBatch batch) {
        idle.render(batch);
    }
}
