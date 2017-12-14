package com.gmail.kryngielto.games.fishing.actors.behaviours;

import com.gmail.kryngielto.games.fishing.actors.FishActor;

import java.util.Random;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class RandomHorizontalSpeedFlipper implements FishVelocityModifier {

    private float fiftyPercentFlipPeriodInSeconds;
    private float currentCycleTimeInSeconds = 0;
    private Random random = new Random();

    public RandomHorizontalSpeedFlipper(float fiftyPercentFlipPeriodInSeconds) {
        this.fiftyPercentFlipPeriodInSeconds = fiftyPercentFlipPeriodInSeconds;
    }

    @Override
    public void modify(FishActor fish, float delta) {
        currentCycleTimeInSeconds += delta;
        if (random.nextFloat() < (currentCycleTimeInSeconds/(2*fiftyPercentFlipPeriodInSeconds))) {
            fish.setVelocityX(-fish.getVelocityX());
            currentCycleTimeInSeconds = 0;
        }
        if (currentCycleTimeInSeconds >= fiftyPercentFlipPeriodInSeconds) {
            currentCycleTimeInSeconds = 0;
        }
    }
}
