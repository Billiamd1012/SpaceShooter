package me.billdarker.ass1;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;

//class that controls what scene is active
public class Main extends Game implements ApplicationListener {
//    Import screens
    public static GameScreen gameScreen;
    public static MenuScreen menuScreen;
    @Override
    public void create() {
        gameScreen = new GameScreen(this);
        menuScreen = new MenuScreen(this);
        setScreen(menuScreen);

    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
    }
}
