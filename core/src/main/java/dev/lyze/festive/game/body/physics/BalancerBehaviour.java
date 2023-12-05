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
        if ((player.getFrontLeg().getBody().getAngle() * (180 / Math.PI)) < -5) {
            player.getFrontLeg().getBody().applyForce(new Vector2(player.getFrontLeg().getBody().getAngle() / (Constants.PPM / 3), 0), new Vector2(player.getFrontLeg().getBody().getWorldCenter().x, player.getFrontLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
        }
        if ((player.getFrontLeg().getBody().getAngle() * (180 / Math.PI)) > -5) {
            if (player.getFrontLeg().getBody().getAngle() * (180 / Math.PI) > 0)
                player.getFrontLeg().getBody().applyForce(new Vector2(player.getFrontLeg().getBody().getAngle() / (Constants.PPM / 3), 0), new Vector2(player.getFrontLeg().getBody().getWorldCenter().x, player.getFrontLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
            else
                player.getFrontLeg().getBody().applyForce(new Vector2(-player.getFrontLeg().getBody().getAngle() / (Constants.PPM / 3), 0), new Vector2(player.getFrontLeg().getBody().getWorldCenter().x, player.getFrontLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
        }

        if ((player.getFrontTighs().getBody().getAngle() * (180 / Math.PI)) < -5f) {
            player.getFrontTighs().getBody().applyForce(new Vector2(player.getFrontTighs().getBody().getAngle() / (Constants.PPM / 5), 0), new Vector2(player.getFrontTighs().getBody().getWorldCenter().x, player.getFrontTighs().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }


        if ((player.getFrontTighs().getBody().getAngle() * (180 / Math.PI)) > -5) {
            if ((player.getFrontTighs().getBody().getAngle() * (180 / Math.PI)) >= 0)
                player.getFrontTighs().getBody().applyForce(new Vector2(player.getFrontTighs().getBody().getAngle() / (Constants.PPM / 5), 0), new Vector2(player.getFrontTighs().getBody().getWorldCenter().x, player.getFrontTighs().getBody().getWorldCenter().y + Constants.Length * 5), true);
            else
                player.getFrontTighs().getBody().applyForce(new Vector2(player.getFrontTighs().getBody().getAngle() / (Constants.PPM / 5), 0), new Vector2(player.getFrontTighs().getBody().getWorldCenter().x, player.getFrontTighs().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        /*if (AllVariables.Front_foot1.getAngle() * (180 / Math.PI) < 0) {
            //AllVariables.Front_foot1.setAngularVelocity(200);
        }*/
        player.getBackFoot1().getBody().setLinearVelocity(0, -1f);


        //back---------------------------------
        if ((player.getBackLeg().getBody().getAngle() * (180 / Math.PI)) < -5f) {
            player.getBackLeg().getBody().applyForce(new Vector2(player.getBackLeg().getBody().getAngle() / (Constants.PPM / 3), 0), new Vector2(player.getBackLeg().getBody().getWorldCenter().x, player.getBackLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
        }
        if ((player.getBackLeg().getBody().getAngle() * (180 / Math.PI)) > -5f) {
            if (player.getBackLeg().getBody().getAngle() * (180 / Math.PI) > 0)
                player.getBackLeg().getBody().applyForce(new Vector2(player.getBackLeg().getBody().getAngle() / (Constants.PPM / 3), 0), new Vector2(player.getBackLeg().getBody().getWorldCenter().x, player.getBackLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);
            else
                player.getBackLeg().getBody().applyForce(new Vector2(-player.getBackLeg().getBody().getAngle() / (Constants.PPM / 3), 0), new Vector2(player.getBackLeg().getBody().getWorldCenter().x, player.getBackLeg().getBody().getWorldCenter().y + Constants.Length * 4), true);

        }

        if ((player.getBackTighs().getBody().getAngle() * (180 / Math.PI)) <= -5) {
            player.getBackTighs().getBody().applyForce(new Vector2(player.getBackTighs().getBody().getAngle() / (Constants.PPM / 5), 0), new Vector2(player.getBackTighs().getBody().getWorldCenter().x, player.getBackTighs().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getBackTighs().getBody().getAngle() * (180 / Math.PI)) > -5) {
            if ((player.getBackTighs().getBody().getAngle() * (180 / Math.PI)) >= 0)
                player.getBackTighs().getBody().applyForce(new Vector2(player.getBackTighs().getBody().getAngle() / (Constants.PPM / 5), 0), new Vector2(player.getBackTighs().getBody().getWorldCenter().x, player.getBackTighs().getBody().getWorldCenter().y + Constants.Length * 5), true);
            else
                player.getBackTighs().getBody().applyForce(new Vector2(player.getBackTighs().getBody().getAngle() / (Constants.PPM / 5), 0), new Vector2(player.getBackTighs().getBody().getWorldCenter().x, player.getBackTighs().getBody().getWorldCenter().y + Constants.Length * 5), true);

        }

        /*if (AllVariables.Back_foot1.getAngle() * (180 / Math.PI) < 0) {
            //AllVariables.Back_foot1.setAngularVelocity(200);
        }
        */
        player.getBackFoot1().getBody().setLinearVelocity(0, -1f);

        //commom ---------------------------

        if ((player.getStomach1().getBody().getAngle() * (180 / Math.PI)) > 1f) {
            player.getStomach1().getBody().applyForce(new Vector2(player.getStomach1().getBody().getAngle() / (Constants.PPM / 3), 0), new Vector2(player.getStomach1().getBody().getWorldCenter().x, player.getStomach1().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }
        if ((player.getStomach1().getBody().getAngle() * (180 / Math.PI)) <= 1f) {
            player.getStomach1().getBody().applyForce(new Vector2(player.getStomach1().getBody().getAngle() / (Constants.PPM / 3), 0), new Vector2(player.getStomach1().getBody().getWorldCenter().x, player.getStomach1().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getStomach2().getBody().getAngle() * (180 / Math.PI)) <= -2f) {
            player.getStomach2().getBody().applyForce(new Vector2(player.getStomach2().getBody().getAngle() / (Constants.PPM / 2), 0), new Vector2(player.getStomach2().getBody().getWorldCenter().x, player.getStomach2().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getStomach2().getBody().getAngle() * (180 / Math.PI)) > -2) {
            player.getStomach2().getBody().applyForce(new Vector2(player.getStomach2().getBody().getAngle() / (Constants.PPM / 2), 0), new Vector2(player.getStomach2().getBody().getWorldCenter().x, player.getStomach2().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getStomach3().getBody().getAngle() * (180 / Math.PI)) <= -2f) {
            player.getStomach3().getBody().applyForce(new Vector2(player.getStomach3().getBody().getAngle() / (Constants.PPM / 2), 0), new Vector2(player.getStomach3().getBody().getWorldCenter().x, player.getStomach3().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getStomach3().getBody().getAngle() * (180 / Math.PI)) > -2) {
            player.getStomach3().getBody().applyForce(new Vector2(player.getStomach3().getBody().getAngle() / (Constants.PPM / 2), 0), new Vector2(player.getStomach3().getBody().getWorldCenter().x, player.getStomach3().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getNeck().getBody().getAngle() * (180 / Math.PI)) < -1f) {
            player.getNeck().getBody().applyForce(new Vector2(player.getNeck().getBody().getAngle() / (Constants.PPM / 2), 0), new Vector2(player.getNeck().getBody().getWorldCenter().x, player.getNeck().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getNeck().getBody().getAngle() * (180 / Math.PI)) > -1) {
            player.getNeck().getBody().applyForce(new Vector2(player.getNeck().getBody().getAngle() / (Constants.PPM / 2), 0), new Vector2(player.getNeck().getBody().getWorldCenter().x, player.getNeck().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getHead().getBody().getAngle() * (180 / Math.PI)) < -1f) {
            player.getHead().getBody().applyForce(new Vector2(player.getHead().getBody().getAngle() / (Constants.PPM / 2), 0), new Vector2(player.getHead().getBody().getWorldCenter().x, player.getHead().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }

        if ((player.getHead().getBody().getAngle() * (180 / Math.PI)) > -1) {
            player.getHead().getBody().applyForce(new Vector2(player.getHead().getBody().getAngle() / (Constants.PPM / 2), 0), new Vector2(player.getHead().getBody().getWorldCenter().x, player.getHead().getBody().getWorldCenter().y + Constants.Length * 5), true);
        }
    }
}
