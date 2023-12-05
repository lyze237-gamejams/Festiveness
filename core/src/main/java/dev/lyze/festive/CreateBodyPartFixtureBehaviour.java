package dev.lyze.festive;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateFixtureBehaviour;

public class CreateBodyPartFixtureBehaviour extends CreateFixtureBehaviour {
    private final PolygonShape shape;

    public CreateBodyPartFixtureBehaviour(Vector2 dim, float density, short cbit, short mbit, GameObject gameObject) {
        super(createFixture(density, cbit, mbit), gameObject);

        shape = new PolygonShape();
        shape.setAsBox(dim.x / Constants.PPM, dim.y / Constants.PPM);
    }

    @Override
    public void awake() {
        createAndAttachFixture(shape);
    }

    public static FixtureDef createFixture(float density, short cbit, short mbit) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = density;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.5f;
        fixtureDef.filter.categoryBits = cbit;
        fixtureDef.filter.maskBits = mbit;

        return fixtureDef;
    }
}
