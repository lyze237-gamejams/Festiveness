package dev.lyze.festive;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Main extends Game {

    @Override
    public void create() {
        setScreen(new TestScreen());
    }
}
