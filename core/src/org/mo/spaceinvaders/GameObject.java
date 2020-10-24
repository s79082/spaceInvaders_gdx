package org.mo.spaceinvaders;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class GameObject
{
    Vector2 position;
    Rectangle rectangle;
    Texture texture;

    public GameObject(Vector2 position, Texture txt) {
        this.texture = txt;
        this.position = position;
        this.rectangle = (new Sprite(txt)).getBoundingRectangle();
        this.rectangle.setX(position.x);
        this.rectangle.setY(position.y);
        this.rectangle.setWidth(txt.getWidth());
        this.rectangle.setHeight(txt.getHeight());
    }

    public abstract void update();
    public void render(SpriteBatch batch)
    {
        batch.draw(texture, position.x, position.y);
    }
}
