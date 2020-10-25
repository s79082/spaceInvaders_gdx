package org.mo.spaceinvaders;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PowerUp extends GameObject{



    public PowerUp(Vector2 position, Texture txt) {
        super(position, txt);
    }

    public void update()
    {
        return;
    }

    /*
    public boolean overlaps(Rectangle r)
    {
        float x, y, width, height;
        x = position.x;
        y = position.y;
        width = rectangle.width;
        height = rectangle.height;

        return x < r.x + r.width && x + width > r.x && y < r.y + r.height && y + height > r.y;
    }

     */
}
