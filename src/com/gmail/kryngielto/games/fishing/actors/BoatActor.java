package com.gmail.kryngielto.games.fishing.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.gmail.kryngielto.games.fishing.utils.Parameters;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class BoatActor extends MovingActor {

    private FloatPair relativeLineStartingPoint = new FloatPair(12, 54);

    public BoatActor() {
        if (Parameters.TROLL_MODE) {
            setTexture(new Texture(Gdx.files.internal(Parameters.TROLL_BOAT_IMAGE)));
        } else {
            setTexture(new Texture(Gdx.files.internal(Parameters.BOAT_IMAGE)));
        }
        setVelocityX(Parameters.DEFAULT_BOAT_SPEED);
        setY(Parameters.VERTICAL_BOAT_POSITION);
    }

    public FloatPair getLineStartingPoint() {
        return new FloatPair(getX() + relativeLineStartingPoint.X, getY() + relativeLineStartingPoint.Y);
    }

    @Override
    public void act (float delta) {
        super.act(delta);
        if (Gdx.input.isKeyJustPressed(Parameters.TROLL_MODE_SWITCH_BUTTON)) {
            Parameters.TROLL_MODE = !Parameters.TROLL_MODE;
            if (Parameters.TROLL_MODE) {
                setTexture(new Texture(Gdx.files.internal(Parameters.TROLL_BOAT_IMAGE)));
            } else {
                setTexture(new Texture(Gdx.files.internal(Parameters.BOAT_IMAGE)));
            }
        }
    }

    @Override
    public boolean isVisible () {
        return Parameters.BOAT_VISIBLE;
    }
}
