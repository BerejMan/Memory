package com.tbvm.memory.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.utils.Timer;
import com.tbvm.memory.model.GButton;
import com.tbvm.memory.view.menus.SettingMenu;
import com.tbvm.memory.view.scenes.Scene1;
import com.tbvm.memory.view.scenes.Scene2;
import com.tbvm.memory.view.scenes.Scene3;

public class Wall1 extends Wall{
    private Game game;
    OrthographicCamera camera;
    Rectangle rectL, rectR;
    GButton btnL, btnR;

    Rectangle rs1, rs2, rs3;
    GButton btn1, btn2, btn3;

    Texture back;
    Vector3 touchPos;

    Timer timer;
    long lastClick;

    public Wall1(Game game) {
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

        rs1 = new Rectangle(264, 420, 220, 134);
        rs2 = new Rectangle(200,77, 280, 180);
        rs3 = new Rectangle(728, 77, 300, 200);

        btn1 = new GButton(new Texture("Null.png"), rs1, 0,0);
        btn2 = new GButton(new Texture("Null.png"), rs2, 0,0);
        btn3 = new GButton(new Texture("Null.png"), rs3, 0,0);

        back = new Texture("Background1.jpg");
        //back = new Texture("testi.jpg");

        setWallNow(1);
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
        btn1.draw(batch);
        btn2.draw(batch);
        btn3.draw(batch);

        btnL.draw(batch);
        btnR.draw(batch);
        drawInventory(batch);
        batch.end();


        if(Gdx.input.isTouched()) {

            lastClick = TimeUtils.nanoTime();
            while (TimeUtils.nanoTime() - lastClick < cooldown) {} //Artificial slow-click.

            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);

            writeClick((int) touchPos.x, (int) touchPos.y); //DEBUG

            if(btnL.checkIfClicked(touchPos.x, touchPos.y)) {
                    if(!inventoryOpen) game.setScreen(new Wall4(game));//Ignore move button if inventory is open
            }
            if(btnR.checkIfClicked(touchPos.x, touchPos.y)) {
                if(!inventoryOpen) game.setScreen(new Wall2(game));//Ignore move button if inventory is open
            }
            if(btn1.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene1(game));
            }
            if(btn2.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene2(game));
            }
            if(btn3.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Scene3(game));
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
