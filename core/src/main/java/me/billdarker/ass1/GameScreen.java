package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;

    private Texture backgroundTexture;
    private Texture floorTexture;
    private Texture characterTexture;

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
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        //draw background
        stage.draw();
        batch.draw(characterTexture, 32, 32, characterTexture.getWidth()*6, characterTexture.getHeight()*6);

        batch.end();
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

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        floorTexture.dispose();
        characterTexture.dispose();
    }
}
