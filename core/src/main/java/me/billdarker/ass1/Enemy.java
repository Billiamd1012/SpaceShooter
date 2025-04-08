package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
    This is an enemy object it will travel from the right of the screen
    shooting bullets.

    Starting position @param startingY
    Texture to represent the object @param skin
 */
public class Enemy {
    Texture skin;
    float startingY;
    float startingX = Gdx.graphics.getWidth();
    public Enemy(float _startingY, Texture _skin){
        startingY = _startingY;
        skin = _skin;
    }
    public void draw(SpriteBatch batch) {
        batch.draw(skin,startingX,startingY);
    }
}
