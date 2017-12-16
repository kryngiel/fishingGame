package com.gmail.kryngielto.games.fishing.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gmail.kryngielto.games.fishing.utils.Parameters;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class MainDriver {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = Parameters.GAME_WINDOW_TITLE;
        cfg.height = Parameters.GAME_MAP_SIZE.Y;
        cfg.width = Parameters.GAME_MAP_SIZE.X;
        cfg.resizable = false;
        new LwjglApplication(new FishingGame(), cfg);
    }
}
