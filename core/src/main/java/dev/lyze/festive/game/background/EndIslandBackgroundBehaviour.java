package dev.lyze.festive.game.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import dev.lyze.festive.Constants;
import dev.lyze.festive.eventsystem.EventListener;
import dev.lyze.festive.eventsystem.events.OnFinalIslandSpawnEvent;
import dev.lyze.festive.game.eventCheckers.OnFinalIslandSpawnEventChecker;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class EndIslandBackgroundBehaviour extends BehaviourAdapter {
    private boolean winConditionMet;

    public EndIslandBackgroundBehaviour(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void onEnable() {
        Constants.events.addListener(new EventListener<>(OnFinalIslandSpawnEvent.class, OnFinalIslandSpawnEventChecker.class) {
            @Override
            protected void fire(OnFinalIslandSpawnEvent event) {
                winConditionMet = true;
            }
        });
    }

    @Override
    public void onDisable() {
        Constants.events.removeListenersOfBind(OnFinalIslandSpawnEventChecker.class);
    }

    @Override
    public void render(Batch batch) {
        if (winConditionMet)
            batch.draw(Constants.assets.getEndIsland(), Constants.viewport.getCamera().position.x - Constants.viewport.getMinWorldWidth()  / 2f, 0, Constants.viewport.getMinWorldWidth(), Constants.viewport.getMinWorldHeight());
    }
}
