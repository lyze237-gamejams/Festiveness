package dev.lyze.festive.game.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import dev.lyze.festive.ViewportBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

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

        System.out.println("HI");
        clicked = false;
        viewport.getViewport().project(input);

        player.getStomach2().getBody().applyLinearImpulse(0.000001f, 0.00001f, input.x, input.y, true);
    }
}
