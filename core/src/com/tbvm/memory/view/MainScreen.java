package com.tbvm.memory.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

public class MainScreen implements Screen { //TODO Перенести сюда save/load функции (Подгрузку файлов пока оставить в Main)
    public SpriteBatch batch;
    public   OrthographicCamera camera;
    public static final long cooldown = 180_000_000; //Cooldown on click on elements
    float stateTime;
    static int WallNow = 0;
    protected void setWallNow(int x) {WallNow = x;}


    Rectangle rectV = new Rectangle(-100, -10, 1560, 740);
    Sprite object = new Sprite(new Texture("vignette.png"));
    public void drawVignette(SpriteBatch batch) {
        object.setBounds(rectV.getX(), rectV.getY(), rectV.getWidth(), rectV.getHeight());
        object.draw(batch);
    }


    Texture black = new Texture("blackout/BlackoutSheet.png");
    TextureRegion[] animationFrames;
    Animation animation;


    @Override
    public void show() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1280, 720);

        TextureRegion[][] tmpFrames = TextureRegion.split(black,1160,720);
        animationFrames = new TextureRegion[4];

        int index = 0;

        for (int i = 0; i < 2; i++){
            for(int j = 0; j < 2; j++) {
                animationFrames[index++] = tmpFrames[j][i];
            }
        }
        animation = new Animation<TextureRegion>(0.025f, animationFrames);

        stateTime = 0f;
    }

    @Override
    public void render(float delta) {

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
