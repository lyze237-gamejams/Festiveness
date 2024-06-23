package dev.lyze.festive.game.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;

public abstract class UiBehaviour<T extends Actor> extends BehaviourAdapter {
    private final Ui ui;

    @Getter private T component;

    public UiBehaviour(Ui ui, GameObject gameObject) {
        super(gameObject);
        this.ui = ui;
    }

    protected abstract T generateComponent();

    @Override
    public void awake() {
        component = generateComponent();
    }

    @Override
    public void onEnable() {
        ui.getStage().addActor(component);
    }

    @Override
    public void onDisable() {
        ui.getStage().getRoot().removeActor(component);
    }
}
