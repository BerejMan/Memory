package com.tbvm.memory.view.scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.tbvm.memory.view.Wall4;

public class Scene11 extends Scene {
    private Game game;
    OrthographicCamera camera;

    long lastClick;
    Vector3 touchPos;
    Texture back;

    public Scene11(Game game) {
        this.game = game;
    }

    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);
        back = new Texture("Scenes/Scene11.png");


    }
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.draw(back, 0,0);
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
                game.setScreen(new Wall4(game));
            }
        }
    }
}
