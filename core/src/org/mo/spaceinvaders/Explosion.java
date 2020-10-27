package org.mo.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Arrays;

public class Explosion extends GameObject{

    private static final int FRAME_COLS = 4, FRAME_ROWS = 1;

    Texture sheet;

    Animation<TextureRegion> animation;
    float stateTime;

    int frames_passed;

    boolean toDraw = true;


    public Explosion(Vector2 pos) {
        super(pos, new Texture("explosion_sheet.png"));
        //sheet = ;
        sheet = sprite.getTexture();
        TextureRegion [][] tmp = TextureRegion.split(sheet, sheet.getWidth() / FRAME_COLS,
                sheet.getHeight() / FRAME_ROWS);

        // we use a one-liner
        TextureRegion[] frames = tmp[0];

        animation = new Animation<>(0.25f, frames);
        stateTime = 0;
        frames_passed = 0;
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        this.scale(10, 10);

    }

    @Override
    public void update() {
        if(stateTime > animation.getAnimationDuration())
        {
            stateTime = 0;
            toDraw = false;
        }
    }

    @Override
    public void render(SpriteBatch batch)
    {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        //sprite.setTexture(currentFrame.);
        batch.draw(currentFrame, position.x , position.y, currentFrame.getRegionWidth() / 2,
                currentFrame.getRegionHeight() / 2, currentFrame.getRegionWidth(), currentFrame.getRegionHeight()
                ,scale.x, scale.y, rotation);

    }
}
