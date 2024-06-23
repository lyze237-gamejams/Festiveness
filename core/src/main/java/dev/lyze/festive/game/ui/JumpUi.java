package dev.lyze.festive.game.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import lombok.Getter;

public class JumpUi extends UiBehaviour<Table> {
    @Getter private int jumps = 2;
    private int maxJumps = 4;

    public JumpUi(Ui ui, GameObject gameObject) {
        super(ui, gameObject);
    }

    @Override
    public Table generateComponent() {
        var table = new Table();
        table.setFillParent(true);

        return table;
    }

    @Override
    public void start() {
        getComponent().clear();
        getComponent().add(regenrateComponent()).top().left().pad(24).expand();
    }

    private Table regenrateComponent() {
        var table = new Table();

        for (int i = 0; i < jumps; i++) {
            var image = new Image(Constants.assets.getBomb());
            table.add(image).size(image.getWidth() * 0.1f, image.getHeight() * 0.1f);
        }

        return table;
    }

    public void jump() {
        if (jumps <= 0)
            return;

        jumps--;

        getUnBox().findBehaviour(BalancerBehaviour.class).getPlayer().applyJump();
        start();
    }
}
