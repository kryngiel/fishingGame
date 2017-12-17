package com.gmail.kryngielto.games.fishing.actors.behaviours;

import com.gmail.kryngielto.games.fishing.actors.FishActor;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class NeutralVelocityModifier extends FishVelocityModifier {

    @Override
    protected void doModify(FishActor fish, float delta) {
        // Neutral modifier does nothing
    }
}
