package dev.lyze.festive.game.booper;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.Player;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class BooperRendererBehaviour extends BehaviourAdapter {

    private final Booper booper;
    private final Player player;
    private final TextureAtlas.AtlasSprite sprite;

    public BooperRendererBehaviour(Booper booper, Player player, GameObject gameObject) {
        super(gameObject);

        this.booper = booper;
        this.player = player;
        this.sprite = new TextureAtlas.AtlasSprite(Constants.assets.getBooper());
    }

    @Override
    public void update(float delta) {
        var body = booper.getBody();

        var rotation = MathUtils.radDeg * MathUtils.atan2(player.getHead().getBody().getPosition().y - body.getPosition().y, player.getHead().getBody().getPosition().x - body.getPosition().x);
        if (rotation < 0)
            rotation += 360;
        if (rotation > 360)
            rotation -= 360;



        sprite.setOriginCenter();
        sprite.setScale(Constants.GraphicsPpm);
        sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);
        sprite.setRotation(rotation);
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }
}
