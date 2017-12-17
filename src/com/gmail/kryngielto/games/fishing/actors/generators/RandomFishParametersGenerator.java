package com.gmail.kryngielto.games.fishing.actors.generators;

import com.badlogic.gdx.graphics.Color;
import com.gmail.kryngielto.games.fishing.utils.Parameters;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;

import java.util.Random;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class RandomFishParametersGenerator {

    private Random random = new Random();

    public FloatPair position() {
        int x = random.nextInt(Parameters.GAME_MAP_SIZE.X - Parameters.DEFAULT_FISH_SIZE.X);
        int y = random.nextInt(Parameters.LAKE_DEPTH - Parameters.DEFAULT_FISH_SIZE.Y);
        return new FloatPair(x, y);
    }

    public Color color() {
        float red = random.nextFloat()/2;
        float green = random.nextFloat()/2;
        float blue = random.nextFloat()/2;
        return new Color(red, green, blue, 1);
    }

     public FloatPair initialVelocity() {
         double rawGausianValue = random.nextGaussian();
         double shiftedGausian = (rawGausianValue + 3.0)/3.0;
         if (random.nextBoolean()) {
             shiftedGausian = -shiftedGausian;
         }
         return new FloatPair((float)(Parameters.AVERAGE_FISH_VELOCITY.X * shiftedGausian), 0);
     }

     public float scale() {
        return random.nextFloat() * 0.5F + 0.75F;
     }
}
