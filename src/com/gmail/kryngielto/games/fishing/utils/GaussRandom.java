package com.gmail.kryngielto.games.fishing.utils;

import java.util.Random;

/**
 * Created by Marcin on 16-Dec-17.
 */
public class GaussRandom extends Random {
    public float nextGaussian(float mean, float deviation) {
        return (float)(nextGaussian() * deviation + mean);
    }
}
