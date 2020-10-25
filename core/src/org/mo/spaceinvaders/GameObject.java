package org.mo.spaceinvaders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import static org.mo.spaceinvaders.SpaceInvadersGame.*;

public abstract class GameObject
{
    //Vector2 position;
    //Rectangle rectangle;
    //Texture texture;
    Sprite sprite;
    private Vector2 position;

    public abstract void update();

    public GameObject(Vector2 position, Texture txt) {
        //this.texture = txt;
        //this.position = position;
        this.sprite = new Sprite(txt, (int) position.x, (int) position.y, (int) WIDTH, (int) HEIGHT);
        this.position = new Vector2(position);
        sprite.setPosition(position.x, position.y);
        //this.rectangle = sprite.getBoundingRectangle();
        //this.rectangle.setX(position.x);
        //this.rectangle.setY(position.y);
        //this.rectangle.setWidth(txt.getWidth());
        //this.rectangle.setHeight(txt.getHeight());
    }

    public void move(Vector2 dis)
    {
        //this.position = this.position.add(dis);
        Vector2 tmp = new Vector2(sprite.getX(), sprite.getY());
        tmp = tmp.add(dis);
        sprite.setPosition(tmp.x, tmp.y);
        this.position.add(dis);
    }

    public Vector2 getPosition()
    {
        Gdx.app.log("xpos", String.valueOf(sprite.getX()));

        //return new Vector2(sprite.getX(), sprite.getY());
        return position;
    }

    public void render(SpriteBatch batch)
    {
        //this.sprite.draw(batch);
        batch.draw(sprite, position.x, position.y);
    }
}
