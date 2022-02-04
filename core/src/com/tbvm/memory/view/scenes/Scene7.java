package com.tbvm.memory.view.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.tbvm.memory.model.GButton;
import com.tbvm.memory.view.Wall3;

public class Scene7 extends Scene {
    private Game game;
    OrthographicCamera camera;

    long lastClick;
    Vector3 touchPos;
    Texture back;
    GButton mirror1, mirror2, mirror3, mirror4;
    Rectangle rect1, rect2, rect3, rect4;

    public Scene7(Game game) {
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        back = new Texture("Scenes/Scene7.png");
        rect1 = new Rectangle(1020, 580, 100, 100);
        rect2 = new Rectangle(1020, 400, 100, 100);
        rect3 = new Rectangle(50, 400, 100, 100);
        rect4 = new Rectangle(30, 25, 100, 100);
        mirror1 = new GButton(new Texture("Scenes/sprites/Mirror.png"), rect1, 0,0);
        mirror2 = new GButton(new Texture("Scenes/sprites/Mirror.png"), rect2, 0,0);
        mirror3 = new GButton(new Texture("Scenes/sprites/Mirror.png"), rect3, 0,0);
        mirror4 = new GButton(new Texture("Scenes/sprites/Mirror.png"), rect4, 0,0);

    }
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.draw(back, 0,0);
        mirror1.draw(batch);
        mirror2.draw(batch);
        mirror3.draw(batch);
        mirror4.draw(batch);
        drawBackButton(batch);
        drawInventory(batch);
        batch.end();

        if(Gdx.input.isTouched()) {

            lastClick = TimeUtils.nanoTime();
            while (TimeUtils.nanoTime() - lastClick < cooldown) {} //Artificial slow-click.

            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);

            writeClick((int) touchPos.x, (int) touchPos.y);

            //Action
            if(btnB.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Wall3(game));
            }

            if(mirror1.checkIfClicked(touchPos.x, touchPos.y)) {
                mirror1.rotate(90f);
            }
            if(mirror2.checkIfClicked(touchPos.x, touchPos.y)) {
                mirror2.rotate(90f);
            }
            if(mirror3.checkIfClicked(touchPos.x, touchPos.y)) {
                mirror3.rotate(90f);
            }
            if(mirror4.checkIfClicked(touchPos.x, touchPos.y)) {
                mirror4.rotate(90f);
            }
        }
    }
    private void rotateMirror(GButton button) {

    }
}
