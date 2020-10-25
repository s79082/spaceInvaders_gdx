package org.mo.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

// handles input for one Player
public class PlayerInputProcessor implements InputProcessor {

    Player player;
    private Vector2 touchPos;

    public PlayerInputProcessor(Player p) {
        this.player = p;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        float dist_y;
        float touch_y = SpaceInvadersGame.WIDTH - screenY;
        dist_y = player.getPosition().y - touch_y;

        player.dpos = Vector2.Y.scl(dist_y * 0.05f);


        Gdx.app.log("xpos", String.valueOf(player.getPosition().x));
        Gdx.app.log("ypos", String.valueOf(player.getPosition().y));

        Gdx.app.log("dist", String.valueOf(dist_y));


        /*
        Vector2 touch, dist;
        touch = TouchInputProcessor.deviceCoordsToGameCoords(screenX, screenY);
        dist = touch.sub(player.getPosition());
        player.dpos = dist.scl(0.05f);

         */
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
