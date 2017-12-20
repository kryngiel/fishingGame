package com.gmail.kryngielto.games.fishing.actor;

import com.gmail.kryngielto.games.fishing.actor.behaviour.FinSimulator;
import com.gmail.kryngielto.games.fishing.actor.behaviour.RandomHorizontalSpeedFlipper;
import com.gmail.kryngielto.games.fishing.actor.generator.GaussFishParametersGenerator;

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
        return new FishActor.Builder()
                .generator(new GaussFishParametersGenerator())
                .modifiers(
                        new FinSimulator(),
                        new RandomHorizontalSpeedFlipper()
                )
                .build();
    }
}
