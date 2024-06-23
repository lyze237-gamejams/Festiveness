package dev.lyze.festive.game.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Value;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.GameInput;
import dev.lyze.gdxUnBox2d.GameObject;

public class MainMenu extends UiBehaviour<Table> {
    public MainMenu(Ui ui, GameObject gameObject) {
        super(ui, gameObject);
    }

    @Override
    public Table generateComponent() {
        var table = new Table();
        table.setFillParent(true);

        table.add().width(Value.percentWidth(0.5f, table)).expand();
        table.add(generateRightTable()).width(Value.percentWidth(0.5f, table)).expand();

        return table;
    }

    private Table generateRightTable() {
        var table = new Table();

        table.add(new Label("A rather festive birb", Constants.assets.getSkin(), "title")).row();
        Label subtitle = new Label("Morgan is stuck on a summery island.\nHelp him by yeeting him across the ocean.\nBuild up infinite speed to time travel back to winter!\n\nClick left next to Morgi to yeet him into the air.\nIn air press space to boost and left click to jump.", Constants.assets.getSkin(), "subtitle");
        subtitle.setAlignment(Align.center, Align.center);
        table.add(subtitle).padBottom(24).row();

        TextButton startGame = new TextButton("Start", Constants.assets.getSkin());
        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Constants.assets.playSound(Constants.assets.getUiClickSound());
                Gdx.input.setInputProcessor(getUnBox().findBehaviour(GameInput.class).getInput());
                getGameObject().setEnabled(false);

                Gdx.graphics.setCursor(Constants.assets.getCursor());
            }
        });

        table.add(startGame).row();
        if (Gdx.app.getType() != Application.ApplicationType.WebGL) {
            TextButton exit = new TextButton("Exit", Constants.assets.getSkin());
            exit.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Constants.assets.playSound(Constants.assets.getUiClickSound());
                    Gdx.app.exit();
                }
            });
            table.add(exit);
        }

        return table;
    }
}
