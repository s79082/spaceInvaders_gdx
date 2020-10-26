package org.mo.spaceinvaders;

import com.badlogic.gdx.math.Vector2;

public class MeteorMovementSequence extends MovementSequence {

    public MeteorMovementSequence() {
        super();
    }

    @Override
    public void addMovements()
    {
        add(new Movement(new Vector2(Vector2.X).scl(5), 70));
        add(new Movement(new Vector2(2, 1).scl(5), 70));
        add(new Movement(new Vector2(Vector2.X).scl(5), 70));
        add(new Movement(new Vector2(2, -1).scl(5), 70));


    }
}
