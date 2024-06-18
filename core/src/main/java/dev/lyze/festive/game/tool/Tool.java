package dev.lyze.festive.game.tool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.Player;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateBoxFixtureBehaviour;

public class Tool {
    private final GameObject gameObject;
    private final Box2dBehaviour physicsBehaviour;

    public Tool(Player player, UnBox unBox) {
        gameObject = new GameObject(unBox);
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(new Vector2(60 / Constants.BodyPartPpm, 150 / Constants.BodyPartPpm));
        physicsBehaviour = new Box2dBehaviour(bodyDef, gameObject);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.3f;
        fixtureDef.filter.categoryBits = Constants.Bit_Ground;
        fixtureDef.filter.maskBits = (short) (Constants.Bit_Enemies | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools);

        new CreateBoxFixtureBehaviour(Constants.Length / Constants.BodyPartPpm, Constants.Length / Constants.BodyPartPpm, new Vector2(0, 0), fixtureDef, gameObject);
        new ToolControlsBehaviour(this, gameObject);
        new ToolRendererBehaviour(this, player, gameObject);
    }

    public Body getBody() {
        return physicsBehaviour.getBody();
    }
}
