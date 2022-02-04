package com.tbvm.memory.view.menus;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.tbvm.memory.model.GButton;
import com.tbvm.memory.view.MainMenu;
import com.tbvm.memory.view.MainScreen;
import com.tbvm.memory.view.Wall1;

public class AchiveMenu extends MainScreen {
    private Game game;
    public AchiveMenu(Game game) {
        this.game = game;
    }

    OrthographicCamera camera;
    Texture back;
    Rectangle rectB, rectM, rectI, rectS;
    GButton btnMain, btnBack, btnSettings, btnInfo;

    Vector3 touchPos;
    @Override
    public void show() {
        batch = new SpriteBatch();
        back = new Texture("menu/AchiveMenu.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        rectM = new Rectangle(15, 670, 60, 60);
        rectB = new Rectangle(1200, 670, 60, 55);
        rectI = new Rectangle(750, 600, 90,90);
        rectS = new Rectangle(450,600, 90,90);



        btnMain = new GButton(new Texture("Null.png"), rectM, 0,0);
        btnBack = new GButton(new Texture("Null.png"), rectB,0,0);
        btnInfo = new GButton(new Texture("Null.png"), rectI,0,0);
        btnSettings = new GButton(new Texture("Null.png"), rectS,0,0);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        batch.draw(back, 0,0);
        btnBack.draw(batch);
        btnMain.draw(batch);
        btnSettings.draw(batch);
        btnInfo.draw(batch);
        batch.end();

        if(Gdx.input.isTouched()) {
            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos); //TODO Artificial slow-click

            if(btnMain.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new MainMenu(game));
            }
            if(btnBack.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Wall1(game));
            }
            if(btnSettings.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new SettingMenu(game));
            }
            if(btnInfo.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new InfoMenu(game));
            }
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

