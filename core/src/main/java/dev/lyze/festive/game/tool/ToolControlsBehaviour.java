package dev.lyze.festive.game.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import dev.lyze.festive.game.body.Player;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class ToolControlsBehaviour extends BehaviourAdapter {
    private final Vector2 velocity = new Vector2();

    private final Tool tool;

    private boolean left, right, up, down;


    public ToolControlsBehaviour(Tool tool, GameObject gameObject) {
        super(gameObject);
        this.tool = tool;
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

        tool.getBody().setLinearVelocity(velocity.x, velocity.y);
    }
}
