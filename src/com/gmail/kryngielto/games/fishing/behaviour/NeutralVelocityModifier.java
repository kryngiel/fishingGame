package com.gmail.kryngielto.games.fishing.behaviour;

import com.gmail.kryngielto.games.fishing.actor.FishActor;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class NeutralVelocityModifier implements Modifier {

    @Override
    public void modify(FishActor fish, float delta) {
        // Neutral modifier does nothing
    }
}
