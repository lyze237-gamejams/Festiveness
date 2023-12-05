package dev.lyze.festive.game.body.physics.init;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.Box2dPhysicsWorld;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;

public class BodyPart {
    private final GameObject gameObject;
    private final Box2dBehaviour physicsBehaviour;
    private final CreateBodyPartFixtureBehaviour bodyPartBehaviour;

    public BodyPart(Vector2 initPos, Vector2 dim, float density, int cbit, int mbit, UnBox<Box2dPhysicsWorld> unBox) {
        gameObject = new GameObject(unBox);

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(initPos.x / Constants.PPM, initPos.y / Constants.PPM);
        physicsBehaviour = new Box2dBehaviour(bodyDef, gameObject);

        bodyPartBehaviour = new CreateBodyPartFixtureBehaviour(dim, density, (short) cbit, (short) mbit, gameObject);
    }

    public Body getBody() {
        return physicsBehaviour.getBody();
    }
}