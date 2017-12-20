package com.gmail.kryngielto.games.fishing.generator;

import com.badlogic.gdx.graphics.Color;
import com.gmail.kryngielto.games.fishing.util.FloatPair;

/**
 * Created by Marcin on 19-Dec-17.
 */
public interface FishParametersGenerator {
    FloatPair position();

    Color color();

    FloatPair initialVelocity();

    float acceleration();

    float dragCoefficient();

    float finUsageTime();

    float finFrequency();

    float finFrequencyDeviation();
}
