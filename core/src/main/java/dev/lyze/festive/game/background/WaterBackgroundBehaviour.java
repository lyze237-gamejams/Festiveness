package dev.lyze.festive.game.background;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import space.earlygrey.shapedrawer.ShapeDrawer;

public class WaterBackgroundBehaviour extends BehaviourAdapter {
    private final float x, y;
    private final Texture overlay;
    private final Texture water;
    private final ExtendViewport viewport;

    public WaterBackgroundBehaviour(float x, float y, Texture water, ExtendViewport viewport, GameObject gameObject) {
        this(x, y, null, water, viewport, gameObject);
    }

    public WaterBackgroundBehaviour(float x, float y, Texture overlay, Texture water, ExtendViewport viewport, GameObject gameObject) {
        super(gameObject);

        this.x = x;
        this.y = y;
        this.overlay = overlay;
        this.water = water;
        this.viewport = viewport;
    }

    @Override
    public void update(float delta) {
        if (viewport.getCamera().position.x - viewport.getWorldWidth() / 2f > x + viewport.getWorldWidth())
            getGameObject().destroy();
    }

    @Override
    public void render(Batch batch) {
        batch.draw(water, x, y, viewport.getMinWorldWidth(), viewport.getMinWorldHeight());

        if (overlay != null)
            batch.draw(overlay, x, y, viewport.getMinWorldWidth(), viewport.getMinWorldHeight());
     }

    @Override
    public void debugRender(ShapeRenderer renderer) {
        renderer.setColor(Color.GREEN);
        renderer.circle(viewport.getCamera().position.x, viewport.getCamera().position.y, 0.2f, 10);
        renderer.setColor(Color.RED);
        renderer.circle(x, y, 0.2f, 10);
        renderer.circle(x + viewport.getWorldWidth(), y, 0.2f, 10);

        renderer.setColor(Color.ORANGE);
        renderer.rect(x, y, 16, 9);

        renderer.setColor(Color.PURPLE);
        renderer.rect(viewport.getCamera().position.x - viewport.getWorldWidth() / 2f, viewport.getCamera().position.y - viewport.getWorldHeight() / 2f, viewport.getWorldWidth(), viewport.getWorldHeight());
    }
}
