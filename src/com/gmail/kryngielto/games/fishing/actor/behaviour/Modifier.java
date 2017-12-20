package com.gmail.kryngielto.games.fishing.actor.behaviour;

import com.gmail.kryngielto.games.fishing.actor.FishActor;

/**
 * Created by Marcin on 14-Dec-17.
 */
public interface Modifier {

    void modify(FishActor fish, float delta);

    default void event(FishEvent event) {}

}
