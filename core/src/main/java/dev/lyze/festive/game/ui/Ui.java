package dev.lyze.festive.game.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import lombok.Getter;

public class Ui {
    @Getter private final Stage stage;

    private final UnBox unBox;

    public Ui(UnBox unBox) {
        this.unBox = unBox;

        this.stage = new Stage(new FitViewport(1280, 720));

        setupStage();
    }

    private void setupStage() {
        new MeterUi(this, new GameObject("Score Ui", unBox)).generateComponent();
        new MainMenu(this, new GameObject("Main Menu", unBox)).generateComponent();
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
