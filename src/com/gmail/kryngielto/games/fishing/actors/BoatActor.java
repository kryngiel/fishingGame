package com.gmail.kryngielto.games.fishing.actors;

import com.gmail.kryngielto.games.fishing.utils.Constants;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class BoatActor extends MovingActor {

    private FloatPair relativeLineStartingPoint = new FloatPair(12, 54);

    public BoatActor() {
        setVelocityX(Constants.DEFAULT_BOAT_SPEED);
        setY(Constants.VERTICAL_BOAT_POSITION);
    }

    public FloatPair getLineStartingPoint() {
        return new FloatPair(getX() + relativeLineStartingPoint.X, getY() + relativeLineStartingPoint.Y);
    }
}
