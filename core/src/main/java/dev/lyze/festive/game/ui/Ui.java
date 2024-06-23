package dev.lyze.festive.game.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;

public class Ui {
    private final Stage stage;
    private final UnBox unBox;

    public Ui(UnBox unBox) {
        this.unBox = unBox;

        this.stage = new Stage(new FitViewport(1280, 720));

        setupStage();
    }

    private void setupStage() {
        var table = new Table();
        table.setFillParent(true);

        var score = new MeterUi(new GameObject("Score Ui", unBox)).generateComponent();
        table.add(score).top().left().expand().padLeft(24).padTop(18);

        stage.addActor(table);
    }

    public void render() {
        stage.getViewport().apply();

        stage.act();
        stage.draw();
    }

    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }
}
