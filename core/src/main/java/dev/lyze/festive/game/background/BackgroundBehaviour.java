package dev.lyze.festive.game.background;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.Assets;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.AllArgsConstructor;

import java.util.LinkedList;

public class BackgroundBehaviour extends BehaviourAdapter {
    private final ExtendViewport viewport;

    private final LinkedList<Background> backgrounds = new LinkedList<>();

    public BackgroundBehaviour(ExtendViewport viewport, GameObject gameObject) {
        super(gameObject);

        this.viewport = viewport;

        // backgrounds.add(new Background(0, 0, Assets.getStartIsland(), Assets.getRandomWaterTile()));
        for (int i = 0; i < 3; i++) {
            backgrounds.add(new Background(i, 0, null, Constants.assets.getRandomWaterTile()));
        }
    }

    @Override
    public void update(float delta) {
        var cameraPosition = viewport.getCamera().position;

        var mostLeftX = backgrounds.getFirst().x;
        var mostRightX = backgrounds.getLast().x;

        if (cameraPosition.x > mostRightX * viewport.getMinWorldWidth()) {
            backgrounds.removeFirst();
            backgrounds.addLast(new Background(mostRightX + 1, 0, null, Constants.assets.getRandomWaterTile()));
        }

        if (cameraPosition.x - viewport.getWorldWidth() / 2f < mostLeftX * viewport.getMinWorldWidth()) {
            backgrounds.removeLast();
            backgrounds.addFirst(new Background(mostLeftX - 1, 0, null, Constants.assets.getRandomWaterTile()));
        }
    }

    @Override
    public void render(Batch batch) {
        for (int i = backgrounds.size() - 1; i >= 0; i--) {
            backgrounds.get(i).draw(viewport, batch);
        }
    }

    @AllArgsConstructor
    private static class Background {
        private int x, y;
        private TextureAtlas.AtlasRegion overlay;
        private TextureAtlas.AtlasRegion texture;

        public void draw(ExtendViewport viewport, Batch batch) {
            batch.draw(texture, x * viewport.getMinWorldWidth(), y * viewport.getMinWorldHeight(), viewport.getMinWorldWidth(), viewport.getMinWorldHeight());

            if (overlay != null)
                batch.draw(overlay, x * viewport.getMinWorldWidth(), y * viewport.getMinWorldHeight(), viewport.getMinWorldWidth(), viewport.getMinWorldHeight());
        }
    }
}
