package dev.lyze.festive.game.body.physics;

import com.badlogic.gdx.math.Vector2;
import dev.lyze.festive.Constants;
import dev.lyze.festive.game.body.Player;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;
import lombok.Getter;
import lombok.Setter;

public class BalancerBehaviour extends BehaviourAdapter {
    private final Player player;

    @Getter @Setter
    private boolean enabled = true;

    public BalancerBehaviour(Player player, GameObject gameObject) {
        super(gameObject);
        this.player = player;
    }

    @Override
    public void fixedUpdate() {
        if (!enabled)
            return;
        if ((player.getFrontLowerLeg().getBody().getAngle() * (180 / Math.PI)) < -5) {
            player.getFrontLowerLeg().getBody().applyForce(new Vector2(player.getFrontLowerLeg().getBody().getAngle() / (Constants.BodyPartPpm / 3), 0), new Vector2(player.getFrontLowerLeg().getBody().getWorldCenter().x, player.getFrontLowerLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
        }
        if ((player.getFrontLowerLeg().getBody().getAngle() * (180 / Math.PI)) > -5) {
            if (player.getFrontLowerLeg().getBody().getAngle() * (180 / Math.PI) > 0)
                player.getFrontLowerLeg().getBody().applyForce(new Vector2(player.getFrontLowerLeg().getBody().getAngle() / (Constants.BodyPartPpm / 3), 0), new Vector2(player.getFrontLowerLeg().getBody().getWorldCenter().x, player.getFrontLowerLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
            else
                player.getFrontLowerLeg().getBody().applyForce(new Vector2(-player.getFrontLowerLeg().getBody().getAngle() / (Constants.BodyPartPpm / 3), 0), new Vector2(player.getFrontLowerLeg().getBody().getWorldCenter().x, player.getFrontLowerLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
        }

        if ((player.getFrontUpperLeg().getBody().getAngle() * (180 / Math.PI)) < -5f) {
            player.getFrontUpperLeg().getBody().applyForce(new Vector2(player.getFrontUpperLeg().getBody().getAngle() / (Constants.BodyPartPpm / 5), 0), new Vector2(player.getFrontUpperLeg().getBody().getWorldCenter().x, player.getFrontUpperLeg().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }


        if ((player.getFrontUpperLeg().getBody().getAngle() * (180 / Math.PI)) > -5) {
            if ((player.getFrontUpperLeg().getBody().getAngle() * (180 / Math.PI)) >= 0)
                player.getFrontUpperLeg().getBody().applyForce(new Vector2(player.getFrontUpperLeg().getBody().getAngle() / (Constants.BodyPartPpm / 5), 0), new Vector2(player.getFrontUpperLeg().getBody().getWorldCenter().x, player.getFrontUpperLeg().getBody().getWorldCenter().y + Constants.Length * 5), true);
            else
                player.getFrontUpperLeg().getBody().applyForce(new Vector2(player.getFrontUpperLeg().getBody().getAngle() / (Constants.BodyPartPpm / 5), 0), new Vector2(player.getFrontUpperLeg().getBody().getWorldCenter().x, player.getFrontUpperLeg().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        /*if (AllVariables.Front_foot1.getAngle() * (180 / Math.PI) < 0) {
            //AllVariables.Front_foot1.setAngularVelocity(200);
        }*/
        player.getBackFoot().getBody().setLinearVelocity(0, -1f);


        //back---------------------------------
        if ((player.getBackLowerLeg().getBody().getAngle() * (180 / Math.PI)) < -5f) {
            player.getBackLowerLeg().getBody().applyForce(new Vector2(player.getBackLowerLeg().getBody().getAngle() / (Constants.BodyPartPpm / 3), 0), new Vector2(player.getBackLowerLeg().getBody().getWorldCenter().x, player.getBackLowerLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
        }
        if ((player.getBackLowerLeg().getBody().getAngle() * (180 / Math.PI)) > -5f) {
            if (player.getBackLowerLeg().getBody().getAngle() * (180 / Math.PI) > 0)
                player.getBackLowerLeg().getBody().applyForce(new Vector2(player.getBackLowerLeg().getBody().getAngle() / (Constants.BodyPartPpm / 3), 0), new Vector2(player.getBackLowerLeg().getBody().getWorldCenter().x, player.getBackLowerLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
            else
                player.getBackLowerLeg().getBody().applyForce(new Vector2(-player.getBackLowerLeg().getBody().getAngle() / (Constants.BodyPartPpm / 3), 0), new Vector2(player.getBackLowerLeg().getBody().getWorldCenter().x, player.getBackLowerLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);

        }

        if ((player.getBackUpperLeg().getBody().getAngle() * (180 / Math.PI)) <= -5) {
            player.getBackUpperLeg().getBody().applyForce(new Vector2(player.getBackUpperLeg().getBody().getAngle() / (Constants.BodyPartPpm / 5), 0), new Vector2(player.getBackUpperLeg().getBody().getWorldCenter().x, player.getBackUpperLeg().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getBackUpperLeg().getBody().getAngle() * (180 / Math.PI)) > -5) {
            if ((player.getBackUpperLeg().getBody().getAngle() * (180 / Math.PI)) >= 0)
                player.getBackUpperLeg().getBody().applyForce(new Vector2(player.getBackUpperLeg().getBody().getAngle() / (Constants.BodyPartPpm / 5), 0), new Vector2(player.getBackUpperLeg().getBody().getWorldCenter().x, player.getBackUpperLeg().getBody().getWorldCenter().y + Constants.Length * 5), true);
            else
                player.getBackUpperLeg().getBody().applyForce(new Vector2(player.getBackUpperLeg().getBody().getAngle() / (Constants.BodyPartPpm / 5), 0), new Vector2(player.getBackUpperLeg().getBody().getWorldCenter().x, player.getBackUpperLeg().getBody().getWorldCenter().y + Constants.Length * 5), true);

        }

        /*if (AllVariables.Back_foot1.getAngle() * (180 / Math.PI) < 0) {
            //AllVariables.Back_foot1.setAngularVelocity(200);
        }
        */
        player.getBackFoot().getBody().setLinearVelocity(0, -1f);

        //commom ---------------------------

        if ((player.getStomach1().getBody().getAngle() * (180 / Math.PI)) > 1f) {
            player.getStomach1().getBody().applyForce(new Vector2(player.getStomach1().getBody().getAngle() / (Constants.BodyPartPpm / 3), 0), new Vector2(player.getStomach1().getBody().getWorldCenter().x, player.getStomach1().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }
        if ((player.getStomach1().getBody().getAngle() * (180 / Math.PI)) <= 1f) {
            player.getStomach1().getBody().applyForce(new Vector2(player.getStomach1().getBody().getAngle() / (Constants.BodyPartPpm / 3), 0), new Vector2(player.getStomach1().getBody().getWorldCenter().x, player.getStomach1().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getStomach2().getBody().getAngle() * (180 / Math.PI)) <= -2f) {
            player.getStomach2().getBody().applyForce(new Vector2(player.getStomach2().getBody().getAngle() / (Constants.BodyPartPpm / 2), 0), new Vector2(player.getStomach2().getBody().getWorldCenter().x, player.getStomach2().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getStomach2().getBody().getAngle() * (180 / Math.PI)) > -2) {
            player.getStomach2().getBody().applyForce(new Vector2(player.getStomach2().getBody().getAngle() / (Constants.BodyPartPpm / 2), 0), new Vector2(player.getStomach2().getBody().getWorldCenter().x, player.getStomach2().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getStomach3().getBody().getAngle() * (180 / Math.PI)) <= -2f) {
            player.getStomach3().getBody().applyForce(new Vector2(player.getStomach3().getBody().getAngle() / (Constants.BodyPartPpm / 2), 0), new Vector2(player.getStomach3().getBody().getWorldCenter().x, player.getStomach3().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getStomach3().getBody().getAngle() * (180 / Math.PI)) > -2) {
            player.getStomach3().getBody().applyForce(new Vector2(player.getStomach3().getBody().getAngle() / (Constants.BodyPartPpm / 2), 0), new Vector2(player.getStomach3().getBody().getWorldCenter().x, player.getStomach3().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getNeck().getBody().getAngle() * (180 / Math.PI)) < -1f) {
            player.getNeck().getBody().applyForce(new Vector2(player.getNeck().getBody().getAngle() / (Constants.BodyPartPpm / 2), 0), new Vector2(player.getNeck().getBody().getWorldCenter().x, player.getNeck().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getNeck().getBody().getAngle() * (180 / Math.PI)) > -1) {
            player.getNeck().getBody().applyForce(new Vector2(player.getNeck().getBody().getAngle() / (Constants.BodyPartPpm / 2), 0), new Vector2(player.getNeck().getBody().getWorldCenter().x, player.getNeck().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getHead().getBody().getAngle() * (180 / Math.PI)) < -1f) {
            player.getHead().getBody().applyForce(new Vector2(player.getHead().getBody().getAngle() / (Constants.BodyPartPpm / 2), 0), new Vector2(player.getHead().getBody().getWorldCenter().x, player.getHead().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getHead().getBody().getAngle() * (180 / Math.PI)) > -1) {
            player.getHead().getBody().applyForce(new Vector2(player.getHead().getBody().getAngle() / (Constants.BodyPartPpm / 2), 0), new Vector2(player.getHead().getBody().getWorldCenter().x, player.getHead().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }
    }
}
