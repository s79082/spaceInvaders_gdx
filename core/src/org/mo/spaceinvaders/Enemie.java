package org.mo.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import java.util.Iterator;

public abstract class Enemie extends GameObject {

    public abstract MovementSequence getMovementSequence();
    protected MovementSequence movementSequence;

    public Enemie(Vector2 pos, Texture t) {
        super(pos, t);

    }

    @Override
    public void update() {

        Vector2 movement = getMovementSequence().getNextMove();
        this.move(movement);

    }
}
