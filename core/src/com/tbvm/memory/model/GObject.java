package com.tbvm.memory.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public abstract class GObject {

    protected Rectangle bounds;
    protected Sprite object;
    public GObject(Texture texture, float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
        object = new Sprite(texture);
    }
    public GObject(Texture texture, Rectangle rect) {
        bounds = new Rectangle(rect.x, rect.y, rect.width, rect.height);
        object = new Sprite(texture);
    }

    public void draw(SpriteBatch batch) {
        object.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        object.draw(batch);
    }
    public String getCoords() {
        return object.getX() + ", " + object.getY();
    }
    public void hide() {
        bounds.x = bounds.y = 5000;
    }

    public void moveByCoords(float x, float y) {
       bounds.setX(x);
       bounds.setY(y);
       object.setX(x);
       object.setY(y);
    }
    public void move(int x, int y) {
        bounds.x += x;
        bounds.y += y;
        object.setX(bounds.getX());
        object.setY(bounds.getY());
    }
    public int getX() {
        return (int) bounds.getX();
    }
    public int getY() {
        return (int) bounds.getY();
    }
    public int getWidth() { return (int) bounds.getWidth();}
    public int getHeight() { return (int) bounds.getHeight();}
    public Texture getTxt() {return object.getTexture();}

    public void rotate(float deg) {
        object.rotate(deg);
    }


}
