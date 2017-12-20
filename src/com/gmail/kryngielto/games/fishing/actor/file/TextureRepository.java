package com.gmail.kryngielto.games.fishing.actor.file;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Marcin on 20-Dec-17.
 */
public class TextureRepository {

    private static final Map<String, Texture> textures = new HashMap<>();

    public static Texture getTexture(String textureName) {
        Texture texture = textures.get(textureName);
        if (texture == null) {
            texture = new Texture((Gdx.files.internal(textureName)));
        }
        return texture;
    }

}
