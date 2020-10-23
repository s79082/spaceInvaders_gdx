package org.mo.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player
{
    Texture texture;
    Vector2 position;
    Vector2 size;

    public Vector2 getCenter()
    {
        return new Vector2(position.x + (size.x / 2), position.y + (size.y / 2));

    }

    public float getLowerEdge()
    {
        return 0f;
    }
}
