package dev.lyze.festive;

import com.badlogic.gdx.Game;
import dev.lyze.festive.game.TestScreen;

public class Main extends Game {

    @Override
    public void create() {
        setScreen(new TestScreen());
    }
}
