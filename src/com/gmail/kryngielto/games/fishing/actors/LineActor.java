package com.gmail.kryngielto.games.fishing.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;
import com.gmail.kryngielto.games.fishing.utils.Parameters;
import com.gmail.kryngielto.games.fishing.utils.ShapeDrawer;

import java.util.List;

/**
 * Created by Marcin on 16-Dec-17.
 */
public class LineActor extends BasicActor {

    private float thickness = 1f;
    private BoatActor boat;
    List<FishActor> fishes;
    private float lineSpeedDown = 180;
    private float lineSpeedUp = 310;
    private boolean caughtSomething = false;
    private FishActor caughtFish;

    public LineActor(BoatActor boat, List<FishActor> fishes) {
        this.boat = boat;
        this.fishes = fishes;

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
            ShapeDrawer.drawColorfulLine(batch, startingPoint.X, startingPoint.Y, getX(), getY(), thickness, getColor());
        }
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        handleDebugButtons();
        if (caughtSomething) {
            caughtSomethingAct(delta);
        } else {
            emptyHookAct(delta);
        }
    }

    private void handleDebugButtons() {
        if (Gdx.input.isKeyPressed(Parameters.RESET_HOOK_BUTTON)) {
            caughtSomething = false;
            if (caughtFish != null) {
                caughtFish.free();
            }
            setX(boat.getLineStartingPoint().X);
            setY(boat.getLineStartingPoint().Y);
        }
    }

    private void caughtSomethingAct(float delta) {
        FloatPair mouthPosition = caughtFish.getMouthPosition();
        setX(mouthPosition.X);
        setY(mouthPosition.Y);
    }

    private void emptyHookAct(float delta) {
        moveHook(delta);
        checkFishCaught(delta);
    }

    private void checkFishCaught(float delta) {
        for (FishActor fish : fishes) {
            if (fish.getBoundary().contains(getX(), getY())) {
                caughtSomething = true;
                fish.caught();
                caughtFish = fish;
                break; // catch one fish a time
            }
        }
    }

    private void moveHook(float delta) {
        setX(boat.getLineStartingPoint().X);
        if (Gdx.input.isKeyPressed(Parameters.CONTROL_BUTTON) || Gdx.input.isButtonPressed(0)) {
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
