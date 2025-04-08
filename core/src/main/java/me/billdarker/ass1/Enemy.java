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
    private final Texture skin;
    private float currentX = Gdx.graphics.getWidth();
    private final float currentY;
    public Enemy(float _startingY, Texture _skin){
        currentY = _startingY;
        skin = _skin;
    }
    /*
    Moves the enemy across the screen
     */
    public void move(float delta){
        currentX -= 20*delta;
       // Gdx.app.log("Enemy", "Moved enemy");

    }

    public boolean checkBounds(){
        //Gdx.app.log("Enemy", "Checked enemy bounds x:" + currentX);

        return !(currentX >= 0);

    }
    public void draw(SpriteBatch batch) {
        batch.draw(skin,currentX,currentY,200,200);
        //Gdx.app.log("Enemy","Drew an enemy at x:"+ currentX + " y:" + currentY);
    }
}
