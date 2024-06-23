package dev.lyze.festive.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Array;
import lombok.Getter;

@Getter
public class Assets {
    private final Sound explosionSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Explosion Sharp.ogg"));
    private final Sound bombPickupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Collect 1.ogg"));
    private final Sound boosterPickupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Collect 4.ogg"));
    private final Sound reverseBoosterPickupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Power Down 3.ogg"));
    private final Sound uiClickSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Fireball Pluck.ogg"));
    private final Sound BoosterSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Power Up 3.ogg"));
    private final Sound coinSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Coin 3.ogg"));
    private final Sound gameOverSound = Gdx.audio.newSound(Gdx.files.internal("sounds/Fall.ogg"));

    private final Music mainMusic  = Gdx.audio.newMusic(Gdx.files.internal("music/8-Bit-Puzzler.mp3"));
    private final Music winMusic = Gdx.audio.newMusic(Gdx.files.internal("music/Cute-8-Bit-Monsters.mp3"));

    private final TextureAtlas mainAtlas = new TextureAtlas("atlases/main.atlas");

    private final Array<TextureAtlas.AtlasRegion> waterTiles;
    private final TextureAtlas.AtlasRegion startIsland ;
    private final TextureAtlas.AtlasRegion endIsland;
    private final Array<TextureAtlas.AtlasRegion> clouds;

    private final TextureAtlas.AtlasRegion booper;

    private final TextureAtlas.AtlasRegion coin;
    private final TextureAtlas.AtlasRegion booster;
    private final TextureAtlas.AtlasRegion reverseBooster;
    private final TextureAtlas.AtlasRegion bomb;

    private final Skin skin;
    private final Cursor cursor;

    private final TextureAtlas.AtlasRegion morgi;

    private final TextureAtlas.AtlasRegion morgiToes;
    private final TextureAtlas.AtlasRegion morgiFeet;
    private final TextureAtlas.AtlasRegion morgiLowerLegs;
    private final TextureAtlas.AtlasRegion morgiUpperLegs;

    private final TextureAtlas.AtlasRegion morgiLowerTorso;
    private final TextureAtlas.AtlasRegion morgiMiddleTorso;
    private final TextureAtlas.AtlasRegion morgiUpperTorso;

    private final TextureAtlas.AtlasRegion morgiUpperArm;
    private final TextureAtlas.AtlasRegion morgiLowerArm;
    private final TextureAtlas.AtlasRegion morgiHand;

    private final TextureAtlas.AtlasRegion morgiNeck;
    private final TextureAtlas.AtlasRegion morgiHead;

    public Assets() {
        waterTiles = mainAtlas.findRegions("Tiles/Water/Water");
        startIsland = mainAtlas.findRegion("Tiles/Start");
        endIsland = mainAtlas.findRegion("Tiles/End");

        clouds = mainAtlas.findRegions("Tiles/Clouds/Cloud");

        booper = mainAtlas.findRegion("Booper");

        coin = mainAtlas.findRegion("Collectibles/Coin");
        bomb = mainAtlas.findRegion("Collectibles/Bomb");
        booster = mainAtlas.findRegion("Collectibles/Booster");
        reverseBooster = mainAtlas.findRegion("Collectibles/ReverseBooster");

        skin = new Skin(Gdx.files.internal("skin/skin.json"));

        var cursorPixmap = new Pixmap(Gdx.files.internal("Cursor.png"));
        cursor = Gdx.graphics.newCursor(cursorPixmap, 15, 15);
        cursorPixmap.dispose();

        morgi = mainAtlas.findRegion("Morgi");

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

    public TextureAtlas.AtlasRegion getRandomCloudTile() {
        return getClouds().get(MathUtils.random(getClouds().size - 1));
    }

    public void playSound(Sound sound) {
        sound.play(0.3f, MathUtils.random(0.8f, 1.2f), 0f);
    }
}
