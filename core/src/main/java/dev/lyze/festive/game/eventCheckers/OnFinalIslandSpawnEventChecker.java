package dev.lyze.festive.game.eventCheckers;

import dev.lyze.festive.Constants;
import dev.lyze.festive.eventsystem.events.OnFinalIslandSpawnEvent;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class OnFinalIslandSpawnEventChecker extends BehaviourAdapter {
    private boolean fired;

    public OnFinalIslandSpawnEventChecker(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void update(float delta) {
        if (!fired && Constants.viewport.getCamera().position.x > Constants.FinalIslandSpawnPosition && Constants.viewport.getCamera().position.y > Constants.viewport.getMinWorldHeight() * 2f) { // win condition
            fired = true;
            Constants.events.fire(new OnFinalIslandSpawnEvent());
        }
    }
}
