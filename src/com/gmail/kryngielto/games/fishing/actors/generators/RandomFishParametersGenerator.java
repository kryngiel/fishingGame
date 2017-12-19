package com.gmail.kryngielto.games.fishing.actors.generators;

import com.badlogic.gdx.graphics.Color;
import com.gmail.kryngielto.games.fishing.utils.GaussRandom;
import com.gmail.kryngielto.games.fishing.utils.Parameters;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;

import java.util.Random;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class RandomFishParametersGenerator implements FishParametersGenerator {

    private Random random = new Random();

    @Override
    public FloatPair position() {
        int x = random.nextInt(Parameters.GAME_MAP_SIZE.X - Parameters.DEFAULT_FISH_SIZE.X);
        int y = random.nextInt(Parameters.LAKE_DEPTH - Parameters.DEFAULT_FISH_SIZE.Y);
        return new FloatPair(x, y);
    }

    @Override
    public Color color() {
        float red = random.nextFloat() / 2;
        float green = random.nextFloat() / 2;
        float blue = random.nextFloat() / 2;
        return new Color(red, green, blue, 1);
    }

    @Override
    public FloatPair initialVelocity() {
        double rawGaussianValue = random.nextGaussian();
        double shiftedGaussian = (rawGaussianValue + 3.0) / 3.0;
        if (random.nextBoolean()) {
            shiftedGaussian = -shiftedGaussian;
        }
        return new FloatPair((float) (Parameters.AVERAGE_FISH_VELOCITY.X * shiftedGaussian), 0);
    }

    @Override
    public float acceleration() {
        float result;
        do {
           result = GaussRandom.get().nextGaussian(Parameters.DEFAULT_FISH_ACCELERATION_MEAN, Parameters.DEFAULT_FISH_ACCELERATION_DEVIATION);
        } while (result <= 0);
        return result;
    }

    @Override
    public float dragCoefficient() {
        float result;
        do {
            result = GaussRandom.get().nextGaussian(Parameters.FISH_DRAG_COEFFICIENT_MEAN, Parameters.FISH_DRAG_COEFFICIENT_DEVIATION);
        } while (result <= 0);
        return result;
    }

    @Override
    public float finUsageTime() {
        float result;
        do {
            result = GaussRandom.get().nextGaussian(Parameters.FIN_USAGE_TIME_MEAN, Parameters.FIN_USAGE__TIME_DEVIATION);
        } while (result <= 0);
        return result;
    }

    @Override
    public float finFrequency() {
        float result;
        do {
            result = GaussRandom.get().nextGaussian(Parameters.FIN_FREQUENCY_MEAN, Parameters.FIN_FREQUENCY_DEVIATION);
        } while (result <= 0);
        return result;
    }

    @Override
    public float finFrequencyDeviation() {
        float result;
        do {
            result = GaussRandom.get().nextGaussian(Parameters.FIN_FREQUENCY_DEVIATION_MEAN, Parameters.FIN_FREQUENCY_DEVIATION_DEVIATION);
        } while (result <= 0);
        return result;
    }


    public float scale() {
        return random.nextFloat() * 0.5F + 0.75F;
    }
}
