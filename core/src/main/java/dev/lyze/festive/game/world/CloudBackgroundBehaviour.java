package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dev.lyze.festive.game.world.tiles.StuffBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;

public class CloudBackgroundBehaviour extends StuffBehaviour {
    private float time = 5f;

    public CloudBackgroundBehaviour(float x, float y, TextureAtlas.AtlasRegion cloud, GameObject gameObject) {
        super(x, y, cloud, gameObject);
    }

    @Override
    public void update(float delta) {
        if ((time -= delta) < 0)
            getGameObject().destroy();
    }
}
