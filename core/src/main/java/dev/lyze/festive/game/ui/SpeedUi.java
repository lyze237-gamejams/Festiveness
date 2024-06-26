package dev.lyze.festive.game.ui;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import lombok.Getter;

public class SpeedUi extends UiBehaviour<Table> {
    private Body bodyToTrack;

    private Label label;

    public SpeedUi(Ui ui, GameObject gameObject) {
        super(ui, gameObject);
    }

    @Override
    public Table generateComponent() {
        var table = new Table();
        table.setFillParent(true);

        label = new Label("0 m/s", Constants.assets.getSkin());
        table.add(label).top().expand().padLeft(24).padTop(18);

        return table;
    }

    @Override
    public void start() {
        bodyToTrack = getUnBox().findBehaviour(BalancerBehaviour.class).getPlayer().getStomach2().getBody();
    }

    @Override
    public void update(float delta) {
        var score = (int) bodyToTrack.getLinearVelocity().len();
        label.setText(score + " m/s");
    }
}
