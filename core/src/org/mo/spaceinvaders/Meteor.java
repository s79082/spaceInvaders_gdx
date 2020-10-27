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
        idle = new Animator(pos, new Texture("meteor.png"), 4, 1, 0.25f);
        idle.scale(1.7f, 1.7f);
        movementSequence = new MeteorMovementSequence();
    }

    @Override
    public void update() {
        super.update();
    }
}
