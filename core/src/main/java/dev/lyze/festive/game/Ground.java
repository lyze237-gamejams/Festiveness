package dev.lyze.festive.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.Player;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateBoxFixtureBehaviour;

public class Ground extends BehaviourAdapter {
    private final float width = 100;

    private final Player player;

    private final Box2dBehaviour physicsBehaviour;

    public Ground(Player player, GameObject gameObject) {
        super(gameObject);

        this.player = player;

        physicsBehaviour = new Box2dBehaviour(BodyDefType.StaticBody, gameObject);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.5f;
        fixtureDef.filter.categoryBits = Constants.Bit_Ground;
        fixtureDef.filter.maskBits = (short) (Constants.Bit_Enemies | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools);

        var hx = width / 2f;
        new CreateBoxFixtureBehaviour(hx, 10f, new Vector2(hx, -8), fixtureDef, gameObject);
    }

    @Override
    public void fixedUpdate() {
        physicsBehaviour.getBody().setTransform(player.getStomach2().getBody().getPosition().x - width / 2f, 0, physicsBehaviour.getBody().getAngle());
    }
}

