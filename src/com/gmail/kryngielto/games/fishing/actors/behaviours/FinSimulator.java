package com.gmail.kryngielto.games.fishing.actors.behaviours;

import com.gmail.kryngielto.games.fishing.actors.FishActor;
import com.gmail.kryngielto.games.fishing.utils.GaussRandom;
import com.gmail.kryngielto.games.fishing.utils.Parameters;


/**
 * Created by Marcin on 17-Dec-17.
 */
public class FinSimulator extends FishVelocityModifier {

    private float timeToNextFin;
    private float accelerationTimer;
    private boolean accelerating = false;

    public FinSimulator() {

    }

    @Override
    protected void doModify(FishActor fish, float delta) {
        updateSimulator(fish, delta);
        modifyVelocity(fish, delta);
    }

    private void updateSimulator(FishActor fish, float delta) {
        if (accelerating) {
            accelerationTimer+=delta;
            if (accelerationTimer >= fish.getFinUsageTime()) {
                accelerating = false;
                initNewPeriodFinTimer(fish);
            }
        }
        if (!accelerating) {
            timeToNextFin-=delta;
            if (timeToNextFin <= 0) {
                accelerating = true;
                accelerationTimer = 0;
            }
        }
    }

    private void modifyVelocity(FishActor fish, float delta) {
        float acceleration = getAcceleration(fish);
        float deltaVelocity = acceleration * delta;
        fish.setVelocityX(fish.getVelocityX() + deltaVelocity);
    }

    private float getAcceleration(FishActor fish) {
        float finAcceleration = 0;
        if (accelerating) {
            finAcceleration = fish.getVelocityX() > 0 ? fish.getFinAcceleration() : -fish.getFinAcceleration();
        }
        float resistanceAcceleration = Parameters.WATER_RESISTANCE * fish.getDragCoefficient() * fish.getVelocityX() * fish.getVelocityX();
        resistanceAcceleration = fish.getVelocityX() > 0 ? -resistanceAcceleration : resistanceAcceleration;
        return finAcceleration + resistanceAcceleration;
    }

    private void initNewPeriodFinTimer(FishActor fish) {
        float frequency;
        do {
            frequency = GaussRandom.get().nextGaussian(fish.getFinFrequency(), fish.getFinFrequencyDeviation());
        } while (frequency <= 0);
        float period = 1/frequency;
        timeToNextFin = period - fish.getFinUsageTime();
    }
}
