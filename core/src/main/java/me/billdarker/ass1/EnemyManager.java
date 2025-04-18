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

    private void moveEnemies(float delta) {
        if (enemies == null) return;

        Iterator<Enemy> iterator = enemies.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            enemy.move(delta);
            if (enemy.checkBounds()) {
                iterator.remove();
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

    /*
    Checks if player bullets and enemies are colliding and removes both if they are
     */
    public void CheckBulletCollision(ArrayList<Bullet> bullets) {
        Iterator<Enemy> enemyIterator = enemies.iterator();

        while (enemyIterator.hasNext()) {
            Enemy enemy = enemyIterator.next();
            Iterator<Bullet> bulletIterator = bullets.iterator();

            while (bulletIterator.hasNext()) {
                Bullet bullet = bulletIterator.next();
                Rectangle bulletRect = bullet.getBounds();

                if (bulletRect.overlaps(enemy.getBounds())) {
                    enemyIterator.remove();
                    bulletIterator.remove();
                    break;
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
