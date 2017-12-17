package com.gmail.kryngielto.games.fishing.actors.behaviours;

import com.gmail.kryngielto.games.fishing.actors.FishActor;

/**
 * Created by Marcin on 14-Dec-17.
 */
public abstract class FishVelocityModifier {

    private FishVelocityModifier next;

    public final void modify(FishActor fish, float delta) {
        doModify(fish, delta);
        if (next != null) {
            next.modify(fish, delta);
        }
    }

    protected abstract void doModify(FishActor fish, float delta);

    public final void event(FishEvent event) {
        handleEvent(event);
    }

    protected void handleEvent(FishEvent event) {

    }

    private void setNext(FishVelocityModifier next) {
        this.next = next;
    }

    public static class ChainModifierBuilder {
        private FishVelocityModifier topNode;
        private FishVelocityModifier lastAdded;

        public ChainModifierBuilder add(FishVelocityModifier next) {
            if (topNode == null) {
                topNode = next;
            } else {
                lastAdded.setNext(next);
            }
            lastAdded = next;
            return this;
        }

        public FishVelocityModifier build() {
            return topNode;
        }

    }
}
