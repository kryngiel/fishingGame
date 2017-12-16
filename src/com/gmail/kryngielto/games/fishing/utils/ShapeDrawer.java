package com.gmail.kryngielto.games.fishing.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Marcin on 16-Dec-17.
 */
public class ShapeDrawer {

    private static final TextureRegion whitePixel = new TextureRegion(new Texture(Gdx.files.internal("images/white_pixel.png")));

    public static void drawLine(Batch batch, float x1, float y1, float x2, float y2, float thickness) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        float dist = (float) Math.sqrt(dx * dx + dy * dy);
        float angle = (float) Math.toDegrees(Math.atan2(dy, dx));
        batch.draw(whitePixel, x1, y1, 0, 0,
                dist, thickness, 1, 1, angle);
    }

    public static void drawColorfulLine(Batch batch, float x1, float y1, float x2, float y2, float thickness, Color color) {
        batch.setColor(color);
        drawLine(batch, x1, y1, x2, y2, thickness);
    }


}
