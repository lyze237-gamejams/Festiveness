package dev.lyze.festive.game.world.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import dev.lyze.gdxUnBox2d.GameObject;
import lombok.AllArgsConstructor;
import lombok.Data;

public class StarClusterBackgroundBehaviour extends StuffBehaviour {
    private final Array<Star> stars = new Array<>();

    public StarClusterBackgroundBehaviour(float x, float y, GameObject gameObject) {
        super(x, y, null, gameObject);

        setWidth(1);
        setHeight(1);

        for (int i = 0; i < MathUtils.random(3, 10); i++) {
            stars.add(new Star(MathUtils.random(x, x + getWidth()), MathUtils.random(y, y + getHeight()), MathUtils.random(0.01f, 0.02f)));
        }
    }

    @Override
    public void render(Batch batch) {
    }

    @Override
    public void debugRender(ShapeRenderer renderer) {
        stars.forEach(s -> renderer.circle(s.x, s.y, s.radius, 8));
    }

    @Override
    protected void touched() {

    }

    @Data
    @AllArgsConstructor
    private static class Star {
        private float x, y;
        private float radius;
    }
}
