package dev.lyze.festive.game.world.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.Player;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.festive.game.body.physics.init.CreateBodyPartFixtureBehaviour;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

public abstract class StuffBehaviour extends BehaviourAdapter {
    @Getter private Player player;
    @Getter private Array<CreateBodyPartFixtureBehaviour> bodyParts;

    @Getter @Setter(AccessLevel.PACKAGE) private float x, y, width, height;
    private final TextureAtlas.AtlasRegion texture;

    public StuffBehaviour(float x, float y, TextureAtlas.AtlasRegion texture, GameObject gameObject) {
        super(gameObject);
        this.x = x;
        this.y = y;

        if (texture != null) {
            this.width = texture.originalWidth * Constants.GraphicsPpm;
            this.height = texture.originalHeight * Constants.GraphicsPpm;
        }

        this.texture = texture;
    }

    @Override
    public void start() {
        player = getUnBox().findBehaviour(BalancerBehaviour.class).getPlayer();
        bodyParts = getUnBox().findBehaviours(CreateBodyPartFixtureBehaviour.class, new Array<>());
    }

    @Override
    public void render(Batch batch) {
        batch.draw(texture, x, y, width, height);
    }

    @Override
    public void update(float delta) {
        for (var bodyPart : bodyParts) {
            var bodyPosition = bodyPart.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition();

            Rectangle.tmp.set(getX(), getY(), getWidth(), getHeight());

            if (Rectangle.tmp.contains(bodyPosition)) {
                touched();
                getGameObject().destroy();
                break;
            }
        }
    }

    protected abstract void touched();
}
