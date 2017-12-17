package com.gmail.kryngielto.games.fishing.actors.behaviours;

import com.gmail.kryngielto.games.fishing.actors.FishActor;
import com.gmail.kryngielto.games.fishing.utils.GaussRandom;
import com.gmail.kryngielto.games.fishing.utils.Parameters;


/**
 * Created by Marcin on 17-Dec-17.
 */
public class FinSimulator extends FishVelocityModifier {

    private float accelerationPeriod;
    private float waterResistanceFactor;
    private float timeToNextFin;
    private float accelerationTimer;
    private boolean accelerating = false;

    public FinSimulator() {
        this(Parameters.DEFAULT_FISH_ACCELERATION_TIME, Parameters.DEFAULT_WATER_RESISTANCE_FACTOR);
    }

    public FinSimulator(float accelerationPeriod, float waterResistanceFactor) {
        this.accelerationPeriod = accelerationPeriod;
        this.waterResistanceFactor = waterResistanceFactor;
    }

    @Override
    protected void doModify(FishActor fish, float delta) {
        if (accelerating) {
            accelerationTimer+=delta;
            if (accelerationTimer >= accelerationPeriod) {
                accelerating = false;
                initNewPeriodFinTimer();
            }
        }
        if (!accelerating) {
            timeToNextFin-=delta;
            if (timeToNextFin <= 0) {
                accelerating = true;
                accelerationTimer = 0;
            }
        }
        modifyVelocity(fish, delta);
    }

    private void modifyVelocity(FishActor fish, float delta) {
        float finAcceleration = 0;
        if (accelerating) {
            finAcceleration = fish.getVelocityX() > 0 ? Parameters.DEFAULT_FISH_ACCELERATION : -Parameters.DEFAULT_FISH_ACCELERATION;
        }
        float resistanceAcceleration = waterResistanceFactor * fish.getVelocityX() * fish.getVelocityX();
        resistanceAcceleration = fish.getVelocityX() > 0 ? -resistanceAcceleration : resistanceAcceleration;
        float deltaVelocity = (finAcceleration + resistanceAcceleration) * delta;
        fish.setVelocityX(fish.getVelocityX() + deltaVelocity);
    }

    private void initNewPeriodFinTimer() {
        do {
            timeToNextFin = GaussRandom.get().nextGaussian(Parameters.FIN_PERIOD_MEAN, Parameters.FIN_PERIOD_DEVIATION);
        } while (timeToNextFin <= 0);
    }
}
