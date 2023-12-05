package dev.lyze.festive.game.body;

import com.badlogic.gdx.math.Vector2;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.festive.game.body.physics.init.BodyPart;
import dev.lyze.festive.game.body.physics.init.CreateBodyJointsBehaviour;
import dev.lyze.gdxUnBox2d.Box2dPhysicsWorld;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import lombok.Getter;

@Getter
public class Player {

    private final GameObject gameObject;

    private BodyPart frontFoot1, frontFoot2, frontLeg;
    private BodyPart backFoot1, backFoot2, backLeg;
    private BodyPart frontTighs, backTighs;
    private BodyPart stomach1, stomach2, stomach3;
    private BodyPart frontArm, frontHand, backArm, backHand;
    private BodyPart neck, head;

    private BalancerBehaviour balancer;

    public Player(UnBox<Box2dPhysicsWorld> unBox) {
        gameObject = new GameObject(unBox);

        setupPhysicsBodyParts(unBox);
        balancer = new BalancerBehaviour(this, gameObject);
    }

    private void setupPhysicsBodyParts(UnBox<Box2dPhysicsWorld> unBox) {
        frontFoot1 = new BodyPart(new Vector2(0, 50), new Vector2(Constants.Length + Constants.Length, Constants.Length), .5f, Constants.Bit_PlayerFront, Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools, unBox);
        frontFoot2 = new BodyPart(new Vector2(8, 50), new Vector2(Constants.Length + Constants.Length / 2, Constants.Length), 0.5f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        frontLeg = new BodyPart(new Vector2(-4, 63), new Vector2(Constants.Length, Constants.Length * 4f), 0.8f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        backFoot1 = new BodyPart(new Vector2(0, 50), new Vector2(Constants.Length + Constants.Length, Constants.Length), .5f, Constants.Bit_PlayerBack, Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools, unBox);
        backFoot2 = new BodyPart(new Vector2(8, 50), new Vector2(Constants.Length + Constants.Length / 2, Constants.Length), 0.5f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        backLeg = new BodyPart(new Vector2(-4, 63), new Vector2(Constants.Length, Constants.Length * 4f), 0.8f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);

        frontTighs = new BodyPart(new Vector2(-4, 80), new Vector2(Constants.Length, Constants.Length * 5f), 0.7f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        backTighs = new BodyPart(new Vector2(-4, 80), new Vector2(Constants.Length, Constants.Length * 5f), 0.7f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);

        stomach1 = new BodyPart(new Vector2(-4, 90), new Vector2(Constants.Length * 1.5f, Constants.Length * 2.5f), 0.5f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        stomach2 = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length * 1.5f, Constants.Length * 2.5f), 0.4f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        stomach3 = new BodyPart(new Vector2(-4, 110), new Vector2(Constants.Length * 2, Constants.Length * 3.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);

        frontArm = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        frontHand = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        backArm = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        backHand = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);

        neck = new BodyPart(new Vector2(-4, 120), new Vector2(Constants.Length, Constants.Length * 2.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        head = new BodyPart(new Vector2(-4, 130), new Vector2(Constants.Length * 3f, Constants.Length * 3.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);

        new CreateBodyJointsBehaviour(this, gameObject);
    }
}
