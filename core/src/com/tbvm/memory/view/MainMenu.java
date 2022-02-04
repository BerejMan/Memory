package com.tbvm.memory.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScalingViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.tbvm.memory.model.GButton;

public class MainMenu extends MainScreen {
    private Game game;
    OrthographicCamera camera;
    Texture back;
    Rectangle touch, rectB, rectW;
    GButton btn, wall;

    Vector3 touchPos;
    public MainMenu(Game game) {
        this.game = game;
    }
    @Override
    public void show() {
        batch = new SpriteBatch();
        back = new Texture("MainMenu.jpg");
        //back = new Texture("back.png");
        touch = new Rectangle(5000, 5000, 1, 1);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        rectB = new Rectangle( 597, 311, 90, 95);
       // SimpleButton btn = new SimpleButton(new Texture("Nik.png"), 100, 100, 200, 100, game);
        btn = new GButton(new Texture("Null.png"), rectB, 0, 0);

        rectW = new Rectangle(0,0,1280,720);
        wall = new GButton(back, rectW, 0,0);


    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0,0,0,1);
        batch.setProjectionMatrix(camera.combined);
        camera.update();

        batch.begin();
        wall.draw(batch);
        btn.draw(batch);
        //batch.draw(btn.getTxt(), btn.getX(), btn.getY(), btn.getWidth(), btn.getHeight());
        batch.end();

        if(Gdx.input.isTouched()) {
            touchPos = new Vector3(Gdx.input.getX(), Gdx.input.getY(),0);
            camera.unproject(touchPos);
            //System.out.println(touchPos.x + ", " + touchPos.y); // DEBUG

            touch.setX(touchPos.x);
            touch.setY(touchPos.y);

            System.out.println(touch.x + ", " + touch.y);

           if(btn.checkIfClicked(touch.x, touch.y)) {
              game.setScreen(new Wall1(game));
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
