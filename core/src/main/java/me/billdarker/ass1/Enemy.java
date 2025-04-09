package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

/*
    This is an enemy object it will travel from the right of the screen
    shooting bullets.

    Starting position @param startingY
    Texture to represent the object @param skin
 */
public class Enemy {
    private final Texture skin;
    private float currentX = Gdx.graphics.getWidth();
    private final float currentY;
    public final float textureScaling = 4f;

    private final Sprite sprite;
    public Enemy(float _startingY, Texture _skin){
        currentY = _startingY;
        skin = _skin;
        int skinWidth = (int) (skin.getWidth()*textureScaling);
        int skinHeight = (int) (skin.getHeight()*textureScaling);
        sprite = new Sprite(skin);
        sprite.setSize(skinWidth,skinHeight);
        sprite.flip(true, false);
    }
    /*
    Moves the enemy across the screen
     */
    public void move(float delta){
        currentX -= 20*delta;
        sprite.setPosition(currentX,currentY);
        // Gdx.app.log("Enemy", "Moved enemy");

    }

    public boolean checkBounds(){
        //Gdx.app.log("Enemy", "Checked enemy bounds x:" + currentX);

        return !(currentX >= 0);

    }

    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }
    public void draw(SpriteBatch batch) {
        sprite.draw(batch);
        //Gdx.app.log("Enemy","Drew an enemy at x:"+ currentX + " y:" + currentY);
    }
}
