package dev.lyze.festive.game.booper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class BooperControlsBehaviour extends BehaviourAdapter {
    private final Vector2 velocity = new Vector2();

    private final Booper booper;

    private boolean left, right, up, down;


    public BooperControlsBehaviour(Booper booper, GameObject gameObject) {
        super(gameObject);
        this.booper = booper;
    }

    @Override
    public void update(float delta) {
        left = Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A);
        right = Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D);
        up = Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W);
        down = Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S);
    }

    @Override
    public void fixedUpdate() {
        velocity.set(0, 0);
        var speed = 10;

        if (left)
            velocity.x = -speed;
        if (right)
            velocity.x = speed;
        if (up)
            velocity.y = speed;
        if (down)
            velocity.y = -speed;

        booper.getBody().setLinearVelocity(velocity.x, velocity.y);
    }
}
