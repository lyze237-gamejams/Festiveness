package dev.lyze.festive.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.lyze.festive.CameraBehaviour;
import dev.lyze.festive.ViewportBehaviour;
import dev.lyze.festive.game.background.BackgroundBehaviour;
import dev.lyze.festive.game.background.SkyBehaviour;
import dev.lyze.festive.game.body.Explosion;
import dev.lyze.festive.game.body.Player;
import dev.lyze.festive.game.tool.Tool;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;

public class TestScreen extends ScreenAdapter {
    private final UnBox unbox = new UnBox(new World(new Vector2(0, -10), true));
    private final Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    private final ExtendViewport viewport = new ExtendViewport(16, 9);
    private final SpriteBatch batch = new SpriteBatch();
    private final ShapeRenderer renderer = new ShapeRenderer();
    private final SkyBehaviour skyBehaviour;

    private final Player player;

    public TestScreen() {
        new BackgroundBehaviour(viewport, new GameObject(unbox));
        new Ground(unbox);
        player = new Player(unbox);
        new Tool(player, unbox);
        new Explosion(player, new GameObject(unbox));
        new ViewportBehaviour(viewport, new GameObject(unbox));
        new CameraBehaviour(viewport, player, new GameObject(unbox));
        skyBehaviour = new SkyBehaviour(viewport, new GameObject(unbox));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(skyBehaviour.getTopSkyColor());

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE))
            player.getBalancer().setEnabled(!player.getBalancer().isEnabled());

        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
            ((Game) Gdx.app.getApplicationListener()).setScreen(new TestScreen());

        if (Gdx.input.isKeyJustPressed(Input.Keys.B))
            player.applySpeedBoost();

        if (Gdx.input.isKeyJustPressed(Input.Keys.J))
            player.applyJump();

        Gdx.gl.glLineWidth(4);

        unbox.preRender(delta);
        viewport.apply();

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        skyBehaviour.debugRender(renderer);
        renderer.end();

        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        unbox.render(batch);
        batch.end();

        renderer.setProjectionMatrix(viewport.getCamera().combined);
        box2DDebugRenderer.render(unbox.getWorld(), viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        unbox.debugRender(renderer);
        renderer.end();

        unbox.postRender();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
