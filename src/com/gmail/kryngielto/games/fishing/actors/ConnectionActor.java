package com.gmail.kryngielto.games.fishing.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.gmail.kryngielto.games.fishing.utils.ShapeDrawer;

/**
 * Created by Marcin on 17-Dec-17.
 */
public class ConnectionActor extends BasicActor {

    private float thickness = 1;
    private FishActor first;
    private FishActor second;

    public ConnectionActor(FishActor first, FishActor second) {
        this.first = first;
        this.second = second;
        setColor(Color.DARK_GRAY);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if (isVisible()) {
            ShapeDrawer.drawColorfulLine(batch, first.getX(), first.getY(), second.getX(), second.getY(), thickness, getColor());
        }
    }
}
