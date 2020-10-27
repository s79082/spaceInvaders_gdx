package org.mo.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class BulletAnimation extends Animator
{

    public BulletAnimation(Vector2 pos)
    {
        super(pos, new Texture("bullet_sheet.png"), 3, 1, 0.25f);
    }

}
