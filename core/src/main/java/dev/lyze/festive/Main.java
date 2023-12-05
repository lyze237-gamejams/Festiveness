package dev.lyze.festive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Main extends Game {

    @Override
    public void create() {
    }

    @Override
    public void render() {
        super.render();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY))
            setScreen(new TestScreen());
    }
}
