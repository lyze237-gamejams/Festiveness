package dev.lyze.festive.game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import lombok.Getter;

public class Assets {
    @Getter private final TextureAtlas mainAtlas = new TextureAtlas("atlases/main.atlas");

    @Getter private final Array<TextureAtlas.AtlasRegion> waterTiles;
    @Getter private final TextureAtlas.AtlasRegion startIsland ;
    @Getter private final TextureAtlas.AtlasRegion endIsland;
    @Getter private final Array<TextureAtlas.AtlasRegion> clouds;

    @Getter private final TextureAtlas.AtlasRegion booper;

    @Getter private final TextureAtlas.AtlasRegion morgiToes;
    @Getter private final TextureAtlas.AtlasRegion morgiFeet;
    @Getter private final TextureAtlas.AtlasRegion morgiLowerLegs;
    @Getter private final TextureAtlas.AtlasRegion morgiUpperLegs;

    @Getter private final TextureAtlas.AtlasRegion morgiLowerTorso;
    @Getter private final TextureAtlas.AtlasRegion morgiMiddleTorso;
    @Getter private final TextureAtlas.AtlasRegion morgiUpperTorso;

    @Getter private final TextureAtlas.AtlasRegion morgiUpperArm;
    @Getter private final TextureAtlas.AtlasRegion morgiLowerArm;
    @Getter private final TextureAtlas.AtlasRegion morgiHand;

    @Getter private final TextureAtlas.AtlasRegion morgiNeck;
    @Getter private final TextureAtlas.AtlasRegion morgiHead;

    public Assets() {
        waterTiles = mainAtlas.findRegions("Tiles/Water/Water");
        startIsland = mainAtlas.findRegion("Tiles/Start");
        endIsland = mainAtlas.findRegion("Tiles/End");
        clouds = mainAtlas.findRegions("Tiles/Clouds/Cloud");

        booper = mainAtlas.findRegion("Booper");


        morgiToes = mainAtlas.findRegion("Morgi/toes");
        morgiFeet = mainAtlas.findRegion("Morgi/feet");
        morgiLowerLegs = mainAtlas.findRegion("Morgi/lower legs");
        morgiUpperLegs = mainAtlas.findRegion("Morgi/upper legs");

        morgiLowerTorso = mainAtlas.findRegion("Morgi/low tors");
        morgiMiddleTorso = mainAtlas.findRegion("Morgi/mid tors");
        morgiUpperTorso = mainAtlas.findRegion("Morgi/upper tors");

        morgiUpperArm = mainAtlas.findRegion("Morgi/up arm");
        morgiLowerArm = mainAtlas.findRegion("Morgi/low arm");
        morgiHand = mainAtlas.findRegion("Morgi/hand");

        morgiNeck = mainAtlas.findRegion("Morgi/neck");
        morgiHead = mainAtlas.findRegion("Morgi/head");
    }

    public TextureAtlas.AtlasRegion getRandomWaterTile() {
        return getWaterTiles().get(MathUtils.random(getWaterTiles().size - 1));
    }
}
