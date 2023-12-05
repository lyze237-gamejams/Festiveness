package dev.lyze.festive;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.lyze.gdxUnBox2d.Box2dPhysicsWorld;
import dev.lyze.gdxUnBox2d.UnBox;

public class TestScreen extends ScreenAdapter {
    private final UnBox<Box2dPhysicsWorld> unbox = new UnBox<>(new Box2dPhysicsWorld(new World(new Vector2(0, -10), true)));
    private final Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    private final ExtendViewport viewport = new ExtendViewport(19, 8);
    private final SpriteBatch batch = new SpriteBatch();

    public TestScreen() {
        new Player(unbox);

        unbox.getPhysicsWorld().getWorld().setContactListener(new ContactListener() {
            @Override
            public void beginContact(Contact contact) {

            }

            @Override
            public void endContact(Contact contact) {

            }

            @Override
            public void preSolve(Contact contact, Manifold oldManifold) {

            }

            @Override
            public void postSolve(Contact contact, ContactImpulse impulse) {

            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        unbox.preRender(delta);
        viewport.apply();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        unbox.render(batch);
        batch.end();

        box2DDebugRenderer.render(unbox.getPhysicsWorld().getWorld(), viewport.getCamera().combined);

        unbox.postRender();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
