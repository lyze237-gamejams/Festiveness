package dev.lyze.festive.game.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.festive.Constants;
import dev.lyze.festive.ViewportBehaviour;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateCircleFixtureBehaviour;

public class Explosion extends BehaviourAdapter {
    private final Vector2 input = new Vector2();
    private final Player player;

    private boolean clicked;
    private ViewportBehaviour viewport;

    public Explosion(Player player, GameObject gameObject) {
        super(gameObject);
        this.player = player;
    }

    @Override
    public void update(float delta) {
        if (!Gdx.input.isButtonJustPressed(Input.Buttons.LEFT))
            return;

        input.set(Gdx.input.getX(), Gdx.input.getY());
        clicked = true;
    }

    @Override
    public void start() {
        viewport = getUnBox().findBehaviours(ViewportBehaviour.class).get(0);
    }

    @Override
    public void fixedUpdate() {
        if (!clicked)
            return;

        clicked = false;
        viewport.getViewport().unproject(input);

        createExplosion();
    }

    private void createExplosion() {
        for (int i = 0; i < 25; i++) {
            var ball = new GameObject(getUnBox());
            var bodyDef = new BodyDef();
            bodyDef.type = BodyDef.BodyType.DynamicBody;
            bodyDef.position.set(input);
            bodyDef.linearVelocity.set(new Vector2(10,0).rotateDeg(MathUtils.random(360)));
            new Box2dBehaviour(bodyDef, ball);
            var fixtureDef = new FixtureDef();
            fixtureDef.filter.categoryBits = Constants.Bit_Ground;
            fixtureDef.filter.maskBits = (short) (Constants.Bit_Enemies | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools);
            new CreateCircleFixtureBehaviour(new Vector2(), .1f, fixtureDef, ball);
        }
    }
}
