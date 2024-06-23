package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class StartIslandBackgroundBehaviour extends BehaviourAdapter {

    public StartIslandBackgroundBehaviour(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void render(Batch batch) {
        batch.draw(Constants.assets.getStartIsland(), 0, 0, Constants.viewport.getWorldWidth(), Constants.viewport.getWorldHeight());
    }
}
