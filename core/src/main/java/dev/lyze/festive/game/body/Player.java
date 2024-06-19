package dev.lyze.festive.game.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.Assets;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.festive.game.body.physics.init.BodyPart;
import dev.lyze.festive.game.body.physics.init.CreateBodyJointsBehaviour;
import dev.lyze.festive.game.body.physics.init.CreateBodyPartFixtureBehaviour;
import dev.lyze.festive.game.body.renderer.BodyPartRendererBehaviour;
import dev.lyze.gdxUnBox2d.Box2dBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import lombok.Getter;

@Getter
public class Player {

    private final GameObject gameObject;
    private final UnBox unBox;

    private BodyPart frontFoot, frontToes, frontLowerLeg;
    private BodyPart backFoot, backToes, backLowerLeg;
    private BodyPart frontUpperLeg, backUpperLeg;
    private BodyPart stomach1, stomach2, stomach3;
    private BodyPart frontArm, frontHand, frontFingers, backArm, backHand, backFingers;
    private BodyPart neck, head;

    private BalancerBehaviour balancer;

    public Player(UnBox unBox) {
        this.unBox = unBox;
        gameObject = new GameObject(unBox);

        setupPhysicsBodyParts(unBox);
        balancer = new BalancerBehaviour(this, gameObject);
    }

    private void setupPhysicsBodyParts(UnBox unBox) {
        var spawnPosition = new Vector2(2.68f, 1.6f);

        backFoot = new BodyPart("Back Foot", spawnPosition, new Vector2(0, 50), new Vector2(Constants.Length + Constants.Length, Constants.Length), .5f, Constants.Bit_PlayerBack, Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools, unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiFeet(), backFoot, false, gameObject);
        backToes = new BodyPart("Back Toes", spawnPosition, new Vector2(8, 50), new Vector2(Constants.Length + Constants.Length / 2, Constants.Length), 0.5f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiToes(), backToes, false, gameObject);
        backLowerLeg = new BodyPart("Back Lower Leg", spawnPosition, new Vector2(-4, 63), new Vector2(Constants.Length, Constants.Length * 4f), 0.8f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiLowerLegs(), backLowerLeg, false, gameObject);
        backUpperLeg = new BodyPart("Back Upper Leg", spawnPosition, new Vector2(-4, 80), new Vector2(Constants.Length, Constants.Length * 5f), 0.7f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiUpperLegs(), backUpperLeg, false, gameObject);

        backArm = new BodyPart("Back Arm", spawnPosition, new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiUpperArm(), backArm, false, gameObject);
        backHand = new BodyPart("Back Hand", spawnPosition, new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiLowerArm(), backHand, false, gameObject);
        backFingers = new BodyPart("Back Fingers", spawnPosition, new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiHand(), backFingers, false, gameObject);

        stomach1 = new BodyPart("Stomach 1", spawnPosition, new Vector2(-4, 90), new Vector2(Constants.Length * 1.5f, Constants.Length * 2.5f), 0.5f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiLowerTorso(), stomach1, true, gameObject);
        stomach2 = new BodyPart("Stomach 2", spawnPosition, new Vector2(-4, 100), new Vector2(Constants.Length * 1.5f, Constants.Length * 2.5f), 0.4f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiMiddleTorso(), stomach2, true, gameObject);
        stomach3 = new BodyPart("Stomach 3", spawnPosition, new Vector2(-4, 110), new Vector2(Constants.Length * 2, Constants.Length * 3.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiUpperTorso(), stomach3, true, gameObject);

        neck = new BodyPart("Neck", spawnPosition, new Vector2(-4, 120), new Vector2(Constants.Length, Constants.Length * 2.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiNeck(), neck, true, gameObject);
        head = new BodyPart("Head", spawnPosition, new Vector2(-4, 130), new Vector2(Constants.Length * 3f, Constants.Length * 3.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiHead(), head, true, gameObject);

        frontFoot = new BodyPart("Front Foot", spawnPosition, new Vector2(0, 50), new Vector2(Constants.Length + Constants.Length, Constants.Length), .5f, Constants.Bit_PlayerFront, Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools, unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiFeet(), frontFoot, true, gameObject);
        frontToes = new BodyPart("Front Toes", spawnPosition, new Vector2(8, 50), new Vector2(Constants.Length + Constants.Length / 2, Constants.Length), 0.5f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiToes(), frontToes, true, gameObject);
        frontLowerLeg = new BodyPart("Front Lower Leg", spawnPosition, new Vector2(-4, 63), new Vector2(Constants.Length, Constants.Length * 4f), 0.8f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiLowerLegs(), frontLowerLeg, true, gameObject);
        frontUpperLeg = new BodyPart("Front Upper Leg", spawnPosition, new Vector2(-4, 80), new Vector2(Constants.Length, Constants.Length * 5f), 0.7f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiUpperLegs(), frontUpperLeg, true, gameObject);

        frontArm = new BodyPart("Front Arm", spawnPosition, new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiUpperArm(), frontArm, true, gameObject);
        frontHand = new BodyPart("Front Hand", spawnPosition, new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiLowerArm(), frontHand, true, gameObject);
        frontFingers = new BodyPart("Front Fingers", spawnPosition, new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Constants.assets.getMorgiHand(), frontFingers, false, gameObject);

        new CreateBodyJointsBehaviour(this, gameObject);
    }

    public void applySpeedBoost() {
        for (CreateBodyPartFixtureBehaviour bodyPart : unBox.findBehaviours(CreateBodyPartFixtureBehaviour.class)) {
            var box2dBehaviour = bodyPart.getGameObject().getBehaviour(Box2dBehaviour.class);
            var body = box2dBehaviour.getBody();
            System.out.println(box2dBehaviour.getGameObject() + ": " + body.getLinearVelocity());
            body.setLinearVelocity(body.getLinearVelocity().scl(2));
        }
    }

    public void applyJump() {
        for (CreateBodyPartFixtureBehaviour bodyPart : unBox.findBehaviours(CreateBodyPartFixtureBehaviour.class)) {
            var box2dBehaviour = bodyPart.getGameObject().getBehaviour(Box2dBehaviour.class);
            var body = box2dBehaviour.getBody();

            var velocity = body.getLinearVelocity();
            var length = velocity.len();
            velocity.set(MathUtils.random(45, 55), MathUtils.random(45, 55)).nor().scl(length);
            body.setLinearVelocity(velocity);
        }
    }
}
