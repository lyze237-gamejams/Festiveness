package dev.lyze.festive.game.world.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;

public abstract class StuffBehaviour extends BehaviourAdapter {
    @Getter private final float x, y, width, height;
    private final TextureAtlas.AtlasRegion texture;

    public StuffBehaviour(float x, float y, TextureAtlas.AtlasRegion texture, GameObject gameObject) {
        super(gameObject);
        this.x = x;
        this.y = y;

        this.width = texture.originalWidth * Constants.GraphicsPpm;
        this.height = texture.originalHeight * Constants.GraphicsPpm;

        this.texture = texture;
    }

    @Override
    public void render(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }
}
