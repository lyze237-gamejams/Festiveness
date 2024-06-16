package dev.lyze.festive.game.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.physics.BalancerBehaviour;
import dev.lyze.festive.game.body.physics.init.BodyPart;
import dev.lyze.festive.game.body.physics.init.CreateBodyJointsBehaviour;
import dev.lyze.festive.game.body.renderer.BodyPartRendererBehaviour;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import lombok.Getter;

@Getter
public class Player {

    private final GameObject gameObject;

    private BodyPart frontFoot, frontToes, frontLowerLeg;
    private BodyPart backFoot, backToes, backLowerLeg;
    private BodyPart frontUpperLeg, backUpperLeg;
    private BodyPart stomach1, stomach2, stomach3;
    private BodyPart frontArm, frontHand, frontFingers, backArm, backHand, backFingers;
    private BodyPart neck, head;

    private BalancerBehaviour balancer;

    public Player(UnBox unBox) {
        gameObject = new GameObject(unBox);

        setupPhysicsBodyParts(unBox);
        balancer = new BalancerBehaviour(this, gameObject);
    }

    private void setupPhysicsBodyParts(UnBox unBox) {
        backFoot = new BodyPart(new Vector2(0, 50), new Vector2(Constants.Length + Constants.Length, Constants.Length), .5f, Constants.Bit_PlayerBack, Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools, unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/feet.png"), backFoot, false, gameObject);
        backToes = new BodyPart(new Vector2(8, 50), new Vector2(Constants.Length + Constants.Length / 2, Constants.Length), 0.5f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/toes.png"), backToes, false, gameObject);
        backLowerLeg = new BodyPart(new Vector2(-4, 63), new Vector2(Constants.Length, Constants.Length * 4f), 0.8f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/lower legs.png"), backLowerLeg, false, gameObject);
        backUpperLeg = new BodyPart(new Vector2(-4, 80), new Vector2(Constants.Length, Constants.Length * 5f), 0.7f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/upper legs.png"), backUpperLeg, false, gameObject);

        backArm = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerBack, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/up arm.png"), backArm, false, gameObject);
        backHand = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/low arm.png"), backHand, false, gameObject);
        backFingers = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/hand.png"), backFingers, false, gameObject);

        stomach1 = new BodyPart(new Vector2(-4, 90), new Vector2(Constants.Length * 1.5f, Constants.Length * 2.5f), 0.5f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/low tors.png"), stomach1, true, gameObject);
        stomach2 = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length * 1.5f, Constants.Length * 2.5f), 0.4f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/mid tors.png"), stomach2, true, gameObject);
        stomach3 = new BodyPart(new Vector2(-4, 110), new Vector2(Constants.Length * 2, Constants.Length * 3.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/upper tors.png"), stomach3, true, gameObject);

        neck = new BodyPart(new Vector2(-4, 120), new Vector2(Constants.Length, Constants.Length * 2.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/neck.png"), neck, true, gameObject);
        head = new BodyPart(new Vector2(-4, 130), new Vector2(Constants.Length * 3f, Constants.Length * 3.5f), 0.3f, (Constants.Bit_PlayerFront | Constants.Bit_PlayerBack), (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_PlayerBack | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/head.png"), head, true, gameObject);

        frontFoot = new BodyPart(new Vector2(0, 50), new Vector2(Constants.Length + Constants.Length, Constants.Length), .5f, Constants.Bit_PlayerFront, Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools, unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/feet.png"), frontFoot, true, gameObject);
        frontToes = new BodyPart(new Vector2(8, 50), new Vector2(Constants.Length + Constants.Length / 2, Constants.Length), 0.5f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/toes.png"), frontToes, true, gameObject);
        frontLowerLeg = new BodyPart(new Vector2(-4, 63), new Vector2(Constants.Length, Constants.Length * 4f), 0.8f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/lower legs.png"), frontLowerLeg, true, gameObject);
        frontUpperLeg = new BodyPart(new Vector2(-4, 80), new Vector2(Constants.Length, Constants.Length * 5f), 0.7f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_PlayerFront | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/upper legs.png"), frontUpperLeg, true, gameObject);

        frontArm = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/up arm.png"), frontArm, true, gameObject);
        frontHand = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/low arm.png"), frontHand, true, gameObject);
        frontFingers = new BodyPart(new Vector2(-4, 100), new Vector2(Constants.Length, Constants.Length * 3.5f), 0.2f, Constants.Bit_PlayerFront, (Constants.Bit_Enemies | Constants.Bit_Ground | Constants.Bit_Tools), unBox);
        new BodyPartRendererBehaviour(Gdx.files.internal("Morgi/hand.png"), frontFingers, false, gameObject);

        new CreateBodyJointsBehaviour(this, gameObject);
    }
}
