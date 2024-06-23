package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class WaterBackgroundBehaviour extends BehaviourAdapter {
    private final int offset;
    private final FitViewport viewport;
    private final Texture overlay;

    private float renderX, renderY;

    public WaterBackgroundBehaviour(int offset, FitViewport viewport, GameObject gameObject) {
        this(offset, viewport, null, gameObject);
    }

    public WaterBackgroundBehaviour(int offset, FitViewport viewport, Texture overlay, GameObject gameObject) {
        super(gameObject);

        this.offset = offset;
        this.viewport = viewport;
        this.overlay = overlay;
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Batch batch) {
        // batch.draw(texture, renderX, renderY, viewport.getMinWorldWidth(), viewport.getMinWorldHeight());

        if (overlay != null)
            batch.draw(overlay, renderX, renderY, viewport.getWorldWidth(), viewport.getWorldHeight());
    }
}
