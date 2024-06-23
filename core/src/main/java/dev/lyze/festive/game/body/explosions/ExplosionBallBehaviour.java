package dev.lyze.festive.game.body.explosions;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.gdxUnBox2d.Behaviour;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class ExplosionBallBehaviour extends BehaviourAdapter {
    private static final float totalAliveTime = 0.5f;
    private float aliveTime;

    public ExplosionBallBehaviour(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void update(float delta) {
        aliveTime += delta;

        if (aliveTime > totalAliveTime)
            getUnBox().destroy(getGameObject());
    }

    @Override
    public void render(Batch batch) {
        Vector2 position = getGameObject().getBehaviour(Box2dBehaviour.class).getBody().getPosition();

        var radius = 0.15f;
        batch.draw(Constants.assets.getBomb(), position.x - radius, position.y - radius, radius * 2, radius * 2);
    }

    @Override
    public void onCollisionEnter(Behaviour other, Contact contact) {
        var balancer = getUnBox().findBehaviour(BalancerBehaviour.class);
        if (balancer == null)
            return;

        balancer.setEnabled(false);
    }
}
