package dev.lyze.festive.game.world;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.Player;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.festive.game.body.physics.init.CreateBodyPartFixtureBehaviour;
import dev.lyze.festive.game.ui.BoostUi;
import dev.lyze.festive.game.ui.StatsUi;
import dev.lyze.festive.game.world.tiles.StuffBehaviour;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import lombok.Getter;

public class CoinBackgroundBehaviour extends StuffBehaviour {
    private Player player;
    private Array<CreateBodyPartFixtureBehaviour> bodyParts;

    public CoinBackgroundBehaviour(float x, float y, GameObject gameObject) {
        super(x, y, Constants.assets.getCoin(), gameObject);
    }

    @Override
    public void start() {
        player = getUnBox().findBehaviour(BalancerBehaviour.class).getPlayer();
        bodyParts = getUnBox().findBehaviours(CreateBodyPartFixtureBehaviour.class, new Array<>());
    }

    @Override
    public void update(float delta) {
        for (var bodyPart : bodyParts) {
            var bodyPosition = bodyPart.getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition();

            Rectangle.tmp.set(getX(), getY(), getWidth(), getHeight());

            if (Rectangle.tmp.contains(bodyPosition)) {
                System.out.println("Coin");
                getUnBox().findBehaviour(StatsUi.class).addScore();
                getGameObject().destroy();
                break;
            }
        }
    }
}
