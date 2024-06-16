package dev.lyze.festive.game.background;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.lyze.festive.game.Assets;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class BackgroundBehaviour extends BehaviourAdapter {

    private final ExtendViewport viewport;

    public BackgroundBehaviour(ExtendViewport viewport, GameObject gameObject) {
        super(gameObject);

        this.viewport = viewport;

        new WaterBackgroundBehaviour(0, 0, Assets.getStartIsland(), Assets.getRandomWaterTile(), this.viewport, new GameObject("Start Island", getUnBox()));
    }

    @Override
    public void update(float delta) {
    }
}
