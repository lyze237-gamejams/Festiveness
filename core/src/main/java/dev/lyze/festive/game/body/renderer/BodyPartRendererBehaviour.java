package dev.lyze.festive.game.body.renderer;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import dev.lyze.festive.game.body.physics.init.BodyPart;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class BodyPartRendererBehaviour extends BehaviourAdapter {
    private final BodyPart bodyPart;
    private final Sprite sprite;
    private final boolean isFront;

    public BodyPartRendererBehaviour(FileHandle file, BodyPart bodyPart, boolean isFront, GameObject gameObject) {
        super(gameObject);

        this.bodyPart = bodyPart;
        this.sprite = new Sprite(new Texture(file));
        this.isFront = isFront;
    }

    @Override
    public void update(float delta) {
        var body = bodyPart.getBody();

        sprite.setColor(isFront ? 1f : 0.8f, isFront ? 1f : 0.8f, isFront ? 1f : 0.8f, 1f);
        sprite.setOriginCenter();
        sprite.setScale(0.0007f);
        sprite.setOriginBasedPosition(body.getPosition().x, body.getPosition().y);
        sprite.setRotation(body.getAngle() * MathUtils.radDeg);
    }

    @Override
    public void render(Batch batch) {
        sprite.draw(batch);
    }

    @Override
    public void debugRender(ShapeRenderer renderer) {
        var body = bodyPart.getBody();

        renderer.setColor(Color.RED);
        renderer.circle(body.getPosition().x, body.getPosition().y, 0.05f, 8);
    }
}
