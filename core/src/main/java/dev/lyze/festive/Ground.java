package dev.lyze.festive;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import dev.lyze.gdxUnBox2d.Box2dPhysicsWorld;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.Box2dBehaviour;

public class Ground extends Box2dBehaviour {
    public Ground(Vector2 initPos, Vector2 dim, float density, int cbit, int mbit, GameObject gameObject) {
        super(createBody(initPos, ((Box2dPhysicsWorld) gameObject.getUnBox().getPhysicsWorld())), gameObject);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(dim.x / Constants.PPM, dim.y / Constants.PPM);

        FixtureDef fdef = new FixtureDef();
        fdef.shape = shape;
        fdef.density = density;
        fdef.restitution = 0;
        fdef.friction = 0.5f;
        fdef.filter.categoryBits = (short) cbit;
        fdef.filter.maskBits = (short) mbit;

        getBody().createFixture(fdef);

        shape.dispose();
    }

    private static Body createBody(Vector2 initPos, Box2dPhysicsWorld physicsWorld) {
        BodyDef bdef = new BodyDef();
        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set(initPos.x / Constants.PPM, initPos.y / Constants.PPM);

        return physicsWorld.getWorld().createBody(bdef);
    }
}

