package org.mo.spaceinvaders;

import com.badlogic.gdx.math.Vector2;

public class Movement
{
    public Vector2 displacement;

    // how many Frames displacement is applied
    public int amount;

    public Movement(Vector2 displacement, int amount) {
        this.displacement = displacement;
        this.amount = amount;
    }
}
