package dev.lyze.festive.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import dev.lyze.festive.game.body.Explosion;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;

@Getter
public class GameInput extends BehaviourAdapter {
    private final InputAdapter input;

    public GameInput(GameObject gameObject) {
        super(gameObject);

        input = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (Input.Buttons.LEFT == button) {

                    getUnBox().findBehaviour(Explosion.class).explode(screenX, screenY);

                    return true;
                }

                return false;
            }

            @Override
            public boolean keyDown(int keycode) {
                return super.keyDown(keycode);
            }
        };
    }
}
