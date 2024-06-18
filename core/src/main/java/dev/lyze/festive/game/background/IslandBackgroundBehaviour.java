package dev.lyze.festive.game.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class IslandBackgroundBehaviour extends BehaviourAdapter {
    private final float x, y;
    private final TextureAtlas.AtlasRegion island;
    private final ExtendViewport viewport;

    public IslandBackgroundBehaviour(int x, int y, ExtendViewport viewport, TextureAtlas.AtlasRegion island, GameObject gameObject) {
        super(gameObject);

        this.x = x;
        this.y = y;
        this.island = island;
        this.viewport = viewport;
    }

    @Override
    public void render(Batch batch) {
        batch.draw(island, x * viewport.getMinWorldWidth(), y * viewport.getMinWorldHeight(), viewport.getMinWorldWidth(), viewport.getMinWorldHeight());
    }
}
