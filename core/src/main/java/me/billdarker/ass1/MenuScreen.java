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
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuScreen implements Screen {
    private SpriteBatch batch;
    private Stage stage;

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
        Texture titleTexture = new Texture("pixel-art-space-shooter-gui/PNG/Main/text_0026_title.png"); // No final needed
        Image titleImage = new Image(titleTexture);
        float titleScale = 3f;
        titleImage.setScale(titleScale);
        titleImage.setPosition(
            (Gdx.graphics.getWidth() - titleImage.getWidth()*titleScale) / 2f,
            (Gdx.graphics.getHeight() - titleImage.getHeight()) / 2f
        );
        stage.addActor(titleImage);

        Texture startButtonTexture = new Texture(Gdx.files.internal("pixel-art-space-shooter-gui/PNG/Main/main_0006_buttons.png"));
        TextureRegionDrawable startButtonDrawable = new TextureRegionDrawable(new TextureRegion(startButtonTexture));
        final ImageButton startButton = new ImageButton(startButtonDrawable);
        startButton.setPosition(
            (Gdx.graphics.getWidth() - titleImage.getWidth()) / 2f,
            (Gdx.graphics.getHeight() - titleImage.getHeight()) / 3f
        );

        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);

        //on click of start button set screen to game
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                game.setScreen(Main.gameScreen);
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
