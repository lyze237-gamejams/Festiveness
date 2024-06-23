package dev.lyze.festive.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import dev.lyze.festive.Constants;
import dev.lyze.festive.eventsystem.EventListener;
import dev.lyze.festive.eventsystem.events.Event;
import dev.lyze.festive.eventsystem.events.OnFlingEvent;
import dev.lyze.festive.game.body.Explosion;
import dev.lyze.festive.game.ui.BoostUi;
import dev.lyze.festive.game.ui.JumpUi;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;

import java.util.jar.JarEntry;

@Getter
public class GameInput extends BehaviourAdapter {
    private final InputAdapter input;

    private boolean reachedTakeoff;

    public GameInput(GameObject gameObject) {
        super(gameObject);

        Constants.events.addListener(new EventListener<>(OnFlingEvent.class) {
            @Override
            protected void fire(OnFlingEvent event) {
                reachedTakeoff = true;
            }
        });

        input = new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                if (Input.Buttons.LEFT == button) {
                    if (reachedTakeoff)
                        getUnBox().findBehaviour(JumpUi.class).jump();
                    else
                        getUnBox().findBehaviour(Explosion.class).explode(screenX, screenY);

                    return true;
                }

                return false;
            }

            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.SPACE) {

                    getUnBox().findBehaviour(BoostUi.class).boost();
                    return true;
                }

                return false;
            }
        };
    }
}
