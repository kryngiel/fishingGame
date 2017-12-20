package com.gmail.kryngielto.games.fishing.actor;

import com.badlogic.gdx.math.Rectangle;
import com.gmail.kryngielto.games.fishing.util.Parameters;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class MovingActor extends BasicActor {

    private Rectangle boundary = new Rectangle();

    @Override
    public void act (float delta) {
        super.act(delta);
        moveBy(getVelocityX() * delta, getVelocityY() * delta);
        flipSpeedIfMapEdgeAchieved();
    }

    protected void flipSpeedIfMapEdgeAchieved() {
        if (getX() + getWidth() >= Parameters.GAME_MAP_SIZE.X) {
            setVelocityX(-Math.abs(getVelocityX()));
        }
        if (getX() <=0) {
            setVelocityX(Math.abs(getVelocityX()));
        }
    }

    public Rectangle getBoundary() {
        boundary.set(getX(), getY(), getWidth(), getHeight());
        return boundary;
    }
}