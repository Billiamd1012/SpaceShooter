package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/*
    A bullet will travel at a set speed from the object of origin colliding with players and enemies along the way.
 */
public class Bullet {
    private final Texture skin;
    private final Sprite sprite;
    private final float speed;
    private float currentX;
    private float currentY;

    public Bullet(Texture _skin, float _speed, float _currentX, float _currentY){
        skin = _skin;
        speed = _speed;
        sprite = new Sprite(skin);

        //position
        currentX = _currentX;
        currentY = _currentY;
        sprite.setPosition(currentX,currentY);
    }

    public void move(float delta){
        float moveX = speed*delta;
        currentX += moveX;
        sprite.setPosition(currentX,currentY);
        Gdx.app.log("Bullet","Bullet moved to "+currentX);
    }

    public void draw(Batch batch){
        sprite.draw(batch);
    }


}
