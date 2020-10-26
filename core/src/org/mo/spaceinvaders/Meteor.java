package org.mo.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Meteor extends Enemie {

    @Override
    public MovementSequence getMovementSequence() {
        return movementSequence;
    }

    public Meteor(Vector2 pos, Texture t) {
        super(pos, t);
        movementSequence = new MeteorMovementSequence();
    }

    @Override
    public void update() {
        super.update();
    }
}
