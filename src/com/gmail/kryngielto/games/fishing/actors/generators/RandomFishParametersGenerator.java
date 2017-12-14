package com.gmail.kryngielto.games.fishing.actors.generators;

import com.badlogic.gdx.graphics.Color;
import com.gmail.kryngielto.games.fishing.utils.Constants;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;

import java.util.Random;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class RandomFishParametersGenerator {

    private Random random = new Random();

    public FloatPair position() {
        int x = random.nextInt(Constants.GAME_MAP_SIZE.X - Constants.DEFAULT_FISH_SIZE.X);
        int y = random.nextInt(Constants.LAKE_DEPTH - Constants.DEFAULT_FISH_SIZE.Y);
        return new FloatPair(x, y);
    }

    public Color color() {
        float red = random.nextFloat();
        float green = random.nextFloat();
        float blue = random.nextFloat();
        return new Color(red, green, blue, 1);
    }

     public FloatPair initialVelocity() {
         double rawGausianValue = random.nextGaussian();
         double shiftedGausian = (rawGausianValue + 3.0)/3.0;
         if (random.nextBoolean()) {
             shiftedGausian = -shiftedGausian;
         }
         return new FloatPair((float)(Constants.AVERAGE_FISH_VELOCITY.X * shiftedGausian), 0);
     }

     public float scale() {
        return random.nextFloat() * 0.5F + 0.75F;
     }
}
