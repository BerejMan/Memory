package com.tbvm.memory.view.menus;

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
import com.tbvm.memory.view.MainMenu;
import com.tbvm.memory.view.MainScreen;
import com.tbvm.memory.view.Wall1;

public class SettingMenu extends MainScreen {
    private Game game;
    OrthographicCamera camera;
    Texture back;
    Rectangle rectM, rectB, rectA, rectI;
    GButton btnMain, btnBack, btnAchive, btnInfo;

    Rectangle rectSoundOn, rectSoundOff, rectMusicOn, rectMusicOff, rectReset;
    GButton btnSoundOn, btnSoundOff, btnMusicOn, btnMusicOff;

    long lastClick;

    public SettingMenu(Game game) { this.game = game;}
    Vector3 touchPos;

    boolean Sound = true;
    boolean Music = true;

    private boolean getSound() {return Sound;}
    private void setSound(boolean x) {Sound = x;}

    private boolean getMusic() {return Music;}
    private void setMusic(boolean x) {Music = x;}

    private void changeButtonStatusSound() {
        if(getSound()) {
            btnSoundOff.moveByCoords(400, 450); //TODO Дибил макс. высота 720
            btnSoundOn.moveByCoords(2000, 450);
            setSound(false);
        }
        else {
            btnSoundOn.moveByCoords(400, 450);
            btnSoundOff.moveByCoords(2000, 450);
            setSound(true);
        }
    }
    private void changeButtonStatusMusic() {
        if(getMusic()) {
            btnMusicOff.moveByCoords(500, 450);
            btnMusicOn.moveByCoords(2000, 450);
            setMusic(false);
        }
        else {
            btnMusicOn.moveByCoords(500,450);
            btnMusicOff.moveByCoords(2000,450);
            setMusic(true);
        }
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        back = new Texture("menu/SettingMenu.png");
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        rectM = new Rectangle(15, 670, 60, 60);
        rectB = new Rectangle(1200, 670, 60, 55);
        rectA = new Rectangle(600, 600, 90,90);
        rectI = new Rectangle(750, 600, 90,90);



        btnMain = new GButton(new Texture("Null.png"), rectM, 0,0);
        btnBack = new GButton(new Texture("Null.png"), rectB,0,0);
        btnAchive = new GButton(new Texture("Null.png"), rectA, 0,0);
        btnInfo = new GButton(new Texture("Null.png"), rectI,0,0);

        if(getSound()) {
            rectSoundOn = new Rectangle(400, 450, 90,90);
            rectSoundOff = new Rectangle(2000, 450, 90,90);
        } else {
            rectSoundOn = new Rectangle(2000, 450, 90,90);
            rectSoundOff = new Rectangle(400, 450, 90,90);
        }

        if(getMusic()) {
            rectMusicOn = new Rectangle(500, 450, 90,90);
            rectMusicOff = new Rectangle(2000, 450, 90,90);
        } else {
            rectMusicOn = new Rectangle(2000, 450, 90,90);
            rectMusicOff = new Rectangle(500, 450, 90,90);
        }

//        rectSoundOn = new Rectangle(2000, 800, 90,90);
//        rectSoundOff = new Rectangle(2000, 800, 90,90);
//        rectMusicOn = new Rectangle(2000, 800, 90,90);
//        rectMusicOff = new Rectangle(2000, 800, 90,90);

        btnSoundOn = new GButton(new Texture("menu/SoundOn.png"), rectSoundOn,0,0);
        btnSoundOff = new GButton(new Texture("menu/SoundOff.png"), rectSoundOff,0,0);
        btnMusicOn = new GButton(new Texture("menu/MusicOn.png"), rectMusicOn, 0,0);
        btnMusicOff = new GButton(new Texture("menu/MusicOff.png"), rectMusicOff, 0,0);


    }

    @Override
    public void render(float delta) { //TODO Вставить во все сцены функ isPauseClicked и переключать на меню настроек --__--
        ScreenUtils.clear(0,0,0,1);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        batch.draw(back, 0,0);
        btnSoundOn.draw(batch);
        btnSoundOff.draw(batch);
        btnMusicOn.draw(batch);
        btnMusicOff.draw(batch);
        btnMain.draw(batch);
        btnBack.draw(batch);
        btnAchive.draw(batch);
        btnInfo.draw(batch);
        batch.end();

        if(Gdx.input.isTouched()) {
            lastClick = TimeUtils.nanoTime();
            while (TimeUtils.nanoTime() - lastClick < cooldown) {} //Artificial slow-click.

            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);


            if(btnMain.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new MainMenu(game));
            }
            if(btnBack.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new Wall1(game));
            }
            if(btnAchive.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new AchiveMenu(game));
            }
            if(btnInfo.checkIfClicked(touchPos.x, touchPos.y)) {
                game.setScreen(new InfoMenu(game));
            }
            if(btnSoundOn.checkIfClicked(touchPos.x, touchPos.y) || btnSoundOff.checkIfClicked(touchPos.x, touchPos.y)) {
                changeButtonStatusSound();

                System.out.println("Click!");
            }
            if(btnMusicOn.checkIfClicked(touchPos.x, touchPos.y) || btnMusicOff.checkIfClicked(touchPos.x, touchPos.y)) {
                changeButtonStatusMusic();
                System.out.println("Click!");
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


