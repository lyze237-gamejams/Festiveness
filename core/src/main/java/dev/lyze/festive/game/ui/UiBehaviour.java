package dev.lyze.festive.game.ui;

import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public abstract class UiBehaviour<T> extends BehaviourAdapter {
    public UiBehaviour(GameObject gameObject) {
        super(gameObject);
    }

    public abstract T generateComponent();
}
