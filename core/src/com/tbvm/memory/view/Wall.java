package com.tbvm.memory.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.tbvm.memory.model.GButton;

public class Wall extends MainScreen {
    protected Rectangle bounds = new Rectangle(1280-120, 0, 240, 720);
    protected Sprite InventoryObject = new Sprite(new Texture("Inventory.png"));
    protected Rectangle rectB = new Rectangle(1190, 655, 60, 60);
    protected  Rectangle rectI = new Rectangle(1210, 12, 20, 20);
    protected GButton btnPause = new GButton(new Texture("Nik.png"), rectB, 0,0);
    protected GButton btnInventory = new GButton(new Texture("Nik.png"), rectI, 0,0);

    public static boolean inventoryOpen = false;
    protected boolean isSelectedSlot = false;
    int selectedSlot;

    public void drawInventory(SpriteBatch batch) {
        InventoryObject.setBounds(bounds.getX(), bounds.getY(), bounds.getWidth(), bounds.getHeight());
        InventoryObject.draw(batch);
        btnPause.draw(batch);
        btnInventory.draw(batch);


    }
    public boolean isPauseClicked(int x, int y) {
        if(btnPause.checkIfClicked(x, y)) {
            return true;
        } else return false;
    }
    public boolean isInventoryClicked(int x, int y) {
        if(btnInventory.checkIfClicked(x, y)) {
            return true;
        } else return false;
    }
    public void openInventory() {
        for(int i = 0; i < 120; i++) {
            int tmp = (int) bounds.getX();
            bounds.setX(tmp-1);
        }
        btnInventory.moveByCoords(1090, 12);
        btnPause.moveByCoords(1070,655);
    }
    public void closeInventory() {
        for(int i = 0; i < 120; i++) {
            int tmp = (int) bounds.getX();
            bounds.setX(tmp+1);
        }
        btnInventory.moveByCoords(1210, 12);
        btnPause.moveByCoords(1190, 655);
    }

    public void writeClick(int x, int y) {
        System.out.println(x + ", " + y);
    }

    public void changeScreen(Screen screen, Game game) {
        game.setScreen(screen);
    }
}
