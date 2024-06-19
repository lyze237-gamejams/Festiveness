package dev.lyze.festive.game.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class CloudBackgroundBehaviour extends BehaviourAdapter {
    private final TextureAtlas.AtlasRegion cloud;
    private final float x, y;

    public CloudBackgroundBehaviour(float x, float y, TextureAtlas.AtlasRegion cloud, GameObject gameObject) {
        super(gameObject);

        this.x = x;
        this.y = y;
        this.cloud = cloud;
    }

    @Override
    public void render(Batch batch) {
        batch.draw(cloud, x, y, cloud.originalWidth * Constants.GraphicsPpm, cloud.originalHeight * Constants.GraphicsPpm);
    }
}
