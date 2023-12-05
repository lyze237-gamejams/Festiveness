package dev.lyze.festive;

import com.badlogic.gdx.utils.viewport.Viewport;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;

public class ViewportBehaviour extends BehaviourAdapter {
    @Getter private final Viewport viewport;

    public ViewportBehaviour(Viewport viewport, GameObject gameObject) {
        super(gameObject);
        this.viewport = viewport;
    }
}
