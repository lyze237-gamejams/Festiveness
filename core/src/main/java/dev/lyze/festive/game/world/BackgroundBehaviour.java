package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.OrderedMap;
import com.badlogic.gdx.utils.viewport.FitViewport;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.world.tiles.*;
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
        new FinalIslandBackgroundBehaviour(new GameObject("End Island", getUnBox()));
    }

    @Override
    public void update(float delta) {
        var cameraPosition = Constants.viewport.getCamera().position;

        var mostLeftX = backgrounds.getFirst().x;
        var mostRightX = backgrounds.getLast().x;

        var after = createdAfterIfNeeded(cameraPosition, mostRightX);
        if (after != null)
            createStuffs();

        var before = createBeforeIfNeeded(cameraPosition, mostLeftX);
        if (before != null)
            createStuffs();
    }

    private final ObjectMap<Stuff, Array<StuffBehaviour>> stuffs = new OrderedMap<>();
    private void createStuffs() {
        createStuff(Stuff.CLOUD, MathUtils.random(20, 100));
        createStuff(Stuff.COIN, MathUtils.random(0, 3));
        createStuff(Stuff.BOMB, MathUtils.random(0, 1));
        createStuff(Stuff.BOOSTER, MathUtils.random(0, 2));
        createStuff(Stuff.REVERSE_BOOSTER, MathUtils.random(0, 1));
    }

    private void createStuff(Stuff stuff, int amount) {
        if (!stuffs.containsKey(stuff))
            stuffs.put(stuff, new Array<>());

        var x = Constants.viewport.getCamera().position.x;
        var y = Constants.viewport.getCamera().position.y;

        if (y < Constants.viewport.getWorldHeight() * 2)
            return;

        var bottomLeftX = x - Constants.viewport.getWorldWidth() / 2f;
        var bottomLeftY = y - Constants.viewport.getWorldHeight() / 2f - Constants.viewport.getWorldHeight();

        var topRightX = x + Constants.viewport.getWorldWidth() / 2f + Constants.viewport.getWorldWidth();
        var topRightY = y + Constants.viewport.getWorldHeight() / 2f + Constants.viewport.getWorldHeight();

        for (int amountIndex = 0; amountIndex < amount; amountIndex++) {
            addThing(stuff, bottomLeftX, bottomLeftY, topRightX, topRightY);
        }
    }

    private void addThing(Stuff stuff, float bottomLeftX, float bottomLeftY, float topRightX, float topRightY) {
        for (int tries = 0; tries < 10; tries++) {
            var sprite = Constants.assets.getRandomCloudTile();

            var x = MathUtils.random(bottomLeftX, topRightX);
            var y = MathUtils.random(bottomLeftY, topRightY);
            var width = sprite.originalWidth * Constants.GraphicsPpm;
            var height = sprite.originalHeight * Constants.GraphicsPpm;

            Rectangle.tmp.set(x, y, width, height);

            Rectangle.tmp2.set(
                Constants.viewport.getCamera().position.x - Constants.viewport.getWorldWidth() / 2f,
                Constants.viewport.getCamera().position.y - Constants.viewport.getWorldHeight() / 2f,
                Constants.viewport.getWorldWidth(),
                Constants.viewport.getWorldHeight()
            );

            if (Rectangle.tmp.overlaps(Rectangle.tmp2))
                continue;

            var cool = true;
            for (var obj : stuffs.get(stuff)) {
                Rectangle.tmp2.set(obj.getX(), obj.getY(), obj.getWidth(), obj.getHeight());
                if (Rectangle.tmp.overlaps(Rectangle.tmp2)) {
                    cool = false;
                    break;
                }
            }

            if (cool) {
                switch (stuff) {
                    case CLOUD:
                        stuffs.get(stuff).add(new CloudBackgroundBehaviour(x, y, sprite, new GameObject("Cloud", getUnBox())));
                        break;
                    case COIN:
                        stuffs.get(stuff).add(new CoinBackgroundBehaviour(x, y, new GameObject("Coin", getUnBox())));
                        break;
                    case BOOSTER:
                        stuffs.get(stuff).add(new BoosterBackgroundBehaviour(x, y, new GameObject("Booster", getUnBox())));
                        break;
                    case BOMB:
                        stuffs.get(stuff).add(new BombBackgroundBehaviour(x, y, new GameObject("Bomb", getUnBox())));
                        break;
                    case REVERSE_BOOSTER:
                        stuffs.get(stuff).add(new ReverseBoosterBackgroundBehaviour(x, y, new GameObject("Reverse Bomb", getUnBox())));
                        break;
                }
                return;
            }
        }
    }

    private Background createBeforeIfNeeded(Vector3 cameraPosition, int mostLeftX) {
        if (cameraPosition.x - Constants.viewport.getWorldWidth() / 2f < mostLeftX * Constants.viewport.getWorldWidth()) {
            backgrounds.removeLast();
            var bg = new Background(mostLeftX - 1, 0, null, Constants.assets.getRandomWaterTile());
            backgrounds.addFirst(bg);
            return bg;
        }

        return null;
    }

    private Background createdAfterIfNeeded(Vector3 cameraPosition, int mostRightX) {
        if (cameraPosition.x > mostRightX * Constants.viewport.getWorldWidth()) {
            backgrounds.removeFirst();
            var bg = new Background(mostRightX + 1, 0, null, Constants.assets.getRandomWaterTile());
            backgrounds.addLast(bg);
            return bg;
        }

        return null;
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

    private enum Stuff {
        CLOUD, COIN, BOOSTER, BOMB, REVERSE_BOOSTER
    }
}
