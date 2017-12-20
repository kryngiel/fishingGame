package com.gmail.kryngielto.games.fishing.actor.behaviour;

import com.gmail.kryngielto.games.fishing.actor.FishActor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 20-Dec-17.
 */
public class Behaviour {

    private List<Modifier> modifiers = new ArrayList<>();

    public void modify(FishActor fish, float deltaTime) {
        for (Modifier modifier: modifiers) {
            modifier.modify(fish, deltaTime);
        }
    }

    public void event(FishEvent event) {
        for (Modifier modifier : modifiers) {
            modifier.event(event);
        }
    }

    public void addModifier(Modifier modifier) {
        modifiers.add(modifier);
    }
}
