package dev.lyze.festive.game.ui;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.github.raeleus.gamejoltapi.*;
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

                Image component = null;
                if (!event.isWon()) {
                    component = new Image(Constants.assets.getMorgi());
                    component.setScale(0.8f);
                }
                getComponent().add(component).width(Value.percentWidth(0.5f, getComponent())).expand();
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

        var statsUi = getUnBox().findBehaviour(StatsUi.class);
        var meter = statsUi.getMeter();
        var titleText = won ? "Touchdown!" : "Oh no...";
        var subtitleText = won ? "Good job, you helped morgan reach festive island!\nWith an impressive jump of " + meter + " meter!" : "Looks like you didn't manage to help him... :(";

        table.add(new Label(titleText, Constants.assets.getSkin(), "title")).row();
        var subtitle = new Label(subtitleText, Constants.assets.getSkin(), "subtitle");
        subtitle.setAlignment(Align.center, Align.center);
        table.add(subtitle).padBottom(24).row();

        if (statsUi.getMeter() > 100) {
            var highscoreTable = new Table();
            highscoreTable.add(new Label("Enter Username:", Constants.assets.getSkin(), "subtitle"));
            var nameField = new TextField("", Constants.assets.getSkin());
            var submitScoreButton = new TextButton("Submit Score", Constants.assets.getSkin(), "smol");
            submitScoreButton.setDisabled(true);
            nameField.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    submitScoreButton.setDisabled(nameField.getText().isEmpty());
                    System.out.println(submitScoreButton.isDisabled());
                }
            });
            highscoreTable.add(nameField).padLeft(8).row();
            highscoreTable.add().pad(2).row();
            submitScoreButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    var gj = new GameJoltApi();
                    var key = Gdx.files.internal("key.txt").readString();
                    var request = GameJoltScores.ScoresAddRequest.builder()
                        .gameID(Constants.gameId)
                        .score(Long.toString(statsUi.getScore() + statsUi.getMeter()))
                        .sort((long) statsUi.getScore() + statsUi.getMeter())
                        .guest(nameField.getText())
                        .build();

                    gj.sendRequest(request, key, new GameJoltScores.ScoresAddListener() {
                        @Override
                        public void scoresAdd(GameJoltScores.ScoresAddValue response) {
                            if (response.success) {
                                submitScoreButton.setText("Submitted");
                            } else {
                                submitScoreButton.setText(response.message);
                                submitScoreButton.setDisabled(false);
                            }
                        }
                    });

                    gj.addGuestScore(Constants.gameId, key, nameField.getText(), (long) statsUi.getHighestMeter(), 919229, null);

                    submitScoreButton.setText("Uploading");
                    submitScoreButton.setDisabled(true);
                }
            });
            highscoreTable.add(submitScoreButton);
            var viewHighscoresButton = new TextButton("View Highscores", Constants.assets.getSkin(), "smol");
            viewHighscoresButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.net.openURI("https://gamejolt.com/games/a-rather-festive-birb/906083/scores/917542/best");
                }
            });
            highscoreTable.add(viewHighscoresButton);

            table.add(highscoreTable).padBottom(24).row();
        }


        TextButton startGame = new TextButton("Retry", Constants.assets.getSkin());
        startGame.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                Constants.assets.playSound(Constants.assets.getUiClickSound());
                ((Game) Gdx.app.getApplicationListener()).setScreen(new MainScreen());
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
