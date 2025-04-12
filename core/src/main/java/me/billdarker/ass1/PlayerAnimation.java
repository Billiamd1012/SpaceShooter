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

        //turbo
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
}
