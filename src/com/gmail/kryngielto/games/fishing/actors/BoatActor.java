package com.gmail.kryngielto.games.fishing.actors;

import com.gmail.kryngielto.games.fishing.utils.Constants;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class BoatActor extends MovingActor {

    public BoatActor() {
        setVelocityX(Constants.DEFAULT_BOAT_SPEED);
        setY(Constants.VERTICAL_BOAT_POSITION);
    }

}
