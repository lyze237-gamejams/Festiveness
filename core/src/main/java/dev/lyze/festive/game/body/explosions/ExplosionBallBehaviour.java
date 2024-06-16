package dev.lyze.festive.game.body.explosions;

import com.badlogic.gdx.physics.box2d.Contact;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.gdxUnBox2d.Behaviour;
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
    public void onCollisionEnter(Behaviour other, Contact contact) {
        var balancer = getUnBox().findBehaviour(BalancerBehaviour.class);
        if (balancer == null)
            return;

        balancer.setEnabled(false);
    }
}
