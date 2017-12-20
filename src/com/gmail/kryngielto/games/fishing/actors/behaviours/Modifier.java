package com.gmail.kryngielto.games.fishing.actors.behaviours;

import com.gmail.kryngielto.games.fishing.actors.FishActor;

/**
 * Created by Marcin on 14-Dec-17.
 */
public interface Modifier {

    void modify(FishActor fish, float delta);

    default void event(FishEvent event) {}

}
