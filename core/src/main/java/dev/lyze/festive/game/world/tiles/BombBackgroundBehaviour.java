package dev.lyze.festive.game.world.tiles;

import dev.lyze.festive.Constants;
import dev.lyze.festive.game.ui.JumpUi;
import dev.lyze.gdxUnBox2d.GameObject;

public class BombBackgroundBehaviour extends StuffBehaviour {
    public BombBackgroundBehaviour(float x, float y, GameObject gameObject) {
        super(x, y, Constants.assets.getBomb(), gameObject);
    }

    @Override
    protected void touched() {
        Constants.assets.playSound(Constants.assets.getBombPickupSound());
        getUnBox().findBehaviour(JumpUi.class).addJump();
    }
}
