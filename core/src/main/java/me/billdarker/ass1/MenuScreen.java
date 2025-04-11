package me.billdarker.ass1;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;
    private Texture startButtonTexture;
    private Texture titleTexture;
    private Texture exitTexture;

    // references to game instance
    Main game;

    public MenuScreen(Main game) {
        this.game = game;
    }


    @Override
    public void show() {
        //setup ui
        Gdx.app.log("MenuScreen: ","menuScreen create");
        batch = new SpriteBatch();
        stage = new Stage();

        //add title and play button
        titleTexture = new Texture("pixel-art-space-shooter-gui/PNG/Main/text_0026_title.png"); // No final needed
        Image titleImage = new Image(titleTexture);
        float titleScale = 3f;
        titleImage.setScale(titleScale);
        titleImage.setPosition(
            (Gdx.graphics.getWidth() - titleImage.getWidth()*titleScale) / 2f,
            (Gdx.graphics.getHeight() - titleImage.getHeight()) / 2f
        );
        stage.addActor(titleImage);

        startButtonTexture = new Texture(Gdx.files.internal("pixel-art-space-shooter-gui/PNG/Pause/pause_0002_play_button.png"));
        TextureRegionDrawable startButtonDrawable = new TextureRegionDrawable(new TextureRegion(startButtonTexture));
        float startScaledWidth = Gdx.graphics.getWidth()/ 14f;
        float startScaledHeight = Gdx.graphics.getWidth()/ 14f;
        startButtonDrawable.setMinSize(startScaledWidth,startScaledHeight);

        final ImageButton startButton = new ImageButton(startButtonDrawable);
        startButton.setPosition(
            (Gdx.graphics.getWidth() - titleImage.getWidth()) / 2f,
            (Gdx.graphics.getHeight() - titleImage.getHeight()) / 3.5f
        );

        stage.addActor(startButton);

        exitTexture = new Texture("pixel-art-space-shooter-gui/PNG/Ingame_Interface/ingame_0028_home.png");
        TextureRegionDrawable exitDrawable = new TextureRegionDrawable(exitTexture);
        float exitScaledWidth = Gdx.graphics.getWidth()/ 20f;
        float exitScaledHeight = Gdx.graphics.getHeight()/ 10f;
        exitDrawable.setMinSize(exitScaledWidth, exitScaledHeight);

        final ImageButton exitButton = new ImageButton(exitDrawable);
        exitButton.setPosition(
            (Gdx.graphics.getWidth() * 0.05f),
            (Gdx.graphics.getHeight() * 0.85f)
        );

        stage.addActor(exitButton);

        Gdx.input.setInputProcessor(stage);

        //on click of start button set screen to game
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(Main.gameScreen);
            }
        });

        exitButton.addListener(new ClickListener(){
           @Override
           public void clicked (InputEvent event, float x, float y) {
               Gdx.app.exit();
           }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        stage.draw();
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
        Gdx.app.log("MenuScreen: ","menuScreen hide called");
    }

    @Override
    public void dispose() {

    }
}
