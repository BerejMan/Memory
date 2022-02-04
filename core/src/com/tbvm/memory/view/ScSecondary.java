package com.tbvm.memory.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.tbvm.memory.model.GButton;
import com.tbvm.memory.model.GObject;
import com.tbvm.memory.model.WalL;

public class ScSecondary implements Screen {

    boolean GameStarted = false;
    SpriteBatch batch;
    OrthographicCamera camera;
    WalL back0, back1, back2, back3, back4, Testim;
    TimeUtils time;
    Rectangle rectL, rectR, rectE, touch, playButton;
    Vector3 touchPos;

    Rectangle[] WallRect = new Rectangle[4];
    Array<Rectangle> Hitboxes = new Array<Rectangle>();
    Array<GObject> Objects = new Array<GObject>();
    Rectangle MMenu, TEST;

    GButton butL, butR, butB, butPause;

    int WallNow = 2;

    long lastClick;
    final long cooldown = 180_000_000; //Cooldown on click on elements

    public void HitClear() {
        for(Rectangle i : Hitboxes) {
            i.setX(5000);
            i.setY(5000);
        }
    }
    public void ObjectClear() {
        for(GObject i: Objects) {
            i.moveByCoords(5000, 5000);
        }
    }

    public void save() { //Func for creating saves

    }
    public void DrawWall(int ent) { //Place hitboxes and wall texture in dependency of WallNow
        if(ent == 0) {
            WallNow -= 1;
            if(WallNow == 0) WallNow = 4;
        }
        else if(ent == 1) {
            WallNow += 1;
            if(WallNow == 5) WallNow = 1;
        }
        else System.out.println("DrawWall error");

        back1.moveByCoords(5000,5000);
        back2.moveByCoords(5000,5000);
        back3.moveByCoords(5000,5000);
        back4.moveByCoords(5000,5000);
        HitClear();
        ObjectClear();

        switch (WallNow) {
            case 1: back1.moveByCoords(0,0);
            case 2: back2.moveByCoords(0,0);
            case 3: back3.moveByCoords(0,0);
            case 4: back4.moveByCoords(0,0);
        }
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        touch = new Rectangle(5000, 5000, 1, 1);

        for(int i = 0; i < WallRect.length; i++) {
            WallRect[i] = new Rectangle(5000, 5000, 1280, 720);
            Hitboxes.add(WallRect[i]);
        }
        rectL = new Rectangle(5000, 5000, 50, 720); //0 0 50 720
        rectR = new Rectangle(5000,5000,50,720);// 720-50 0 50 720
        rectE = new Rectangle(5000, 5000, 200, 100);

        MMenu = new Rectangle(0, 0, 1280, 720);

        playButton = new Rectangle(500, 295, 236, 138); // 500 295 236 138

        //TEST = new Rectangle(WallRect[1].x, WallRect[1].y - 50, 50, 50);
        //Testim = new Wall(new Texture("Nik.png"), TEST);

        back0 = new WalL(new Texture("MainMenu.png"), MMenu); //Main menu
        back1 = new WalL(new Texture("Background1.png"), WallRect[0]);
        back2 = new WalL(new Texture("Background2.png"), WallRect[1]);
        back3 = new WalL(new Texture("Background3.png"), WallRect[2]);
        back4 = new WalL(new Texture("Background4.png"), WallRect[3]);

        //butL = new Button(new Texture("ButtonMoveLeft.png"), rectL, 0,0);
       // butR = new Button(new Texture("ButtonMoveRight.png"), rectR, 700, 0);

        time = new TimeUtils();

        Hitboxes.add(rectL, rectR, rectE, MMenu);
        Hitboxes.add(playButton);
        Objects.add(back0, back1, back2, back3);
        Objects.add(back4, butL, butR, butR);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        if(GameStarted) { //Objects for game
            back4.draw(batch);
            back3.draw(batch);
            back2.draw(batch);
            back1.draw(batch);
            butL.draw(batch);
            butR.draw(batch);
        }
        else { //Objects for start menu and end menu
            back0.draw(batch);
        }
        batch.end();


        if(Gdx.input.isTouched()) {
            System.out.println();//DEBUG

            lastClick = TimeUtils.nanoTime();

            while (TimeUtils.nanoTime() - lastClick < cooldown) {} //Artificial slow-click.
            touch.x = touch.y = 10000;
            touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            touch.x = touchPos.x;
            touch.y = touchPos.y;

            //Detect clicks
            System.out.println(touch.x + ", " + touch.y);
            if(playButton.overlaps(touch)) {
                GameStarted = true;
                HitClear();
                DrawWall(0);
                butL.ButtonMove();
                butR.ButtonMove();
            }

            if(touch.x <= 50 && touch.x >= 0) {
                DrawWall(0);
                ObjectClear();
            }
            if(touch.x >= 670 && touch.x <= 720) {
                DrawWall(1);
                ObjectClear();
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
