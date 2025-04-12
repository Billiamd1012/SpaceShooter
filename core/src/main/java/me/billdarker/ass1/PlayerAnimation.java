package me.billdarker.ass1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.ArrayList;

public class PlayerAnimation {
    private Animation<TextureRegion> flight, turbo;
    private float stateTime = 0f;
    private final ArrayList<Texture> texturesToDispose = new ArrayList<>();

    /*
        Constructor takes texture scaling
     */
    public PlayerAnimation(){
        //flight
        flight = new Animation<>(0.1f, loadFrames("pixel-art-alien-spaceship-2d-game-sprites/PNG_Animations/Exhaust/Ship1/Ship1_flight_00", 4));
        flight.setPlayMode(Animation.PlayMode.LOOP);

        //turbo
        turbo = new Animation<>(0.1f, loadFrames("pixel-art-alien-spaceship-2d-game-sprites/PNG_Animations/Exhaust/Ship1/Ship1_turbo_00", 4));
        turbo.setPlayMode(Animation.PlayMode.LOOP);
    }

    /*
        Load frames from individual files and track textures for cleanup
     */
    private TextureRegion[] loadFrames(String prefix, int count) {
        TextureRegion[] frames = new TextureRegion[count];
        for (int i = 0; i < count; i++) {
            Texture texture = new Texture(prefix + (i + 1) + ".png");
            texturesToDispose.add(texture);
            frames[i] = new TextureRegion(texture);
        }
        return frames;
    }

    /*
        Get the current frame to display
     */
    public TextureRegion getFrame(boolean isMoving, float delta) {
        stateTime += delta;
        return (isMoving ? turbo : flight).getKeyFrame(stateTime);
    }

    /*
        Dispose of all textures
     */
    public void dispose(){
        for (Texture texture : texturesToDispose) {
            texture.dispose();
        }
    }
}
