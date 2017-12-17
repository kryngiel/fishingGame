package com.gmail.kryngielto.games.fishing.utils;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class Parameters {

    private Parameters() {}

    public static boolean BOAT_VISIBLE = false;

    public static boolean DRAW_CONNECTIONS = false;

    public static String GAME_WINDOW_TITLE = "Fishing Game";


    public static IntPair GAME_MAP_SIZE = new IntPair(1500, 850);

    public static boolean TROLL_MODE = false;
    public static int VERTICAL_BOAT_POSITION = 760;
    public static int DEFAULT_BOAT_SPEED = 50;
    public static IntPair DEFAULT_FISH_SIZE = new IntPair(48, 19);
    public static int LAKE_DEPTH = 790;
    public static Color BROWN_COLOR = new Color(0.33f, 0.13f, 0.06f, 0.75f);

    // BUTTONS
    public static int RESET_HOOK_BUTTON = Input.Keys.R;
    public static int CONTROL_BUTTON = Input.Keys.SPACE;
    public static int TROLL_MODE_SWITCH_BUTTON = Input.Keys.T;

    // IMAGES
    public static String LAKE_IMAGE = "images/lake.png";
    public static String BOAT_IMAGE = "images/boat.png";
    public static String TROLL_BOAT_IMAGE = "images/troll_boat.png";

    // FISH DYNAMICS
    public static FloatPair AVERAGE_FISH_VELOCITY = new FloatPair(100, 0);
    public static float CAUGHT_FISH_SPEED_MODIFIER = 3f;
    public static float FISH_VELOCITY_FLIP_TIME_MEAN = 6;
    public static float FISH_VELOCITY_FLIP_TIME_DEVIATION = 2;
    public static float CAUGHT_FISH_VELOCITY_FLIP_PERIOD_DEVIATION = 0.5f;
    public static float CAUGHT_FISH_VELOCITY_FLIP_PERIOD_MEAN = 0.3f;
    public static float DEFAULT_WATER_RESISTANCE_FACTOR = 0.005f;
    public static float DEFAULT_FISH_ACCELERATION_TIME = 0.02f;
    public static float DEFAULT_FISH_ACCELERATION = 5000.0f;
    public static float FIN_PERIOD_MEAN = 2.3f;
    public static float FIN_PERIOD_DEVIATION = 1.1f;
}
