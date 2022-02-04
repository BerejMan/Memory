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

public class InfoMenu extends MainScreen {
    private Game game;
    Texture back;
    Rectangle rectM, rectB, rectS, rectA;
    GButton btnMain, btnBack, btnSettings, btnAchive;
    public InfoMenu(Game game) {this.game = game;}
    Vector3 touchPos;

    public void show() {
        batch = new SpriteBatch();
        back = new Texture("menu/InfoMenu.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        rectM = new Rectangle(15, 670, 60, 60);
        rectB = new Rectangle(1200, 670, 60, 55);
        rectS = new Rectangle(450,600, 90,90);
        rectA = new Rectangle(600, 600, 90,90);


        btnMain = new GButton(new Texture("Null.png"), rectM, 0,0);
        btnBack = new GButton(new Texture("Null.png"), rectB,0,0);
        btnSettings = new GButton(new Texture("Null.png"), rectS,0,0);
        btnAchive = new GButton(new Texture("Null.png"), rectA, 0,0);
    }

    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        batch.draw(back, 0,0);
        btnBack.draw(batch);
        btnMain.draw(batch);
        btnSettings.draw(batch);
        btnAchive.draw(batch);
        batch.end();

        if(Gdx.input.isTouched()) {
            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);

            if(btnMain.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new MainMenu(game));
            }
            if(btnBack.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Wall1(game));
            }
            if(btnSettings.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new SettingMenu(game));
            }
            if(btnAchive.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new AchiveMenu(game));
            }
        }
    }
}
