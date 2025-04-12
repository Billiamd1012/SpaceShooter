package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameScreen implements Screen {

    private final float moveSpeed = 200f;
    private float lastShot;
    private final float shotCooldown = 0.5f;
    private SpriteBatch batch;
    private SpriteBatch uiBatch;
    private Stage stage;
    private Stage ui;
    private EnemyManager enemyManager;

    //load the textures
    private Texture backgroundTexture;
    private Texture gamePadBackground;
    private Texture gamePadUp;
    private Button gamePadUpButton;
    private Texture gamePadDown;
    private Button gamePadDownButton;
    private Texture gamePadLeft;
    private Button gamePadLeftButton;
    private Texture gamePadRight;
    private Button gamePadRightButton;
    private Texture shoot;
    private Button shootButton;

    //reference to game instance
    Main game;

    //and player
    Player player;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen: ","gameScreen created");
        batch = new SpriteBatch();
        uiBatch = new SpriteBatch();
        stage = new Stage();
        ui = new Stage();
        enemyManager = new EnemyManager();

        // add background texture
        backgroundTexture = new Texture("pixel-art-space-2d-game-backgrounds/original_size/PNG/Space4/Bright/Space4.png");
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        //player
        player = new Player();

        //gamepad texture
        gamePadBackground = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0035_gamepad.png");
        gamePadUp = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0034_arrow.png");
        gamePadDown = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0033_arrow-copy.png");
        gamePadLeft = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0031_arrow-copy-2.png");
        gamePadRight = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0032_arrow-copy-3.png");
        shoot = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0030_fire.png");

        //position gamepad buttons
        float gamepadScaling = 3f;

        Image gamePadBackgroundImage = new Image(gamePadBackground);
        gamePadBackgroundImage.setScale(gamepadScaling);
        gamePadBackgroundImage.setPosition((0.85f*Gdx.graphics.getWidth()),(0.10f*Gdx.graphics.getHeight()));
        ui.addActor(gamePadBackgroundImage);

        float GPU_x = Gdx.graphics.getWidth() * 0.89f;
        float GPU_y = Gdx.graphics.getHeight() * 0.28f;
        float GPU_w = gamePadUp.getWidth() * gamepadScaling;
        float GPU_h = gamePadUp.getHeight() * gamepadScaling;
        gamePadUpButton = new Button(GPU_x, GPU_y, GPU_w, GPU_h, gamePadUp, gamePadUp);

        float GPD_x = Gdx.graphics.getWidth() * 0.89f;
        float GPD_y = Gdx.graphics.getHeight() * 0.13f;
        float GPD_w = gamePadDown.getWidth() * gamepadScaling;
        float GPD_h = gamePadDown.getHeight() * gamepadScaling;
        gamePadDownButton = new Button(GPD_x, GPD_y, GPD_w, GPD_h, gamePadDown, gamePadDown);

        float GPL_x = Gdx.graphics.getWidth()*0.86f;
        float GPL_y = Gdx.graphics.getHeight()*0.19f;
        float GPL_w = gamePadLeft.getWidth()*gamepadScaling;
        float GPL_h = gamePadLeft.getHeight()*gamepadScaling;
        gamePadLeftButton = new Button(GPL_x,GPL_y,GPL_w,GPL_h,gamePadLeft, gamePadLeft);

        float GPR_x = Gdx.graphics.getWidth()*0.935f;
        float GPR_y = Gdx.graphics.getHeight()*0.19f;
        float GPR_w = gamePadRight.getWidth()*gamepadScaling;
        float GPR_h = gamePadRight.getHeight()*gamepadScaling;
        gamePadRightButton = new Button(GPR_x,GPR_y,GPR_w,GPR_h,gamePadRight, gamePadRight);

        float shoot_x = Gdx.graphics.getWidth()*0.1f;
        float shoot_y = Gdx.graphics.getHeight()*0.19f;
        float shoot_w = shoot.getWidth()*gamepadScaling;
        float shoot_h = shoot.getHeight()*gamepadScaling;
        shootButton = new Button(shoot_x,shoot_y,shoot_w,shoot_h,shoot,shoot);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        //floorTexture.dispose();
        gamePadBackground.dispose();
        gamePadUp.dispose();
        gamePadDown.dispose();
        gamePadLeft.dispose();
        gamePadRight.dispose();
        shoot.dispose();
        enemyManager.dispose();
        player.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //update game state
        update(delta);

        batch.begin();
        //draw background
        stage.draw();
        //draw enemies
        enemyManager.drawEnemies(batch);
        //draw character
        player.draw(batch,delta);
        batch.end();

        //draw separate ui batch to controls appear above everything else
        uiBatch.begin();

        //draw controls
        ui.draw();
        gamePadUpButton.draw(uiBatch);
        gamePadDownButton.draw(uiBatch);
        gamePadLeftButton.draw(uiBatch);
        gamePadRightButton.draw(uiBatch);
        shootButton.draw(uiBatch);
        uiBatch.end();

    }
    /**Method for all game logic. This method is called at the start of GameCore.render() before
     * any actual drawing is done. */
    private void update(float deltaTime) {
        //spawn enemies
        enemyManager.spawnLevel1(deltaTime);

        boolean checkTouch = Gdx.input.isTouched();
        int touchX = Gdx.input.getX();
        int touchY = Gdx.input.getY();

        //poll movement
        gamePadUpButton.update(checkTouch, touchX, touchY);
        gamePadDownButton.update(checkTouch, touchX, touchY);
        gamePadLeftButton.update(checkTouch, touchX, touchY);
        gamePadRightButton.update(checkTouch, touchX, touchY);
        shootButton.update(checkTouch,touchX,touchY);

        //calculate movement movement
        float moveX = 0;
        float moveY = 0;
        if (gamePadUpButton.isDown){
            moveY += moveSpeed*deltaTime;
        }
        if (gamePadDownButton.isDown){
            moveY -= moveSpeed*deltaTime;
        }
        if (gamePadLeftButton.isDown){
            moveX -= moveSpeed*deltaTime;
        }
        if (gamePadRightButton.isDown){
            moveX += moveSpeed*deltaTime;
        }
        if (shootButton.isDown){
            if (lastShot >= shotCooldown){
                player.shoot();
                lastShot = 0f;
            }
        }
        lastShot += deltaTime;

        //Gdx.app.log("Movement","X "+moveX+" Y "+moveY);
        player.move(moveX,moveY);
        enemyManager.CheckBulletCollision(player.bulletsUpdate(deltaTime));

        //check collisions
        Rectangle playerBound = player.getBounds();
        //Gdx.app.log("Collision","Player has box height:" +playerBound.height +" width: " +playerBound.width + " at x,y: "+playerBound.x+", "+playerBound.y);

        //if enemy hits the player then end the game
        if (enemyManager.CheckPlayerCollision(playerBound)){
            Gdx.app.log("Collision","Player hit an enemy");
        }

    }
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
