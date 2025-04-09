package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

public class Player {
    private float characterX;
    private float characterY;
    private Texture playerTexture;
    private final float textureScale = 6f;
    private Sprite sprite;

    public Player(){
        playerTexture = new Texture("pixel-art-enemy-spaceship-2d-sprites/PNG_Parts&Spriter_Animation/Ship1/Ship1.png");
        int spriteWidth = (int) ((int)playerTexture.getWidth()*textureScale);
        int spriteHeight = (int) (playerTexture.getHeight()*textureScale);
        sprite = new Sprite(playerTexture);
        sprite.setSize(spriteWidth,spriteHeight);

        Gdx.app.log("Sprite", "Width: "+spriteWidth+" Height: "+spriteHeight);
        characterX = 32;
        characterY = 32;
    }
    public void dispose(){
        playerTexture.dispose();
    }

    public Rectangle getBounds(){
        return sprite.getBoundingRectangle();
    }

    public void move(float moveX, float moveY){
        //out of bounds check
        float nextX = characterX + moveX;
        float nextY = characterY + moveY;
        if (nextX >= 0
            && nextX <= Gdx.graphics.getWidth()-(playerTexture.getWidth()*textureScale)
            && nextY >= -playerTexture.getHeight()
            && nextY <= Gdx.graphics.getHeight()-(playerTexture.getWidth()*textureScale-(playerTexture.getHeight()))){
            //if passing add move steps
            characterY += moveY;
            characterX += moveX;
            sprite.setPosition(characterX, characterY);
        }


    }

    public void draw(Batch batch){
        sprite.draw(batch);

    }
}
