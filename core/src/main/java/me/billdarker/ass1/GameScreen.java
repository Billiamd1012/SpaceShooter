package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private EnemySpawner enemySpawner;

    //load the textures
    private Texture backgroundTexture;
    private Texture floorTexture;
    private Texture characterTexture;
    private Texture gamePadBackground;
    private Texture gamePadUp;
    private Button gamePadUpButton;
    private Texture gamePadDown;
    private Button gamePadDownButton;
    private Texture gamePadLeft;
    private Button gamePadLeftButton;
    private Texture gamePadRight;
    private Button gamePadRightButton;

    //character state variables
    private float characterX = 32;
    private float characterY = 32;

    //reference to game instance
    Main game;

    public GameScreen(Main game) {
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.app.log("GameScreen: ","gameScreen created");
        batch = new SpriteBatch();
        stage = new Stage();
        enemySpawner = new EnemySpawner();

        // add background texture
        backgroundTexture = new Texture("pixel-art-space-2d-game-backgrounds/original_size/PNG/Space5/Bright/Space5.png");
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(backgroundImage);

        //add floor texture
        floorTexture = new Texture("pixel-art-space-shooter-game-tileset/Tiles/Metal/metal_0000_tile.png");
        float floorScale = 5f;

        float scaledWidth = floorTexture.getWidth() * floorScale;
        float scaledHeight = floorTexture.getHeight() * floorScale;

        int tileCount = (int) Math.ceil(Gdx.graphics.getWidth() / scaledWidth);
        for (int i = 0; i < tileCount; i++) {
            Image floorImage = new Image(floorTexture);
            floorImage.setSize(scaledWidth,scaledHeight);
            floorImage.setPosition(i * scaledWidth, -(scaledHeight-(scaledHeight/3)));
            stage.addActor(floorImage);
        }

        //character texture
        characterTexture = new Texture("pixel-art-enemy-spaceship-2d-sprites/PNG_Parts&Spriter_Animation/Ship1/Ship1.png");

        //gamepad texture
        gamePadBackground = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0035_gamepad.png");
        gamePadUp = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0034_arrow.png");
        TextureRegionDrawable gamePadUpDrawable = new TextureRegionDrawable(gamePadUp);
        gamePadDown = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0033_arrow-copy.png");
        TextureRegionDrawable gamePadDownDrawable = new TextureRegionDrawable(gamePadDown);
        gamePadLeft = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0031_arrow-copy-2.png");
        TextureRegionDrawable gamePadLeftDrawable = new TextureRegionDrawable(gamePadLeft);
        gamePadRight = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0032_arrow-copy-3.png");
        TextureRegionDrawable gamePadRightDrawable = new TextureRegionDrawable(gamePadRight);

        //position gamepad buttons
        float gamepadScaling = 3f;

        Image gamePadBackgroundImage = new Image(gamePadBackground);
        gamePadBackgroundImage.setScale(gamepadScaling);
        gamePadBackgroundImage.setPosition((0.85f*Gdx.graphics.getWidth()),(0.15f*Gdx.graphics.getHeight()));
        stage.addActor(gamePadBackgroundImage);

        float GPU_x = Gdx.graphics.getWidth() * 0.89f;
        float GPU_y = Gdx.graphics.getHeight() * 0.33f;
        float GPU_w = gamePadUp.getWidth() * gamepadScaling;
        float GPU_h = gamePadUp.getHeight() * gamepadScaling;
        gamePadUpButton = new Button(GPU_x, GPU_y, GPU_w, GPU_h, gamePadUp, gamePadUp);

        float GPD_x = Gdx.graphics.getWidth() * 0.89f;
        float GPD_y = Gdx.graphics.getHeight() * 0.18f;
        float GPD_w = gamePadDown.getWidth() * gamepadScaling;
        float GPD_h = gamePadDown.getHeight() * gamepadScaling;
        gamePadDownButton = new Button(GPD_x, GPD_y, GPD_w, GPD_h, gamePadDown, gamePadDown);

        float GPL_x = Gdx.graphics.getWidth()*0.86f;
        float GPL_y = Gdx.graphics.getHeight()*0.24f;
        float GPL_w = gamePadLeft.getWidth()*gamepadScaling;
        float GPL_h = gamePadLeft.getHeight()*gamepadScaling;
        gamePadLeftButton = new Button(GPL_x,GPL_y,GPL_w,GPL_h,gamePadLeft, gamePadLeft);

        float GPR_x = Gdx.graphics.getWidth()*0.935f;
        float GPR_y = Gdx.graphics.getHeight()*0.24f;
        float GPR_w = gamePadRight.getWidth()*gamepadScaling;
        float GPR_h = gamePadRight.getHeight()*gamepadScaling;
        gamePadRightButton = new Button(GPR_x,GPR_y,GPR_w,GPR_h,gamePadRight, gamePadRight);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        floorTexture.dispose();
        characterTexture.dispose();
        gamePadBackground.dispose();
        gamePadUp.dispose();
        gamePadDown.dispose();
        gamePadLeft.dispose();
        gamePadRight.dispose();
        enemySpawner.dispose();
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
        //draw controls
        gamePadUpButton.draw(batch);
        gamePadDownButton.draw(batch);
        gamePadLeftButton.draw(batch);
        gamePadRightButton.draw(batch);
        //draw character
        batch.draw(characterTexture, characterX, characterY, characterTexture.getWidth()*6, characterTexture.getHeight()*6);

        //draw enemies
        enemySpawner.drawEnemies(batch);
        batch.end();
    }
    /**Method for all game logic. This method is called at the start of GameCore.render() before
     * any actual drawing is done. */
    private void update(float deltaTime) {
        //spawn enemies
        enemySpawner.spawnLevel1(deltaTime);

        boolean checkTouch = Gdx.input.isTouched();
        int touchX = Gdx.input.getX();
        int touchY = Gdx.input.getY();

        //poll movement
        gamePadUpButton.update(checkTouch, touchX, touchY);
        gamePadDownButton.update(checkTouch, touchX, touchY);
        gamePadLeftButton.update(checkTouch, touchX, touchY);
        gamePadRightButton.update(checkTouch, touchX, touchY);

        //calculate movement movement
        float moveX = 0;
        float moveY = 0;
        float moveSpeed = 10f;
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

        //out of bounds check
        float nextX = characterX + moveX;
        float nextY = characterY + moveY;
        if (nextX >= 0
            && nextX <= Gdx.graphics.getWidth()
            && nextY >= 0
            && nextY <= Gdx.graphics.getHeight()){
            //if passing add move steps
            characterY += moveY;
            characterX += moveX;
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
