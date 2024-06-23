package dev.lyze.festive.game.world.tiles;

import dev.lyze.festive.Constants;
import dev.lyze.festive.game.ui.StatsUi;
import dev.lyze.gdxUnBox2d.GameObject;

public class CoinBackgroundBehaviour extends StuffBehaviour {
    public CoinBackgroundBehaviour(float x, float y, GameObject gameObject) {
        super(x, y, Constants.assets.getCoin(), gameObject);
    }

    @Override
    protected void touched() {
        Constants.assets.playSound(Constants.assets.getCoinSound());
        getUnBox().findBehaviour(StatsUi.class).addScore();
    }
}
