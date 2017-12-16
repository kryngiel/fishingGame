package com.gmail.kryngielto.games.fishing.actors.behaviours;

import com.gmail.kryngielto.games.fishing.actors.FishActor;
import com.gmail.kryngielto.games.fishing.utils.GaussRandom;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class RandomHorizontalSpeedFlipper implements FishVelocityModifier {

    private float mean;
    private float deviation;
    private float timeToNextFlip;
    private GaussRandom random = new GaussRandom();


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

    public void resetTimer() {
        do {
            timeToNextFlip = random.nextGaussian(mean, deviation);
        } while (timeToNextFlip <= 0);
    }

    private void flip(FishActor fish) {
        fish.setVelocityX(-fish.getVelocityX());
    }
}
