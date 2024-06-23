package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;

public class CloudBackgroundBehaviour extends BehaviourAdapter {
    private final TextureAtlas.AtlasRegion cloud;
    @Getter private final float x, y;
    @Getter private final float width, height;

    private float time = 5f;

    public CloudBackgroundBehaviour(float x, float y, TextureAtlas.AtlasRegion cloud, GameObject gameObject) {
        super(gameObject);

        this.x = x;
        this.y = y;
        this.width = cloud.originalWidth * Constants.GraphicsPpm;
        this.height = cloud.originalHeight * Constants.GraphicsPpm;
        this.cloud = cloud;
    }

    @Override
    public void update(float delta) {
        //if ((time -= delta) < 0)
        //    getGameObject().destroy();
    }

    @Override
    public void render(Batch batch) {
        batch.draw(cloud, x, y, width, height);
    }
}
