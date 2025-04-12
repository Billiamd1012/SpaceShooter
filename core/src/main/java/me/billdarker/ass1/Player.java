package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;

public class Player {
    private final float bulletSpeed = 600f;
    private float characterX;
    private float characterY;
    private Texture playerTexture;
    private Texture bulletTexture;
    private final float textureScale = 6f;
    private Sprite sprite;

    private ArrayList<Bullet> bullets;

    private Rectangle bounds = new Rectangle();
    private final PlayerAnimation playerAnimation = new PlayerAnimation();
    private boolean isMoving = false;
    private TextureRegion frame;

    private float exhaustOffsetX;
    private float exhaustOffsetY;

    public Player(){
        playerTexture = new Texture("pixel-art-enemy-spaceship-2d-sprites/PNG_Parts&Spriter_Animation/Ship1/Ship1.png");
        bulletTexture = new Texture("pixel-art-alien-spaceship-2d-game-sprites/PNG_Animations/Shots/Shot1/shot1_asset.png");

        int spriteWidth = (int) ((int)playerTexture.getWidth()*textureScale);
        int spriteHeight = (int) (playerTexture.getHeight()*textureScale);
        sprite = new Sprite(playerTexture);
        sprite.setSize(spriteWidth,spriteHeight);

        bullets = new ArrayList<>();

        Gdx.app.log("Sprite", "Width: "+spriteWidth+" Height: "+spriteHeight);
        characterX = 32;
        characterY = 32;
        bounds = sprite.getBoundingRectangle();

        exhaustOffsetY = spriteHeight*-0.02f;
        exhaustOffsetX = spriteWidth*-0.55f;
    }
    public void dispose(){
        bulletTexture.dispose();
        playerTexture.dispose();
    }

    public Rectangle getBounds(){
        bounds.setPosition(characterX,characterY);
        return bounds;
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
            isMoving = Math.abs(moveX) + Math.abs(moveY) > 1f;
            //Gdx.app.log("IsMoving","Moving "+isMoving+(Math.abs(nextX) + Math.abs(nextY)));
            sprite.setPosition(characterX, characterY);
        }
    }
    /*
        This method runs all of the update methods for bullets and then returns the bounding boxes of all the bullets so that enemy manager can manage collisions
     */
    public ArrayList<Bullet> bulletsUpdate(float delta){
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            bullet.move(delta);
            if (!bullet.checkBounds()){
                iterator.remove();
                break;
            }
        }

        return bullets;
    }

    public void shoot(){
        Gdx.app.log("Player", "pew");
        //create bullet with bullet texture, starting position and speed
        float startX = characterX + sprite.getWidth()*0.70f;
        float startY = characterY + sprite.getHeight()/4;
        Bullet bullet = new Bullet(bulletTexture, bulletSpeed, startX, startY);
        bullets.add(bullet);
    }

    public void draw(Batch batch, float delta){
        for (Bullet bullet:
            bullets){
            bullet.draw(batch);
        }
        frame = playerAnimation.getFrame(isMoving, delta);
        batch.draw(frame,characterX+exhaustOffsetX,characterY+exhaustOffsetY,frame.getRegionWidth()*textureScale,frame.getRegionHeight()*textureScale);
        sprite.draw(batch);

    }
}
