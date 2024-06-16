package dev.lyze.festive.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Assets {
    @Getter private static final Texture[] waterTiles = new Texture[] { new Texture("Tiles/Water/Water1.png"), new Texture("Tiles/Water/Water2.png"), new Texture("Tiles/Water/Water3.png") };
    @Getter private static final Texture startIsland = new Texture("Tiles/Start.png");
    @Getter private static final Texture endIsland = new Texture("Tiles/End.png");
    @Getter private static final Texture[] clouds = new Texture[] { new Texture("Tiles/Clouds/Cloud1.png"), new Texture("Tiles/Clouds/Cloud2.png"), new Texture("Tiles/Clouds/Cloud3.png") };

    @Getter private static final Texture booper = new Texture("booper.png");

    @Getter private static final Texture morgiToes = new Texture("Morgi/toes.png");
    @Getter private static final Texture morgiFeet = new Texture("Morgi/feet.png");
    @Getter private static final Texture morgiLowerLegs = new Texture("Morgi/lower legs.png");
    @Getter private static final Texture morgiUpperLegs = new Texture("Morgi/upper legs.png");

    @Getter private static final Texture morgiLowerTorso = new Texture("Morgi/low tors.png");
    @Getter private static final Texture morgiMiddleTorso = new Texture("Morgi/mid tors.png");
    @Getter private static final Texture morgiUpperTorso = new Texture("Morgi/upper tors.png");

    @Getter private static final Texture morgiUpperArm = new Texture("Morgi/up arm.png");
    @Getter private static final Texture morgiLowerArm = new Texture("Morgi/low arm.png");
    @Getter private static final Texture morgiHand = new Texture("Morgi/hand.png");

    @Getter private static final Texture morgiNeck = new Texture("Morgi/neck.png");
    @Getter private static final Texture morgiHead = new Texture("Morgi/head.png");

    public static Texture getRandomWaterTile() {
        return Assets.getWaterTiles()[MathUtils.random(Assets.getWaterTiles().length - 1)];
    }
}
