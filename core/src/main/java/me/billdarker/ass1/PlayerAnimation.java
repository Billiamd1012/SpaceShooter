package me.billdarker.ass1;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class PlayerAnimation {
    Animation<TextureRegion> flight, turbo;
    float stateTime = 0f;

    /*
        Get the flight and turbo flight states, when no input ship is just flying when there is movement input ship is in turbo mode

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
    Function to get the frames to be displayed
     */
    private TextureRegion[] loadFrames(String prefix, int count) {
        TextureRegion[] frames = new TextureRegion[count];
        for (int i = 0; i < count; i++) {
            frames[i] = new TextureRegion(new Texture(prefix + (i + 1) + ".png"));
        }
        return frames;
    }

    /*
    Function called every frame to get the current frame to display
     */
    public TextureRegion getFrame(boolean isMoving, float delta) {
        stateTime += delta;
        return (isMoving ? turbo : flight).getKeyFrame(stateTime);
    }
    
}
