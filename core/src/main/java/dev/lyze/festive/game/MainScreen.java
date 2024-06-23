package dev.lyze.festive.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
import dev.lyze.festive.CameraBehaviour;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.ui.Ui;
import dev.lyze.festive.game.world.BackgroundBehaviour;
import dev.lyze.festive.game.world.SkyBehaviour;
import dev.lyze.festive.game.body.Explosion;
import dev.lyze.festive.game.body.Player;
import dev.lyze.festive.game.eventCheckers.OnFinalIslandSpawnEventChecker;
import dev.lyze.festive.game.tool.Tool;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;

public class MainScreen extends ScreenAdapter {
    private final UnBox unbox = new UnBox(new World(new Vector2(0, -10), true));
    private final Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    private final SpriteBatch batch = new SpriteBatch();
    private final ShapeRenderer renderer = new ShapeRenderer();
    private final SkyBehaviour skyBehaviour;

    private final Player player;

    private final Ui ui;

    public MainScreen() {
        new GameInput(new GameObject("Input", unbox));
        new BackgroundBehaviour(new GameObject(unbox));
        player = new Player(unbox);
        new Ground(player, new GameObject("Ground", unbox));
        new Tool(player, unbox);
        new CameraBehaviour(player, new GameObject(unbox));
        skyBehaviour = new SkyBehaviour(new GameObject(unbox));

        new Explosion(player, new GameObject(unbox));
        ui = new Ui(unbox);
        Gdx.input.setInputProcessor(ui.getStage());

        new OnFinalIslandSpawnEventChecker(new GameObject("Final Island Spawn Event Checker", unbox));
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(skyBehaviour.getTopSkyColor());

        if (Gdx.input.isKeyJustPressed(Input.Keys.R))
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());

        Gdx.gl.glLineWidth(4);

        unbox.preRender(delta);
        Constants.viewport.apply();

        renderer.setProjectionMatrix(Constants.viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        skyBehaviour.debugRender(renderer);
        renderer.end();

        batch.setProjectionMatrix(Constants.viewport.getCamera().combined);
        batch.begin();
        unbox.render(batch);
        batch.end();

        ui.render();

        /*
        renderer.setProjectionMatrix(Constants.viewport.getCamera().combined);
        box2DDebugRenderer.render(unbox.getWorld(), Constants.viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Line);
        unbox.debugRender(renderer);
        renderer.end();
         */

        unbox.postRender();
    }

    @Override
    public void resize(int width, int height) {
        Constants.viewport.update(width, height);
        ui.resize(width, height);
    }
}