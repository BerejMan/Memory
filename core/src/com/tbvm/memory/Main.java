package com.tbvm.memory;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.tbvm.memory.model.GButton;
import com.tbvm.memory.view.MainMenu;
import com.tbvm.memory.view.ScSecondary;
import com.tbvm.memory.view.scenes.Scene7;

public class Main extends Game {

	//TODO Save func. Temporary save will be a part of code :)

	private FileHandle wall = new FileHandle("Wall.txt");
	private FileHandle settings = new FileHandle("Settings.txt");
	private FileHandle language = new FileHandle("Language.txt");
	private FileHandle achive = new FileHandle("Achievements.txt");
	private FileHandle inventory = new FileHandle("Inventory.txt");

	//Initial data load BEGIN
	int WallNow = Integer.parseInt(wall.readString());

	char[] tmp = (settings.readString()).toCharArray();
	public boolean Sound = tmp[0] == '0' ? false : true;
	public boolean Music = tmp[1] == '0' ? false : true;

	String lang = language.readString();

	protected boolean[] achievements = initialAchieveLoad();


	public static GButton[] items = new GButton[12]; //TODO load data from file

	//Initial data load END

	@Override
	public void create () {
		setScreen(new MainMenu(this));
	}
	//public void create () {
	//	setScreen(new Scene7(this));
	//}
	public int getWallNow() {
		return WallNow;
	}
	public void setWallNow(int x) {
		WallNow = x;
	}

	public boolean getSound() {return Sound;}
	public void setSound(boolean x) {Sound = x;}

	public boolean getMusic() {return Music;}
	public void setMusic(boolean x) {Music = x;}

	public void save() { //TODO create save func. of: WallNow, Settings, Lang, Achievements and Inventory (remove save())

	}
	public void saveWallNow(int num) {
		WallNow = num;
	}
	public void saveSettings(boolean sound, boolean music) {
		Sound = sound;
		Music = music;
	}
	public void saveLanguage(String lang) {
		this.lang = lang;
	}
	public void saveAchievements(boolean... list) { //TODO To concretize arguments

	}
	public void saveInventory(GButton[] inv) {
		items = inv;
	}

	public void load(int type) { //TODO create load func. of: WallNow, Settings, Lang, Achievements and Inventory (remove load())

	}
	public int loadWallNow() {
		return WallNow;
	}
	public boolean[] loadSettings() {
		boolean[] tmp = new boolean[]{Sound, Music};
		return tmp;
	}
	public String loadLanguage() {
		return lang;
	}
	public boolean[] loadAchievements() {
		return achievements;
	}
	public GButton[] loadInventory() {
		return items;
	}

	private boolean[] initialAchieveLoad() {
		char[] tmp = (achive.readString()).toCharArray();
		boolean[] returnVar = new boolean[tmp.length];
		if(tmp.length != 9) {
			System.out.println("READING ERROR: Achievements");
			System.exit(0);
		}
		for(int i = 0; i < tmp.length; i++) {
			returnVar[i] = tmp[i] == '0' ? false : true;
		}
		return returnVar;
	}
}