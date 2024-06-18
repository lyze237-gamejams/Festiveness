package dev.lyze.festive.game.body.physics.init;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.Player;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class CreateBodyJointsBehaviour extends BehaviourAdapter {
    private final Player player;

    public CreateBodyJointsBehaviour(Player player, GameObject gameObject) {
        super(gameObject);

        this.player = player;
    }

    @Override
    public void awake() {
        var world = (World) getUnBox().getWorld();

        RevoluteJointDef rdef = new RevoluteJointDef();
        rdef.bodyA = player.getFrontFoot().getBody();
        rdef.bodyB = player.getFrontLowerLeg().getBody();
        rdef.collideConnected = false;
        rdef.localAnchorA.set(-((Constants.Length - (Constants.Length / 2)) / Constants.BodyPartPpm), 0);
        rdef.localAnchorB.set(0, -((Constants.Length * 5 - (Constants.Length / 2)) / Constants.BodyPartPpm));
        rdef.lowerAngle = (float) (-0.2f * Math.PI);
        rdef.upperAngle = (float) (0.07f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = player.getFrontToes().getBody();
        rdef.localAnchorA.set((Constants.Length + Constants.Length / 2) / Constants.BodyPartPpm, 0);
        rdef.localAnchorB.set(-((Constants.Length + Constants.Length / 2) / Constants.BodyPartPpm), 0);
        rdef.lowerAngle = 0;
        rdef.upperAngle = (float) (0.3f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyA = player.getFrontUpperLeg().getBody();
        rdef.bodyB = player.getFrontLowerLeg().getBody();
        rdef.localAnchorA.set(0, -(Constants.Length * 5 - (Constants.Length / 2)) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, ((Constants.Length * 4 - (Constants.Length / 2)) / Constants.BodyPartPpm));
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.7f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = player.getStomach1().getBody();
        rdef.localAnchorA.set(0, (Constants.Length * 5 - (Constants.Length / 2)) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, -(Constants.Length * 2) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.04f * Math.PI);
        rdef.lowerAngle = (float) (-0.5f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyA = player.getStomach2().getBody();
        rdef.localAnchorA.set(0, -(Constants.Length * 2) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, (Constants.Length * 2) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.1f * Math.PI);
        rdef.lowerAngle = -(float) (0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = player.getStomach3().getBody();
        rdef.localAnchorA.set(0, (Constants.Length * 2) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(-(Constants.Length / 2) / Constants.BodyPartPpm, -(Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyA = player.getFrontArm().getBody();
        rdef.localAnchorA.set(0, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(-(Constants.Length / 2) / Constants.BodyPartPpm, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.5f * Math.PI);
        rdef.lowerAngle = (float) (-1f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = player.getFrontHand().getBody();
        rdef.localAnchorA.set(0, -(Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.4f * Math.PI);
        rdef.lowerAngle = 0;

        world.createJoint(rdef);

        rdef.bodyA = player.getFrontHand().getBody();
        rdef.bodyB = player.getFrontFingers().getBody();
        rdef.localAnchorA.set(0, -(Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.4f * Math.PI);
        rdef.lowerAngle = 0;

        world.createJoint(rdef);

        rdef.bodyA = player.getBackArm().getBody();
        rdef.bodyB = player.getStomach3().getBody();
        rdef.localAnchorA.set(0, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(-(Constants.Length / 2) / Constants.BodyPartPpm, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.5f * Math.PI);
        rdef.lowerAngle = (float) (-1f * Math.PI);

        world.createJoint(rdef);


        rdef.bodyB = player.getBackHand().getBody();
        rdef.localAnchorA.set(0, -(Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.4f * Math.PI);
        rdef.lowerAngle = 0;

        world.createJoint(rdef);

        rdef.bodyA = player.getBackHand().getBody();
        rdef.bodyB = player.getBackFingers().getBody();
        rdef.localAnchorA.set(0, -(Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.4f * Math.PI);
        rdef.lowerAngle = 0;

        world.createJoint(rdef);

        rdef.bodyA = player.getNeck().getBody();
        rdef.bodyB = player.getStomach3().getBody();
        rdef.localAnchorA.set(0, -(Constants.Length * 2) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(-(Constants.Length / 2) / Constants.BodyPartPpm, (Constants.Length * 3) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.05f * Math.PI);
        rdef.lowerAngle = (float) (-0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = player.getHead().getBody();
        rdef.localAnchorA.set(0, (Constants.Length * 2) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(-(Constants.Length / 2) / Constants.BodyPartPpm, -(Constants.Length * 2.5f) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.1f * Math.PI);
        rdef.lowerAngle = (float) (-0.1f * Math.PI);

        world.createJoint(rdef);


        //bqck joiminh---------------------------------------------------------------------------------
        rdef.bodyA = player.getBackFoot().getBody();
        rdef.bodyB = player.getBackLowerLeg().getBody();
        rdef.localAnchorA.set(-((Constants.Length - (Constants.Length / 2)) / Constants.BodyPartPpm), 0);
        rdef.localAnchorB.set(0, -((Constants.Length * 5 - (Constants.Length / 2)) / Constants.BodyPartPpm));
        rdef.lowerAngle = (float) (-0.2f * Math.PI);
        rdef.upperAngle = (float) (0.07f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = player.getBackToes().getBody();
        rdef.localAnchorA.set((Constants.Length + Constants.Length / 2) / Constants.BodyPartPpm, 0);
        rdef.localAnchorB.set(-((Constants.Length + Constants.Length / 2) / Constants.BodyPartPpm), 0);
        rdef.lowerAngle = 0;
        rdef.upperAngle = (float) (0.3f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyA = player.getBackUpperLeg().getBody();
        rdef.bodyB = player.getBackLowerLeg().getBody();
        rdef.localAnchorA.set(0, -(Constants.Length * 5 - (Constants.Length / 2)) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, ((Constants.Length * 4 - (Constants.Length / 2)) / Constants.BodyPartPpm));
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.7f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = player.getStomach1().getBody();
        rdef.localAnchorA.set(0, (Constants.Length * 5 - (Constants.Length / 2)) / Constants.BodyPartPpm);
        rdef.localAnchorB.set(0, -(Constants.Length * 2) / Constants.BodyPartPpm);
        rdef.upperAngle = (float) (0.04f * Math.PI);
        rdef.lowerAngle = (float) (-0.5f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);
    }
}
