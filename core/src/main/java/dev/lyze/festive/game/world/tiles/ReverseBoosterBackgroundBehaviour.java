package dev.lyze.festive.game.world.tiles;

import dev.lyze.festive.Constants;
import dev.lyze.festive.game.ui.BoostUi;
import dev.lyze.gdxUnBox2d.GameObject;

public class ReverseBoosterBackgroundBehaviour extends StuffBehaviour {
    public ReverseBoosterBackgroundBehaviour(float x, float y, GameObject gameObject) {
        super(x, y, Constants.assets.getReverseBooster(), gameObject);
    }

    @Override
    protected void touched() {
        Constants.assets.playSound(Constants.assets.getReverseBoosterPickupSound());
        getPlayer().reverseBoost();
    }
}
