package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;

@Getter
public class SkyBehaviour extends BehaviourAdapter {
    private final Color bottomSkyColor = new Color(210f / 255f, 240f / 255f, 230f / 255f, 1f);
    private final Color topSkyColor = new Color(65 / 255f, 139 / 255f, 199 / 255f, 1f);

    public SkyBehaviour(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void debugRender(ShapeRenderer renderer) {
        renderer.rect(Constants.viewport.getCamera().position.x - Constants.viewport.getWorldWidth() / 2f, 0, Constants.viewport.getWorldWidth(), Constants.StarsSpawnPosition, bottomSkyColor,bottomSkyColor, topSkyColor, topSkyColor);
    }
}
