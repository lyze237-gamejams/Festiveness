package dev.lyze.festive;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.gempukku.libgdx.camera2d.Camera2DController;
import com.gempukku.libgdx.camera2d.UpdateCameraControl;
import com.gempukku.libgdx.camera2d.constraint.LockedCamera2DConstraint;
import com.gempukku.libgdx.camera2d.constraint.LockedToWindowCamera2DConstraint;
import com.gempukku.libgdx.camera2d.constraint.SceneCamera2DConstraint;
import com.gempukku.libgdx.camera2d.constraint.SnapToWindowCamera2DConstraint;
import com.gempukku.libgdx.camera2d.focus.EntityFocus;
import dev.lyze.festive.game.body.Player;
import dev.lyze.gdxUnBox2d.GameObject;
import dev.lyze.gdxUnBox2d.behaviours.BehaviourAdapter;

public class CameraBehaviour extends BehaviourAdapter {
    private final UpdateCameraControl updater;
    private final Camera2DController controller;

    public CameraBehaviour(Player player, GameObject gameObject) {
        super(gameObject);

        updater = new UpdateCameraControl(Constants.viewport.getCamera());

        var lockPos = 0.25f;
        var lockSize = 1 - (lockPos * 2);

        var snapPos = 0.4f;
        var snapSize = 1 - (snapPos * 2);

        controller = new Camera2DController(
            new EntityFocus(position -> player.getStomach2().getBody().getPosition()),
            new SnapToWindowCamera2DConstraint(new Rectangle(snapPos, snapPos, snapSize, snapSize), new Vector2(0.2f, 0.2f)),
            new LockedToWindowCamera2DConstraint(new Rectangle(lockPos, lockPos, lockSize, lockSize)),
            new SceneCamera2DConstraint(new Rectangle(0, 0, Float.MAX_VALUE, Float.MAX_VALUE))
            );
    }

    @Override
    public void update(float delta) {
        controller.update(delta, updater);
    }
}
