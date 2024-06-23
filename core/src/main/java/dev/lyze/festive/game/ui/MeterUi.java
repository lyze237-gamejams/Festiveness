package dev.lyze.festive.game.ui;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import lombok.Getter;

public class MeterUi extends UiBehaviour<Label> {
    private Body bodyToTrack;

    private Label label;

    @Getter private int score;

    public MeterUi(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public Label generateComponent() {
        return label = new Label("0 meter", Constants.assets.getSkin());
    }

    @Override
    public void start() {
        bodyToTrack = getUnBox().findBehaviour(BalancerBehaviour.class).getPlayer().getStomach2().getBody();
    }

    @Override
    public void update(float delta) {
        score = (int) bodyToTrack.getPosition().x;
        label.setText(score + " meter");
    }
}
