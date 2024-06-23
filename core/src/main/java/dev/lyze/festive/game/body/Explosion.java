package dev.lyze.festive.game.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.festive.Constants;
import dev.lyze.festive.eventsystem.events.OnFlingEvent;
import dev.lyze.festive.game.body.explosions.ExplosionBallBehaviour;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

public class Explosion extends BehaviourAdapter {
    private final Vector2 input = new Vector2();
    private final Player player;

    private float explosionEventDelay = 0.2f;
    private boolean shouldFireExplosionEvent = false;

    public Explosion(Player player, GameObject gameObject) {
        super(gameObject);
        this.player = player;
    }

    @Override
    public void update(float delta) {
        if (shouldFireExplosionEvent) {
            if ((explosionEventDelay -= delta)  < 0) {
                Constants.events.fire(new OnFlingEvent());
                shouldFireExplosionEvent = false;
            }
        }
    }

    public void explode(int screenX, int screenY) {
        input.set(screenX, screenY);
        Constants.viewport.unproject(input);

        createExplosion(50);

        Constants.assets.playSound(Constants.assets.getExplosionSound());
        Gdx.graphics.setSystemCursor(Cursor.SystemCursor.Arrow);

        shouldFireExplosionEvent = true;
    }

    private void createExplosion(int totalBalls) {
        for (int i = 0; i < totalBalls; i++) {
            var ball = new GameObject(getUnBox());

            var bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.bullet = true;
            bodyDef.position.set(input);
            bodyDef.linearVelocity.set(new Vector2(50,0).rotateDeg(360f / totalBalls * i));

            new Box2dBehaviour(bodyDef, ball);
            var fixtureDef = new FixtureDef();
            fixtureDef.filter.categoryBits = Constants.Bit_Ground;
            fixtureDef.filter.maskBits = (short) (Constants.Bit_Enemies | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools);
            new CreateCircleFixtureBehaviour(new Vector2(), .1f, fixtureDef, ball);

            new ExplosionBallBehaviour(ball);
        }
    }
}
