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
        new MainMenu(this, new GameObject("Main Menu", unBox)).generateComponent();
        new GameOverMenu(this, new GameObject("Game Over", unBox)).generateComponent();
        new StatsUi(this, new GameObject("Score Ui", unBox)).generateComponent();
        new BoostUi(this, new GameObject("Boosts Ui", unBox)).generateComponent();
        new JumpUi(this, new GameObject("Jump Ui", unBox)).generateComponent();
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
