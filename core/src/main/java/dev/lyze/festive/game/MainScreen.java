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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.lyze.festive.CameraBehaviour;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.ui.Ui;
import dev.lyze.festive.game.world.BackgroundBehaviour;
import dev.lyze.festive.game.world.SkyBehaviour;
import dev.lyze.festive.game.body.Explosion;
import dev.lyze.festive.game.body.Player;
import dev.lyze.festive.game.eventCheckers.OnFinalIslandSpawnEventChecker;
import dev.lyze.festive.game.booper.Booper;
import dev.lyze.festive.game.world.SpaceBehaviour;
import dev.lyze.festive.game.world.tiles.StarClusterBackgroundBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;

public class MainScreen extends ScreenAdapter {
    private final UnBox unbox = new UnBox(new World(new Vector2(0, -10), true));
    private final ExtendViewport skyViewport = new ExtendViewport(Constants.viewport.getWorldWidth(), Constants.viewport.getWorldHeight());
    private final Box2DDebugRenderer box2DDebugRenderer = new Box2DDebugRenderer();
    private final SpriteBatch batch = new SpriteBatch();
    private final ShapeRenderer renderer = new ShapeRenderer();
    private final SpaceBehaviour spaceBehaviour;
    private final SkyBehaviour skyBehaviour;

    private final Player player;

    private final Ui ui;
    private final Booper booper;

    public MainScreen() {
        new GameInput(new GameObject("Input", unbox));
        new BackgroundBehaviour(new GameObject(unbox));
        player = new Player(unbox);
        new SideWall(new GameObject("Side Wall", unbox));
        new Ground(player, new GameObject("Ground", unbox));
        booper = new Booper(player, unbox);
        new CameraBehaviour(player, new GameObject(unbox));

        spaceBehaviour = new SpaceBehaviour(skyViewport, new GameObject(unbox));
        skyBehaviour = new SkyBehaviour(skyViewport, new GameObject(unbox));

        new Explosion(player, new GameObject(unbox));
        ui = new Ui(unbox);
        Gdx.input.setInputProcessor(ui.getStage());

        new OnFinalIslandSpawnEventChecker(new GameObject("Final Island Spawn Event Checker", unbox));

        if (Constants.assets.getWinMusic().isPlaying())
            Constants.assets.getWinMusic().stop();

        Constants.assets.getMainMusic().setLooping(true);
        Constants.assets.getMainMusic().setVolume(0.3f);
        Constants.assets.getMainMusic().play();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(spaceBehaviour.getTopSkyColor());

        if (Gdx.input.isKeyJustPressed(Input.Keys.F9))
            ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());

        if (Gdx.input.isKeyJustPressed(Input.Keys.F10))
            Constants.debug = !Constants.debug;

        unbox.preRender(delta);

        skyViewport.getCamera().position.set(Constants.viewport.getCamera().position);
        skyViewport.apply();
        renderer.setProjectionMatrix(skyViewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        spaceBehaviour.debugRender(renderer);
        skyBehaviour.debugRender(renderer);
        renderer.end();

        Constants.viewport.apply();
        batch.setProjectionMatrix(Constants.viewport.getCamera().combined);
        batch.begin();
        unbox.render(batch);
        batch.end();

        ui.render();

        renderer.setProjectionMatrix(Constants.viewport.getCamera().combined);
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        unbox.findBehaviours(StarClusterBackgroundBehaviour.class).forEach(s -> s.debugRender(renderer));
        renderer.end();

        if (Constants.debug) {
            renderer.setProjectionMatrix(Constants.viewport.getCamera().combined);
            box2DDebugRenderer.render(unbox.getWorld(), Constants.viewport.getCamera().combined);
            renderer.begin(ShapeRenderer.ShapeType.Line);
            unbox.debugRender(renderer);
            renderer.end();
        }

        unbox.postRender();

        if (booper.getGameObject().isEnabled() && !Constants.debug)
            booper.getGameObject().setEnabled(false);
        if (!booper.getGameObject().isEnabled() && Constants.debug)
            booper.getGameObject().setEnabled(true);
    }

    @Override
    public void resize(int width, int height) {
        Constants.viewport.update(width, height);
        skyViewport.update(width, height);
        ui.resize(width, height);
    }
}
