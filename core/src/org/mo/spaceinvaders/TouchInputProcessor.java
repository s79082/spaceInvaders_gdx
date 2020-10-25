package org.mo.spaceinvaders;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class TouchInputProcessor implements InputProcessor {

    private SpaceInvadersGame game;

    public TouchInputProcessor(SpaceInvadersGame g)
    {
        this.game = g;
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

        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public static Vector2 deviceCoordsToGameCoords(int x, int y)
    {
        return new Vector2(x, SpaceInvadersGame.WIDTH - y);
    }
}
