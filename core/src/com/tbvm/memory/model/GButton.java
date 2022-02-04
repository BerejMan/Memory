package com.tbvm.memory.model;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.tbvm.memory.view.Wall1;

public class GButton extends GObject {
    int X,Y;
    public GButton(Texture texture, Rectangle rect, int x, int y) {
        super(texture, rect);
        X = x;
        Y = y;
    }
    public void ButtonMove() {
        bounds.setX(X);
        bounds.setY(Y);
    }
    public boolean checkIfClicked(float x, float y) {
        if(x > super.getX() && x < super.getX() + super.getWidth()) {
            if(y > super.getY() && y < super.getY() + super.getHeight()) {
                return true;
            }
        }
        return false;

    }
}
