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
    protected Vector2 position;
    protected float rotation;
    protected Vector2 scale;
    Rectangle rectangle;

    public abstract void update();

    public GameObject(Vector2 position, Texture txt) {
        //this.texture = txt;
        //this.position = position;
        this.sprite = new Sprite(txt, (int) position.x, (int) position.y, (int) WIDTH, (int) HEIGHT);
        this.position = new Vector2(position);
        sprite.setPosition(position.x, position.y);

        this.rotation = 0;
        this.scale = new Vector2(1, 1);
        this.rectangle = new Rectangle(position.x, position.y, sprite.getTexture().getWidth(),
                sprite.getTexture().getHeight());
        //this.rectangle = sprite.getBoundingRectangle();
        //this.rectangle.setX(position.x);
        //this.rectangle.setY(position.y);
        //this.rectangle.setWidth(txt.getWidth());
        //this.rectangle.setHeight(txt.getHeight());
    }

    public void move(Vector2 dis)
    {
        this.position = this.position.add(dis);
        Vector2 tmp = new Vector2(sprite.getX(), sprite.getY());
        tmp = tmp.add(dis);
        sprite.setPosition(tmp.x, tmp.y);
        this.rectangle.setX(tmp.x);
        this.rectangle.setY(tmp.y);
        //this.position.add(dis);
    }

    public void scale(float scaleX, float scaleY)
    {
        scale.x *= scaleX;
        scale.y *= scaleY;
    }

    public void rotate(float ang)
    {
        rotation += ang;
    }

    public Vector2 getPosition()
    {
        //Gdx.app.log("xpos", String.valueOf(sprite.getX()));

        //return new Vector2(sprite.getX(), sprite.getY());
        return position;
    }

    public void render(SpriteBatch batch)
    {
        //this.sprite.draw(batch);
        Texture texture = sprite.getTexture();
        batch.draw(texture, position.x , position.y, texture.getWidth() / 2,
                texture.getHeight() / 2, texture.getWidth(), texture.getHeight()
                ,scale.x, scale.y, rotation, 0,0, texture.getWidth(), texture.getHeight(),
                false, false);
        //batch.draw(texture, position.x , position.y);
        //batch.draw(sprite.getTexture());
    }
}
