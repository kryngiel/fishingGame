package com.gmail.kryngielto.games.fishing.util;

import java.util.Random;

/**
 * Created by Marcin on 16-Dec-17.
 */
public class GaussRandom extends Random {

    private static GaussRandom instance = new GaussRandom();

    private GaussRandom() {

    }

    public static GaussRandom get() {
        return instance;
    }

    public float nextGaussian(float mean, float deviation) {
        return (float)(nextGaussian() * deviation + mean);
    }
}
