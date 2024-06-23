package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.AllArgsConstructor;

import java.util.LinkedList;

public class BackgroundBehaviour extends BehaviourAdapter {
    private final LinkedList<Background> backgrounds = new LinkedList<>();

    public BackgroundBehaviour(GameObject gameObject) {
        super(gameObject);

        new StartIslandBackgroundBehaviour(new GameObject("Start Island", getUnBox()));
        for (int i = 0; i < 3; i++)
            backgrounds.add(new Background(i, 0, null, Constants.assets.getRandomWaterTile()));
        new EndIslandBackgroundBehaviour(new GameObject("End Island", getUnBox()));
    }

    @Override
    public void update(float delta) {
        var cameraPosition = Constants.viewport.getCamera().position;

        var mostLeftX = backgrounds.getFirst().x;
        var mostRightX = backgrounds.getLast().x;

        if (cameraPosition.x > mostRightX * Constants.viewport.getWorldWidth()) {
            backgrounds.removeFirst();
            backgrounds.addLast(new Background(mostRightX + 1, 0, null, Constants.assets.getRandomWaterTile()));
        }

        if (cameraPosition.x - Constants.viewport.getWorldWidth() / 2f < mostLeftX * Constants.viewport.getWorldWidth()) {
            backgrounds.removeLast();
            backgrounds.addFirst(new Background(mostLeftX - 1, 0, null, Constants.assets.getRandomWaterTile()));
        }
    }

    @Override
    public void render(Batch batch) {
        for (int i = backgrounds.size() - 1; i >= 0; i--) {
            backgrounds.get(i).draw(Constants.viewport, batch);
        }
    }

    @AllArgsConstructor
    private static class Background {
        private int x, y;
        private TextureAtlas.AtlasRegion overlay;
        private TextureAtlas.AtlasRegion texture;

        public void draw(FitViewport viewport, Batch batch) {
            batch.draw(texture, x * viewport.getWorldWidth(), y * viewport.getWorldHeight(), viewport.getWorldWidth(), viewport.getWorldHeight());

            if (overlay != null)
                batch.draw(overlay, x * viewport.getWorldWidth(), y * viewport.getWorldHeight(), viewport.getWorldWidth(), viewport.getWorldHeight());
        }
    }
}
