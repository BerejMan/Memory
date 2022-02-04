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
import com.tbvm.memory.view.scenes.Scene10;
import com.tbvm.memory.view.scenes.Scene6;
import com.tbvm.memory.view.scenes.Scene7;
import com.tbvm.memory.view.scenes.Scene8;
import com.tbvm.memory.view.scenes.Scene9;

public class Wall3 extends Wall {
    private Game game;
    OrthographicCamera camera;
    Rectangle rectL, rectR;
    GButton btnL, btnR;

    Rectangle rs6, rs7, rs8, rs9, rs10;
    GButton btn6, btn7, btn8, btn9, btn10;

    Rectangle element1;
    GButton elem1;

    long lastClick;
    Vector3 touchPos;

    Texture back;

    public Wall3(Game game) {
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

        rs6 = new Rectangle(325, 420, 163, 129);
        rs7 = new Rectangle(700, 417, 265, 185);
        rs8 = new Rectangle(173, 86, 77, 90);
        rs9 = new Rectangle(335, 65, 135, 136);
        rs10 = new Rectangle(715, 162, 233, 156);

        btn6 = new GButton(new Texture("Null.png"), rs6, 0,0);
        btn7 = new GButton(new Texture("Null.png"), rs7, 0,0);
        btn8 = new GButton(new Texture("Null.png"), rs8, 0,0);
        btn9 = new GButton(new Texture("Null.png"), rs9, 0,0);
        btn10 = new GButton(new Texture("Null.png"), rs10, 0,0);

        back = new Texture("Background3.png");


        setWallNow(3);
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
        btn6.draw(batch);
        btn7.draw(batch);
        btn8.draw(batch);
        btn9.draw(batch);
        btn10.draw(batch);
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
                if(!inventoryOpen) game.setScreen(new Wall2(game));//Ignore move button if inventory is open
            }
            if(btnR.checkIfClicked(touchPos.x, touchPos.y)) {
                if(inventoryOpen) {} //Ignore move button if inventory is open
                else game.setScreen(new Wall4(game));
            }
            if(btn6.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene6(game));
            }
            if(btn7.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene7(game));
            }
            if(btn8.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene8(game));
            }
            if(btn9.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene9(game));
            }
            if(btn10.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene10(game));
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
