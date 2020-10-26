package org.mo.spaceinvaders;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

public abstract class MovementSequence extends ArrayList<Movement> {

    public abstract void addMovements();

    int current_amount, current_index, size;

    public MovementSequence()
    {
        super();
        this.addMovements();
        current_index = 0;
        current_amount = get(current_index).amount;
        size = this.size();
    }

    public Vector2 getNextMove()
    {
        if (current_amount != 0)
        {
            current_amount--;
            return get(current_index).displacement;
        }
        // next entry
        else
        {
            current_index++;
            if(current_index == size)
                current_index = 0;
            current_amount = get(current_index).amount;
            return get(current_index).displacement;
        }
    }
}
