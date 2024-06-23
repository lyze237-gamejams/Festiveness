package dev.lyze.festive.game.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import dev.lyze.festive.Constants;
import dev.lyze.festive.eventsystem.EventListener;
import dev.lyze.festive.eventsystem.events.OnTouchdownEvent;
import dev.lyze.festive.game.MainScreen;
import dev.lyze.gdxUnBox2d.GameObject;

public class GameOverMenu extends UiBehaviour<Table> {
    public GameOverMenu(Ui ui, GameObject gameObject) {
        super(ui, gameObject);

        Constants.events.addListener(new EventListener<>(OnTouchdownEvent.class) {
            @Override
            protected void fire(OnTouchdownEvent event) {
                Gdx.input.setInputProcessor(ui.getStage());

                getComponent().clear();
                getComponent().add(generateLeftTable(event.isWon())).width(Value.percentWidth(0.5f, getComponent())).expand();
                getComponent().add().width(Value.percentWidth(0.5f, getComponent())).expand();
            }
        });
    }

    @Override
    public Table generateComponent() {
        var table = new Table();
        table.setFillParent(true);

        return table;
    }

    private Table generateLeftTable(boolean won) {
        var table = new Table();

        var meter = getUnBox().findBehaviour(StatsUi.class).getMeter();
        var titleText = won ? "Touchdown!" : "Oh no...";
        var subtitleText = won ? "Good job, you helped morgan reach festive island!\nWith an impressive jump of " + meter + " meter!" : "Looks like you didn't manage to help him... :(";

        table.add(new Label(titleText, Constants.assets.getSkin(), "title")).row();
        Label subtitle = new Label(subtitleText, Constants.assets.getSkin(), "subtitle");
        subtitle.setAlignment(Align.center, Align.center);
        table.add(subtitle).padBottom(24).row();

        TextButton startGame = new TextButton("Retry", Constants.assets.getSkin());
        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
            }
        });

        table.add(startGame).row();
        if (Gdx.app.getType() != Application.ApplicationType.WebGL) {
            TextButton exit = new TextButton("Exit", Constants.assets.getSkin());
            exit.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.app.exit();
                }
            });
            table.add(exit);
        }

        return table;
    }
}
