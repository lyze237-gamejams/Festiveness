package dev.lyze.festive.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.festive.Constants;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.Box2dPhysicsWorld;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateBoxFixtureBehaviour;

public class Ground {
    private GameObject gameObject;
    private Box2dBehaviour physicsBehaviour;

    public Ground(UnBox<Box2dPhysicsWorld> unBox) {
        gameObject = new GameObject(unBox);
        physicsBehaviour = new Box2dBehaviour(BodyDefType.StaticBody, gameObject);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.5f;
        fixtureDef.filter.categoryBits = Constants.Bit_Ground;
        fixtureDef.filter.maskBits = (short) (Constants.Bit_Enemies | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools);

        new CreateBoxFixtureBehaviour(5000 / Constants.PPM, 5 / Constants.PPM, new Vector2(0, -2), fixtureDef, gameObject);
    }
}

