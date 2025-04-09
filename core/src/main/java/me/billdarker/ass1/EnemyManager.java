package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/*
    This class will spawn enemy object on the right side of the screen
 */
public class EnemyManager {
    public Texture basicEnemy = new Texture("pixel-art-alien-spaceship-2d-game-sprites/PNG_Parts&Spriter_Animation/Ship5/Ship5.png");

    public ArrayList<Enemy> enemies = new ArrayList<>();
    private float timeSinceSpawn = 0;


    /*
    Every second spawn a new basic enemy at a random y position
     */
    public void spawnLevel1(float delta){
        timeSinceSpawn += delta;

        if (timeSinceSpawn >= 5f){
            spawnBasic();
            timeSinceSpawn = 0;
        }
        moveEnemies(delta);
    }

    private void moveEnemies(float delta){
        if (enemies == null) {
            Gdx.app.log("Enemy", "Enemy list is null!");
            return;
        }
        for (Enemy enemy:
             enemies) {
            enemy.move(delta);
            if (enemy.checkBounds()){
                enemies.remove(enemy);
            }

        }
    }

    //check collisions with player and enemy models
    public boolean CheckPlayerCollision(Rectangle playerBound){


        for (Enemy enemy:
            enemies){
            if (playerBound.overlaps(enemy.getBounds())) {
                return true;
            }
        }
        return false;
    }

    public void CheckBulletCollision(ArrayList<Rectangle> bullets){
        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            for (Rectangle bullet:bullets){
                if(bullet.overlaps(enemy.getBounds())){
                    iterator.remove();
                }
            }
        }
    }

    public void drawEnemies(SpriteBatch batch){
        if (enemies == null) {
            Gdx.app.log("Enemy", "Enemy list is null");
            return;
        }
        //Gdx.app.log("Enemy", "Drawing enemies");


        for (Enemy enemy:
            enemies) {
            //Gdx.app.log("Enemy", "Attempting to draw enemy");

            enemy.draw(batch);
        }
    }

    private void spawnBasic(){
        float startingY = new Random().nextFloat() * (Gdx.graphics.getHeight());
        Enemy newEnemy = new Enemy(startingY,basicEnemy);
        enemies.add(newEnemy);
    }

    public void dispose(){
        basicEnemy.dispose();
    }
}
