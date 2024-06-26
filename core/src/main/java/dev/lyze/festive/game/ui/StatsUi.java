package dev.lyze.festive.game.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import lombok.Getter;

public class StatsUi extends UiBehaviour<Table> {
    private Body bodyToTrack;

    private Label meterLabel, mpsLabel, heightLabel;

    @Getter private int score;
    @Getter private float highestMeter;

    @Getter private float totalDelta;

    public StatsUi(Ui ui, GameObject gameObject) {
        super(ui, gameObject);
    }

    @Override
    public Table generateComponent() {
        var table = new Table();
        table.setFillParent(true);

        var innerTable = new Table();

        innerTable.add(meterLabel = new Label("0 score", Constants.assets.getSkin())).row();
        innerTable.add(mpsLabel = new Label("0 m/s", Constants.assets.getSkin(), "subtext")).row();
        innerTable.add(heightLabel = new Label("0 m high", Constants.assets.getSkin(), "subtext"));

        table.add(innerTable).top().expand().padLeft(24).padTop(18);

        return table;
    }

    @Override
    public void start() {
        bodyToTrack = getUnBox().findBehaviour(BalancerBehaviour.class).getPlayer().getStomach2().getBody();
    }

    @Override
    public void update(float delta) {
        totalDelta += delta;

        meterLabel.setText((score + getMeter()) + " score");
        mpsLabel.setText((int) bodyToTrack.getLinearVelocity().len() + " m/s");
        heightLabel.setText((int) bodyToTrack.getPosition().y - 2 + " m high");

        highestMeter = Math.max(highestMeter, bodyToTrack.getPosition().y - 2);
    }

    public int getMeter() {
        return (int) bodyToTrack.getPosition().x - 2;
    }

    public void addScore() {
        score += 100;
    }
}
