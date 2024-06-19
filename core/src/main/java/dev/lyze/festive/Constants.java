package dev.lyze.festive;

import com.badlogic.gdx.utils.viewport.ExtendViewport;
import dev.lyze.festive.eventsystem.EventManager;
import dev.lyze.festive.game.Assets;

import javax.swing.text.View;

public class Constants {
    public static float BodyPartPpm = 50f;
    public static float GraphicsPpm = 0.0007f * 2 * 2;
    public static float Length = 2;

    public static final short Bit_PlayerFront = 32;
    public static final short Bit_PlayerBack = 64;
    public static final short Bit_Ground = 128;
    public static final short Bit_Enemies = 256;
    public static final short Bit_Tools = 512;

    public static final int FinalIslandScreenNumber = 24;
    public static final float FinalIslandSpawnPosition = 160;

    public static final ExtendViewport viewport = new ExtendViewport(16,9);
    public static final EventManager events = new EventManager();

    public static final Assets assets = new Assets();
}
