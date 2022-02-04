package com.tbvm.memory.view.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.tbvm.memory.model.GButton;
import com.tbvm.memory.view.Wall;

public class Scene extends Wall {

    Rectangle rectB = new Rectangle(1160/2-40, 5, 50, 50);
    protected GButton btnB = new GButton(new Texture("BackButton.png"), rectB, 0, 0);
    public void drawBackButton(SpriteBatch batch) {
        btnB.draw(batch);
    }
    public boolean isBackButtonClicked(int x, int y) {
        if(btnB.checkIfClicked(x, y)) {
            return true;
        } else return false;
    }
}
