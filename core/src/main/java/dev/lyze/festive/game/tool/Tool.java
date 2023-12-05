package dev.lyze.festive.game.tool;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.Box2dPhysicsWorld;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateBoxFixtureBehaviour;

public class Tool {
    private final GameObject gameObject;
    private final Box2dBehaviour physicsBehaviour;

    public Tool(UnBox<Box2dPhysicsWorld> unBox) {
        gameObject = new GameObject(unBox);
        physicsBehaviour = new Box2dBehaviour(BodyDefType.KinematicBody, gameObject);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.3f;
        fixtureDef.filter.categoryBits = Constants.Bit_Ground;
        fixtureDef.filter.maskBits = (short) (Constants.Bit_Enemies | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools);

        new CreateBoxFixtureBehaviour(Constants.Length / Constants.PPM, Constants.Length / Constants.PPM, new Vector2(60 / Constants.PPM, 150 / Constants.PPM), fixtureDef, gameObject);
        new ToolControlsBehaviour(this, gameObject);
    }

    public Body getBody() {
        return physicsBehaviour.getBody();
    }
}