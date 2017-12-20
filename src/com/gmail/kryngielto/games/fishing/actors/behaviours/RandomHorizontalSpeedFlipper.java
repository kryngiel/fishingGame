package com.gmail.kryngielto.games.fishing.actors.behaviours;

import com.gmail.kryngielto.games.fishing.actors.FishActor;
import com.gmail.kryngielto.games.fishing.utils.GaussRandom;
import com.gmail.kryngielto.games.fishing.utils.Parameters;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class RandomHorizontalSpeedFlipper implements Modifier {

    private float mean;
    private float deviation;
    private float timeToNextFlip;

    public RandomHorizontalSpeedFlipper() {
        this(Parameters.FISH_VELOCITY_FLIP_TIME_MEAN, Parameters.FISH_VELOCITY_FLIP_TIME_DEVIATION);
    }

    public RandomHorizontalSpeedFlipper(float mean, float deviation) {
        this.mean = mean;
        this.deviation = deviation;
    }

    @Override
    public void modify(FishActor fish, float delta) {
        timeToNextFlip -= delta;
        if (timeToNextFlip <= 0) {
            flip(fish);
            resetTimer();
        }
    }

    private void resetTimer() {
        do {
            timeToNextFlip = GaussRandom.get().nextGaussian(mean, deviation);
        } while (timeToNextFlip <= 0);
    }

    public void event(FishEvent event) {
        switch (event) {
            case FLIP:
                resetTimer();
                break;
            case FREE:
                mean = Parameters.FISH_VELOCITY_FLIP_TIME_MEAN;
                deviation = Parameters.FISH_VELOCITY_FLIP_TIME_DEVIATION;
                break;
            case CAUGHT:
                mean = Parameters.CAUGHT_FISH_VELOCITY_FLIP_PERIOD_MEAN;
                deviation = Parameters.CAUGHT_FISH_VELOCITY_FLIP_PERIOD_DEVIATION;
                break;
        }
    }

    private void flip(FishActor fish) {
        fish.setVelocityX(-fish.getVelocityX());
    }
}
