package dev.lyze.festive;

import com.badlogic.gdx.math.Vector2;
import dev.lyze.gdxUnBox2d.Box2dPhysicsWorld;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.UnBox;
import lombok.Getter;

@Getter
public class Player {

    private final BodyPart frontFoot1, frontFoot2, frontLeg;
    private final BodyPart backFoot1, backFoot2, backLeg;
    private final BodyPart frontTighs, backTighs;
    private final BodyPart stomach1, stomach2, stomach3;
    private final BodyPart frontArm, frontHand, backArm, backHand;
    private final BodyPart neck, head;

    public Player(UnBox<Box2dPhysicsWorld> unBox) {
        float len = 2f;

        frontFoot1 = new BodyPart(new Vector2(0, 50), new Vector2(len + len, len), .5f, Constants.Bit_Player_Front, Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Tool, unBox);
        frontFoot2 = new BodyPart(new Vector2(8, 50), new Vector2(len + len / 2, len), 0.5f, Constants.Bit_Player_Front, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Tool), unBox);
        frontLeg = new BodyPart(new Vector2(-4, 63), new Vector2(len, len * 4f), 0.8f, Constants.Bit_Player_Front, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Tool), unBox);
        backFoot1 = new BodyPart(new Vector2(0, 50), new Vector2(len + len, len), .5f, Constants.Bit_Player_Back, Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Back | Constants.Bit_Tool, unBox);
        backFoot2 = new BodyPart(new Vector2(8, 50), new Vector2(len + len / 2, len), 0.5f, Constants.Bit_Player_Back, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Back | Constants.Bit_Tool), unBox);
        backLeg = new BodyPart(new Vector2(-4, 63), new Vector2(len, len * 4f), 0.8f, Constants.Bit_Player_Back, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Back | Constants.Bit_Tool), unBox);

        frontTighs = new BodyPart(new Vector2(-4, 80), new Vector2(len, len * 5f), 0.7f, Constants.Bit_Player_Front, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Tool), unBox);
        backTighs = new BodyPart(new Vector2(-4, 80), new Vector2(len, len * 5f), 0.7f, Constants.Bit_Player_Back, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Back | Constants.Bit_Tool), unBox);

        stomach1 = new BodyPart(new Vector2(-4, 90), new Vector2(len * 1.5f, len * 2.5f), 0.5f, (Constants.Bit_Player_Front | Constants.Bit_Player_Back), (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Player_Back | Constants.Bit_Tool), unBox);
        stomach2 = new BodyPart(new Vector2(-4, 100), new Vector2(len * 1.5f, len * 2.5f), 0.4f, (Constants.Bit_Player_Front | Constants.Bit_Player_Back), (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Player_Back | Constants.Bit_Tool), unBox);
        stomach3 = new BodyPart(new Vector2(-4, 110), new Vector2(len * 2, len * 3.5f), 0.3f, (Constants.Bit_Player_Front | Constants.Bit_Player_Back), (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Player_Back | Constants.Bit_Tool), unBox);

        frontArm = new BodyPart(new Vector2(-4, 100), new Vector2(len, len * 3.5f), 0.2f, Constants.Bit_Player_Front, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Tool), unBox);
        frontHand = new BodyPart(new Vector2(-4, 100), new Vector2(len, len * 3.5f), 0.2f, Constants.Bit_Player_Front, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Tool), unBox);
        backArm = new BodyPart(new Vector2(-4, 100), new Vector2(len, len * 3.5f), 0.2f, Constants.Bit_Player_Back, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Tool), unBox);
        backHand = new BodyPart(new Vector2(-4, 100), new Vector2(len, len * 3.5f), 0.2f, Constants.Bit_Player_Front, (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Tool), unBox);

        neck = new BodyPart(new Vector2(-4, 120), new Vector2(len, len * 2.5f), 0.3f, (Constants.Bit_Player_Front | Constants.Bit_Player_Back), (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Player_Back | Constants.Bit_Tool), unBox);
        head = new BodyPart(new Vector2(-4, 130), new Vector2(len * 3f, len * 3.5f), 0.3f, (Constants.Bit_Player_Front | Constants.Bit_Player_Back), (Constants.Bit_enimes | Constants.Bit_land | Constants.Bit_Player_Front | Constants.Bit_Player_Back | Constants.Bit_Tool), unBox);

        new CreateBodyJointsBehaviour(this, new GameObject(unBox));
    }
}
