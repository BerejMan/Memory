package com.tbvm.memory.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class WalL extends GObject{
    public WalL(Texture texture, float x, float y, float width, float height) {
        super(texture, x, y, width, height);
    }

    public WalL(Texture texture, Rectangle rect) {
        super(texture, rect);
    }
    //TODO Move DrawWall func in this class

}
