package com.tbvm.memory.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.tbvm.memory.model.GButton;

public class GAnimation {
    long time;
    Rectangle rect;
    GButton[] btnArray;
    int totalFrames;
    int frameNow = 0;
    int precedentFrame;
    long precedentFrameTime = 0;
    int X,Y;


    public GAnimation(long length, int x, int y, int width, int height, Texture[] textures ) {
        time = length;
        totalFrames = (int) textures.length;
        X = x;
        Y = y;
        btnArray = new GButton[textures.length];

        for(int i = 0; i < textures.length; i++) {
            rect = new Rectangle(2000, 2000, width, height);
            btnArray[i] = new GButton(textures[i], rect,0,0);
        }
    }
    public void draw(SpriteBatch batch) {
        for(int i = 0; i < btnArray.length; i++) {
            btnArray[i].draw(batch);
        }


    }
    public boolean startAnimation(long timeNow) { //Create illusion of moving with periodic texture changing
        while(true) {
            if (frameNow == totalFrames) { //Stop animation
                resetFrame();
                frameNow = 0;
                precedentFrame = 0;
                precedentFrameTime = 0;
                return true;
            }
            if (precedentFrameTime == 0) {
                btnArray[frameNow].moveByCoords(X, Y);
                precedentFrame = frameNow;
                frameNow++;

            }
            if (timeNow - precedentFrameTime >= time) {
                resetFrame();
                btnArray[frameNow].moveByCoords(X, Y);
                precedentFrame = frameNow;
                frameNow++;
            }
            precedentFrameTime = timeNow;
            return false;
        }

    }
    public void startLoop(long delta) { //Create animation in loop

    }
    public void resetFrame() { //Move texture from scene
        btnArray[precedentFrame].moveByCoords(2000, 2000);
    }

}
