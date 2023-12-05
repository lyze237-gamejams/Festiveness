package dev.lyze.festive.game.body.physics;

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
        var world = (World) getUnBox().getPhysicsWorld().getWorld();
        float len = 2;

        RevoluteJointDef rdef = new RevoluteJointDef();
        rdef.bodyA = player.getFrontFoot1().getBody();
        rdef.bodyB = player.getFrontLeg().getBody();
        rdef.collideConnected = false;
        rdef.localAnchorA.set(-((len - (len / 2)) / Constants.PPM), 0);
        rdef.localAnchorB.set(0, -((len * 5 - (len / 2)) / Constants.PPM));
        rdef.lowerAngle = (float) (-0.2f * Math.PI);
        rdef.upperAngle = (float) (0.07f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = player.getFrontFoot2().getBody();
        rdef.localAnchorA.set((len + len / 2) / Constants.PPM, 0);
        rdef.localAnchorB.set(-((len + len / 2) / Constants.PPM), 0);
        rdef.lowerAngle = 0;
        rdef.upperAngle = (float) (0.3f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyA = player.getFrontTighs().getBody();
        rdef.bodyB = player.getFrontLeg().getBody();
        rdef.localAnchorA.set(0, -(len * 5 - (len / 2)) / Constants.PPM);
        rdef.localAnchorB.set(0, ((len * 4 - (len / 2)) / Constants.PPM));
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.7f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = player.getStomach1().getBody();
        rdef.localAnchorA.set(0, (len * 5 - (len / 2)) / Constants.PPM);
        rdef.localAnchorB.set(0, -(len * 2) / Constants.PPM);
        rdef.upperAngle = (float) (0.04f * Math.PI);
        rdef.lowerAngle = (float) (-0.5f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyA = player.getStomach2().getBody();
        rdef.localAnchorA.set(0, -(len * 2) / Constants.PPM);
        rdef.localAnchorB.set(0, (len * 2) / Constants.PPM);
        rdef.upperAngle = (float) (0.1f * Math.PI);
        rdef.lowerAngle = -(float) (0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = player.getStomach3().getBody();
        rdef.localAnchorA.set(0, (len * 2) / Constants.PPM);
        rdef.localAnchorB.set(-(len / 2) / Constants.PPM, -(len * 3) / Constants.PPM);
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyA = player.getFrontArm().getBody();
        rdef.localAnchorA.set(0, (len * 3) / Constants.PPM);
        rdef.localAnchorB.set(-(len / 2) / Constants.PPM, (len * 3) / Constants.PPM);
        rdef.upperAngle = (float) (0.5f * Math.PI);
        rdef.lowerAngle = (float) (-1f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = player.getFrontHand().getBody();
        rdef.localAnchorA.set(0, -(len * 3) / Constants.PPM);
        rdef.localAnchorB.set(0, (len * 3) / Constants.PPM);
        rdef.upperAngle = (float) (0.4f * Math.PI);
        rdef.lowerAngle = 0;

        world.createJoint(rdef);

        rdef.bodyA = player.getBackArm().getBody();
        rdef.bodyB = player.getStomach3().getBody();
        rdef.localAnchorA.set(0, (len * 3) / Constants.PPM);
        rdef.localAnchorB.set(-(len / 2) / Constants.PPM, (len * 3) / Constants.PPM);
        rdef.upperAngle = (float) (0.5f * Math.PI);
        rdef.lowerAngle = (float) (-1f * Math.PI);

        world.createJoint(rdef);


        rdef.bodyB = player.getBackHand().getBody();
        rdef.localAnchorA.set(0, -(len * 3) / Constants.PPM);
        rdef.localAnchorB.set(0, (len * 3) / Constants.PPM);
        rdef.upperAngle = (float) (0.4f * Math.PI);
        rdef.lowerAngle = 0;

        world.createJoint(rdef);


        rdef.bodyA = player.getNeck().getBody();
        rdef.bodyB = player.getStomach3().getBody();
        rdef.localAnchorA.set(0, -(len * 2) / Constants.PPM);
        rdef.localAnchorB.set(-(len / 2) / Constants.PPM, (len * 3) / Constants.PPM);
        rdef.upperAngle = (float) (0.05f * Math.PI);
        rdef.lowerAngle = (float) (-0.05f * Math.PI);

        world.createJoint(rdef);

        rdef.bodyB = player.getHead().getBody();
        rdef.localAnchorA.set(0, (len * 2) / Constants.PPM);
        rdef.localAnchorB.set(-(len / 2) / Constants.PPM, -(len * 2.5f) / Constants.PPM);
        rdef.upperAngle = (float) (0.1f * Math.PI);
        rdef.lowerAngle = (float) (-0.1f * Math.PI);

        world.createJoint(rdef);


        //bqck joiminh---------------------------------------------------------------------------------
        rdef.bodyA = player.getBackFoot1().getBody();
        rdef.bodyB = player.getBackLeg().getBody();
        rdef.localAnchorA.set(-((len - (len / 2)) / Constants.PPM), 0);
        rdef.localAnchorB.set(0, -((len * 5 - (len / 2)) / Constants.PPM));
        rdef.lowerAngle = (float) (-0.2f * Math.PI);
        rdef.upperAngle = (float) (0.07f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = player.getBackFoot2().getBody();
        rdef.localAnchorA.set((len + len / 2) / Constants.PPM, 0);
        rdef.localAnchorB.set(-((len + len / 2) / Constants.PPM), 0);
        rdef.lowerAngle = 0;
        rdef.upperAngle = (float) (0.3f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyA = player.getBackTighs().getBody();
        rdef.bodyB = player.getBackLeg().getBody();
        rdef.localAnchorA.set(0, -(len * 5 - (len / 2)) / Constants.PPM);
        rdef.localAnchorB.set(0, ((len * 4 - (len / 2)) / Constants.PPM));
        rdef.upperAngle = 0;
        rdef.lowerAngle = -(float) (0.7f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);

        rdef.bodyB = player.getStomach1().getBody();
        rdef.localAnchorA.set(0, (len * 5 - (len / 2)) / Constants.PPM);
        rdef.localAnchorB.set(0, -(len * 2) / Constants.PPM);
        rdef.upperAngle = (float) (0.04f * Math.PI);
        rdef.lowerAngle = (float) (-0.5f * Math.PI);
        rdef.enableLimit = true;

        world.createJoint(rdef);
    }
}
