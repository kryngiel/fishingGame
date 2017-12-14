package com.gmail.kryngielto.games.fishing.game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.gmail.kryngielto.games.fishing.utils.Constants;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class MainDriver {
    public static void main(String[] args) {
        LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
        cfg.title = "Title";
        cfg.height = Constants.GAME_MAP_SIZE.Y;
        cfg.width = Constants.GAME_MAP_SIZE.X;
        cfg.resizable = false;
        new LwjglApplication(new FishingGame(), cfg);
    }
}
