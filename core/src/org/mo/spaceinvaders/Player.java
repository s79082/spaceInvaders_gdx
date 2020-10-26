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
        this.dpos = Vector2.Y;
    }

    public Vector2 getCenter()
    {
        //return new Vector2(position.x + (size.x / 2), position.y + (size.y / 2));
        Vector2 tmp = new Vector2(position);
        tmp.x += sprite.getTexture().getHeight() / 2;
        tmp.y += sprite.getTexture().getWidth() / 2;
        return tmp;

    }

    public float getLowerEdge()
    {
        return 0f;
    }

    @Override
    public void update()
    {

       this.move(dpos);
       float pos = getPosition().y;
       if (pos < 0)
           position.y = 0;
        //Gdx.app.log("moving", String.valueOf(dpos.y));


    }
}
