package dev.lyze.festive.game.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import dev.lyze.festive.game.Assets;
import dev.lyze.festive.game.body.Player;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class ToolRendererBehaviour extends BehaviourAdapter {

    private final Tool tool;
    private final Player player;
    private final Sprite sprite;

    public ToolRendererBehaviour(Tool tool, Player player, GameObject gameObject) {
        super(gameObject);

        this.tool = tool;
        this.player = player;
        this.sprite = new Sprite(Assets.getBooper());
    }

    @Override
    public void update(float delta) {
        var body = tool.getBody();

        var rotation = MathUtils.radDeg * MathUtils.atan2(player.getHead().getBody().getPosition().y - body.getPosition().y, player.getHead().getBody().getPosition().x - body.getPosition().x);
        if (rotation < 0)
            rotation += 360;
        if (rotation > 360)
            rotation -= 360;



        sprite.setOriginCenter();
        sprite.setScale(0.0007f);
        sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);
        sprite.setRotation(rotation);
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }
}
