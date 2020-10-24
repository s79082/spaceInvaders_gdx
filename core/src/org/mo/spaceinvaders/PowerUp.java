package org.mo.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class PowerUp {

    Vector2 position;
    Rectangle rectangle;

    public PowerUp(Vector2 position, Texture txt) {
        this.position = position;
        this.rectangle = (new Sprite(txt)).getBoundingRectangle();
        this.rectangle.setX(position.x);
        this.rectangle.setY(position.y);
        this.rectangle.setWidth(txt.getWidth());
        this.rectangle.setHeight(txt.getHeight());
    }

}
