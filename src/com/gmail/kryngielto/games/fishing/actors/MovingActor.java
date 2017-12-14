package com.gmail.kryngielto.games.fishing.actors;

import com.gmail.kryngielto.games.fishing.utils.Constants;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class MovingActor extends BasicActor {

    @Override
    public void act (float delta) {
        super.act(delta);
        flipSpeedIfMapEdgeAchieved();
    }

    private void flipSpeedIfMapEdgeAchieved() {
        if (getX() + getWidth() >= Constants.GAME_MAP_SIZE.X) {
            setVelocityX(-Math.abs(getVelocityX()));
        }
        if (getX() <=0) {
            setVelocityX(Math.abs(getVelocityX()));
        }
    }
}