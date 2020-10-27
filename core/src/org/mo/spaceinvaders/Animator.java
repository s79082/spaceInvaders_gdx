package org.mo.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Animator extends GameObject{
    private int FRAME_COLS, FRAME_ROWS;

    Texture sheet;

    Animation<TextureRegion> animation;
    float stateTime;

    int frames_passed;

    boolean toDraw = true;

    public Animator(Vector2 pos, Texture t, int c, int r, float len)
    {
        super(pos, t);
        FRAME_COLS = c;
        FRAME_ROWS = r;
        sheet = sprite.getTexture();
        TextureRegion [][] tmp = TextureRegion.split(sheet, sheet.getWidth() / FRAME_COLS,
                sheet.getHeight() / FRAME_ROWS);

        // we use a one-liner
        TextureRegion[] frames = tmp[0];

        animation = new Animation<>(len, frames);
        stateTime = 0;
        frames_passed = 0;
        animation.setPlayMode(Animation.PlayMode.NORMAL);
        this.scale(5, 5);
        this.rotate(90);
    }

    @Override
    public void update() {
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
