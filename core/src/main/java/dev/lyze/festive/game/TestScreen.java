package dev.lyze.festive.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.lyze.festive.ViewportBehaviour;
import dev.lyze.festive.game.body.Explosion;
import dev.lyze.festive.game.body.Player;
import dev.lyze.festive.game.tool.Tool;
import dev.lyze.festive.game.tool.ToolControlsBehaviour;
import dev.lyze.gdxUnBox2d.Box2dPhysicsWorld;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;

public class TestScreen extends ScreenAdapter {
    private final UnBox<Box2dPhysicsWorld> unbox = new UnBox<>(new Box2dPhysicsWorld(new World(new Vector2(0, -10), true)));
    private final Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    private final ExtendViewport viewport = new ExtendViewport(19, 8);
    private final SpriteBatch batch = new SpriteBatch();

    private final Player player;

    public TestScreen() {
        new Ground(unbox);
        player = new Player(unbox);
        new Tool(unbox);
        new Explosion(player, new GameObject(unbox));
        new ViewportBehaviour(viewport, new GameObject(unbox));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.BLACK);

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            player.getBalancer().setEnabled(!player.getBalancer().isEnabled());

        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
            ((Game) Gdx.app.getApplicationListener()).setScreen(new TestScreen());

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
