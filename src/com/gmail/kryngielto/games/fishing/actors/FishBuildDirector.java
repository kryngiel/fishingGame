package com.gmail.kryngielto.games.fishing.actors;

import com.gmail.kryngielto.games.fishing.actors.behaviours.RandomHorizontalSpeedFlipper;
import com.gmail.kryngielto.games.fishing.actors.generators.RandomFishParametersGenerator;
import com.gmail.kryngielto.games.fishing.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class FishBuildDirector {

    public static List<FishActor> buildTestFishList(int number) {
        List<FishActor> list = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            list.add(buildTestFish());
        }
        return list;
    }

    public static FishActor buildTestFish() {
        RandomFishParametersGenerator generator = new RandomFishParametersGenerator();
        return new FishActor.Builder()
                .color(generator.color())
                .initialPosition(generator.position())
                .velocity(generator.initialVelocity())
                .velocityModifier(new RandomHorizontalSpeedFlipper(Constants.FISH_VELOCITY_FLIP_TIME_MEAN, Constants.FISH_VELOCITY_FLIP_TIME_DEVIATION))
                .build();
    }
}
