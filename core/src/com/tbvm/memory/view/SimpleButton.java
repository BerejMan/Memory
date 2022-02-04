package com.tbvm.memory.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.tbvm.memory.model.GObject;

public class SimpleButton extends GObject {
    Game game;
    public SimpleButton(Texture texture, float x, float y, float width, float height, Game game) {
        super(texture, x, y, width, height);
        this.game = game;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(super.getTxt(), super.getX(), super.getY(), super.getWidth(), super.getHeight());

    }
}