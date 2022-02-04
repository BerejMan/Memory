package com.tbvm.memory.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.tbvm.memory.model.GButton;
import com.tbvm.memory.model.GObject;
import com.tbvm.memory.view.menus.SettingMenu;
import com.tbvm.memory.view.scenes.Scene11;
import com.tbvm.memory.view.scenes.Scene12;
import com.tbvm.memory.view.scenes.Scene13;
import com.tbvm.memory.view.scenes.Scene14;

public class Wall4 extends Wall {
    private Game game;
    OrthographicCamera camera;
    Rectangle rectL, rectR;
    GButton btnL, btnR;

    Rectangle rs11, rs12, rs13, rs14;
    GButton btn11, btn12, btn13, btn14;

    Vector3 touchPos;

    Texture back;
    long lastClick;
    public Wall4(Game game) {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        rectL = new Rectangle(35,720/2-25,50,50);
        rectR = new Rectangle(1152-60,720/2-20,50,50);
        btnL = new GButton(new Texture("ButtonLeft.png"), rectL, 0,0);
        btnR = new GButton(new Texture("ButtonRight.png"), rectR, 0,0);

        rs11 = new Rectangle(180, 500, 251, 81);
        rs12 = new Rectangle(180, 323, 248, 164);
        rs13 = new Rectangle(180, 81, 252, 240);
        rs14 = new Rectangle(640, 145, 440, 170);

        btn11 = new GButton(new Texture("Null.png"), rs11, 0,0);
        btn12 = new GButton(new Texture("Null.png"), rs12, 0,0);
        btn13 = new GButton(new Texture("Null.png"), rs13, 0,0);
        btn14 = new GButton(new Texture("Null.png"), rs14, 0,0);

        back = new Texture("Background4.jpg");

        setWallNow(4);
        System.out.println(WallNow);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        batch.setProjectionMatrix(camera.combined);
        camera.update();
        batch.begin();
        batch.draw(back, 0,0);
        drawVignette(batch);
        btn11.draw(batch);
        btn12.draw(batch);
        btn13.draw(batch);
        btn14.draw(batch);
        btnL.draw(batch);
        btnR.draw(batch);
        drawInventory(batch);
        batch.end();

        if(Gdx.input.isTouched()) {

            lastClick = TimeUtils.nanoTime();
            while (TimeUtils.nanoTime() - lastClick < cooldown) {} //Artificial slow-click.

            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);

            writeClick((int) touchPos.x, (int) touchPos.y);

            if(btnL.checkIfClicked(touchPos.x, touchPos.y)) {
                if(!inventoryOpen) game.setScreen(new Wall3(game));//Ignore move button if inventory is open
            }
            if(btnR.checkIfClicked(touchPos.x, touchPos.y)) {
                if(!inventoryOpen) game.setScreen(new Wall1(game));//Ignore move button if inventory is open;
            }
            if(btn11.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene11(game));
            }
            if(btn12.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene12(game));
            }
            if(btn13.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene13(game));
            }
            if(btn14.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene14(game));
            }
            if(isPauseClicked((int) touchPos.x,(int) touchPos.y)) {
                game.setScreen(new SettingMenu(game));
            }
            if(isInventoryClicked((int) touchPos.x,(int) touchPos.y)) {
                if(inventoryOpen) {
                    closeInventory();
                    inventoryOpen = false;
                } else {
                    openInventory();
                    inventoryOpen = true;
                }
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
