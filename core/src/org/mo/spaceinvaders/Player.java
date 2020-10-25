package org.mo.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import static org.mo.spaceinvaders.SpaceInvadersGame.HEIGHT;
import static org.mo.spaceinvaders.SpaceInvadersGame.WIDTH;

public class Player extends GameObject
{

    // displacement per frame
    public Vector2 dpos;

    public Player(Vector2 p, Texture t)
    {
        super(p, t);
        this.dpos = Vector2.Zero;
    }

    public Vector2 getCenter()
    {
        //return new Vector2(position.x + (size.x / 2), position.y + (size.y / 2));
        return Vector2.Zero;
    }

    public float getLowerEdge()
    {
        return 0f;
    }

    @Override
    public void update()
    {

       // this.move(dpos);

    }
}
