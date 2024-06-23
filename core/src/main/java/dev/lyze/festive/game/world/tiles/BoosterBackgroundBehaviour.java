package dev.lyze.festive.game.world.tiles;

import dev.lyze.festive.Constants;
import dev.lyze.festive.game.ui.BoostUi;
import dev.lyze.gdxUnBox2d.GameObject;

public class BoosterBackgroundBehaviour extends StuffBehaviour {
    public BoosterBackgroundBehaviour(float x, float y, GameObject gameObject) {
        super(x, y, Constants.assets.getBooster(), gameObject);
    }

    @Override
    protected void touched() {
        Constants.assets.playSound(Constants.assets.getBoosterPickupSound());
        getUnBox().findBehaviour(BoostUi.class).addBoost();
    }
}
