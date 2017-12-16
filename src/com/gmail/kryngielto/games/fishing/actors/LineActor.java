package com.gmail.kryngielto.games.fishing.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;
import com.gmail.kryngielto.games.fishing.utils.ShapeDrawer;

/**
 * Created by Marcin on 16-Dec-17.
 */
public class LineActor extends BasicActor {

    private float thickness = 1f;
    private BoatActor boat;
    private float lineSpeedDown = 180;
    private float lineSpeedUp = 310;

    public LineActor(BoatActor boat) {
        this.boat = boat;
        //setColor(new Color(0.33f, 0.13f, 0.06f, 0.75f));
        FloatPair startingPoint = boat.getLineStartingPoint();
        setX(startingPoint.X);
        setY(startingPoint.Y);
        setColor(Color.DARK_GRAY);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if (isVisible()) {
            FloatPair startingPoint = boat.getLineStartingPoint();
            ShapeDrawer.drawColorfulLine(batch, startingPoint.X, startingPoint.Y, startingPoint.X, getY(), thickness, getColor());

        }
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        emptyHookAct(delta);
    }

    private void emptyHookAct(float delta) {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            if (getY() <= 0) {
                setY(0);
            } else {
                setY(getY() - lineSpeedDown * delta);
            }
        } else {
            if (getY() >= boat.getLineStartingPoint().Y) {
                setY(boat.getLineStartingPoint().Y);
            } else {
                setY(getY() + lineSpeedUp * delta);
            }
        }
    }

    public float getThickness() {
        return thickness;
    }

    public void setThickness(float thickness) {
        this.thickness = thickness;
    }

}
