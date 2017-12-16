package com.gmail.kryngielto.games.fishing.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class Constants {

    private Constants() {}

    public static final String GAME_WINDOW_TITLE = "Fishing Game";
    public static final IntPair GAME_MAP_SIZE = new IntPair(1500, 850);
    public static final int VERTICAL_BOAT_POSITION = 760;
    public static final int DEFAULT_BOAT_SPEED = 50;
    public static final IntPair DEFAULT_FISH_SIZE = new IntPair(48, 19);
    public static final int LAKE_DEPTH = 790;
    public static final FloatPair AVERAGE_FISH_VELOCITY = new FloatPair(100, 0);
    public static final float FISH_VELOCITY_FLIP_TIME_MEAN = 1;
    public static final float FISH_VELOCITY_FLIP_TIME_DEVIATION = 2;
    public static final Color BROWN_COLOR = new Color(0.33f, 0.13f, 0.06f, 0.75f);

    // BUTTONS
    public static final int RESET_HOOK_BUTTON = Input.Keys.R;
    public static final int CONTROL_BUTTON = Input.Keys.SPACE;
}
