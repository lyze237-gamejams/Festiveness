package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;

@Getter
public class SpaceBehaviour extends BehaviourAdapter {
    private final Color bottomSkyColor = new Color(65 / 255f, 139 / 255f, 199 / 255f, 1f);
    private final Color topSkyColor = new Color(5f / 255f, 24f / 255f, 89f / 255f, 1f);
    private final Viewport viewport;

    public SpaceBehaviour(Viewport viewport, GameObject gameObject) {
        super(gameObject);
        this.viewport = viewport;
    }

    @Override
    public void debugRender(ShapeRenderer renderer) {
        renderer.rect(viewport.getCamera().position.x - viewport.getWorldWidth() / 2f, Constants.StarsSpawnPosition, viewport.getWorldWidth(), Constants.StarsSpawnPosition + 100, bottomSkyColor, bottomSkyColor, topSkyColor, topSkyColor);
    }
}
