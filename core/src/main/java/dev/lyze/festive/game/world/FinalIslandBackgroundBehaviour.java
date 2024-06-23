package dev.lyze.festive.game.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import dev.lyze.festive.Constants;
import dev.lyze.festive.eventsystem.EventListener;
import dev.lyze.festive.eventsystem.events.Event;
import dev.lyze.festive.eventsystem.events.OnFinalIslandSpawnEvent;
import dev.lyze.festive.eventsystem.events.OnTouchdownEvent;
import dev.lyze.festive.game.body.Player;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.festive.game.eventCheckers.OnFinalIslandSpawnEventChecker;
import dev.lyze.festive.game.tool.Tool;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class FinalIslandBackgroundBehaviour extends BehaviourAdapter {
    private boolean winConditionMet;
    private Player player;

    private Vector2 finalIslandPosition;

    public FinalIslandBackgroundBehaviour(GameObject gameObject) {
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
    public void start() {
        player = getUnBox().findBehaviour(BalancerBehaviour.class).getPlayer();

        Constants.events.addListener(new EventListener<>(OnTouchdownEvent.class) {
            @Override
            protected void fire(OnTouchdownEvent event) {
                finalIslandPosition = new Vector2(player.getStomach2().getBody().getPosition().x, 0);
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
            batch.draw(Constants.assets.getEndIsland(), (finalIslandPosition == null ? player.getStomach2().getBody().getPosition().x : finalIslandPosition.x) - Constants.viewport.getWorldWidth() / 2f - 2, 0, Constants.viewport.getWorldWidth(), Constants.viewport.getWorldHeight());
    }
}
