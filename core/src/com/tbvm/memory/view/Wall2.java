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
import com.tbvm.memory.view.menus.SettingMenu;
import com.tbvm.memory.view.scenes.Scene4;
import com.tbvm.memory.view.scenes.Scene5;

public class Wall2 extends Wall {
    private Game game;
    OrthographicCamera camera;
    Rectangle rectL, rectR;
    GButton btnL, btnR;

    Rectangle rs4, rs5;
    GButton btn4, btn5;

    long lastClick;
    Vector3 touchPos;

    Texture back;

    public Wall2(Game game) {
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

        rs4 = new Rectangle(200, 90, 220, 400);
        rs5 = new Rectangle(706, 86, 235, 445);

        btn4 = new GButton(new Texture("Null.png"), rs4, 0,0);
        btn5 = new GButton(new Texture("Null.png"), rs5, 0,0);

        back = new Texture("Background2.jpg");

        setWallNow(2);
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
        btn4.draw(batch);
        btn5.draw(batch);
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
                if(!inventoryOpen) game.setScreen(new Wall1(game));//Ignore move button if inventory is open
            }
            if(btnR.checkIfClicked(touchPos.x, touchPos.y)) {
                if(!inventoryOpen) game.setScreen(new Wall3(game));//Ignore move button if inventory is open
            }
            if(btn4.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene4(game));
            }
            if(btn5.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene5(game));
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
