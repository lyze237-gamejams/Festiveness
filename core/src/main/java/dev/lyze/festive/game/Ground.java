package dev.lyze.festive.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import dev.lyze.festive.Constants;
import dev.lyze.festive.eventsystem.EventListener;
import dev.lyze.festive.eventsystem.events.Event;
import dev.lyze.festive.eventsystem.events.OnFinalIslandSpawnEvent;
import dev.lyze.festive.eventsystem.events.OnFlingEvent;
import dev.lyze.festive.eventsystem.events.OnTouchdownEvent;
import dev.lyze.festive.game.body.Player;
import dev.lyze.festive.game.body.physics.init.BodyPart;
import dev.lyze.gdxUnBox2d.Behaviour;
import dev.lyze.gdxUnBox2d.BodyDefType;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import dev.lyze.gdxUnBox2d.behaviours.fixtures.CreateBoxFixtureBehaviour;

public class Ground extends BehaviourAdapter {
    private final float width = 100;

    private final Player player;

    private final Box2dBehaviour physicsBehaviour;

    private boolean morganFlinged, morganTouchedDown;
    private boolean finalIslandSpawned;

    public Ground(Player player, GameObject gameObject) {
        super(gameObject);

        this.player = player;

        physicsBehaviour = new Box2dBehaviour(BodyDefType.StaticBody, gameObject);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.density = 1;
        fixtureDef.restitution = 0;
        fixtureDef.friction = 0.5f;
        fixtureDef.filter.categoryBits = Constants.Bit_Ground;
        fixtureDef.filter.maskBits = (short) (Constants.Bit_Enemies | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools);

        var hx = width / 2f;
        new CreateBoxFixtureBehaviour(hx, 10f, new Vector2(hx, -8), fixtureDef, gameObject);
    }

    @Override
    public void start() {
        Constants.events.addListener(new EventListener<>(OnFinalIslandSpawnEvent.class) {
            @Override
            protected void fire(OnFinalIslandSpawnEvent event) {
                finalIslandSpawned = true;
            }
        });

        Constants.events.addListener(new EventListener<>(OnFlingEvent.class) {
            @Override
            protected void fire(OnFlingEvent event) {
                morganFlinged = true;
            }
        });
    }

    @Override
    public void fixedUpdate() {
        physicsBehaviour.getBody().setTransform(player.getStomach2().getBody().getPosition().x - width / 2f, 0, physicsBehaviour.getBody().getAngle());
    }

    @Override
    public void onCollisionEnter(Behaviour other, Contact contact) {
        if (!morganFlinged)
            return;

        if (morganTouchedDown)
            return;

        if (finalIslandSpawned) {
            Constants.assets.getMainMusic().stop();

            Constants.assets.getWinMusic().setLooping(true);
            Constants.assets.getWinMusic().setVolume(0.3f);
            Constants.assets.getWinMusic().play();

            Constants.events.fire(new OnTouchdownEvent(true));
        }
        else {
            Constants.assets.playSound(Constants.assets.getGameOverSound());
            Constants.events.fire(new OnTouchdownEvent(false));
        }

        morganTouchedDown = true;
    }
}

