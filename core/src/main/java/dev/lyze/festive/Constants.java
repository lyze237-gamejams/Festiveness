package dev.lyze.festive;

import com.badlogic.gdx.utils.viewport.FitViewport;
import dev.lyze.festive.eventsystem.EventManager;
import dev.lyze.festive.game.Assets;

public class Constants {
    public static float BodyPartPpm = 50f;
    public static float GraphicsPpm = 0.0007f * 2 * 2;
    public static float Length = 2;

    public static final short Bit_PlayerFront = 32;
    public static final short Bit_PlayerBack = 64;
    public static final short Bit_Ground = 128;
    public static final short Bit_Enemies = 256;
    public static final short Bit_Tools = 512;

    public static final float FinalIslandSpawnPosition = 2000;

    public static final FitViewport viewport = new FitViewport(16,9);
    public static final EventManager events = new EventManager();

    public static final Assets assets = new Assets();
}
