package com.gmail.kryngielto.games.fishing.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.gmail.kryngielto.games.fishing.actors.behaviours.FishEvent;
import com.gmail.kryngielto.games.fishing.actors.behaviours.FishVelocityModifier;
import com.gmail.kryngielto.games.fishing.actors.behaviours.NeutralVelocityModifier;
import com.gmail.kryngielto.games.fishing.actors.generators.FishParametersGenerator;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;
import com.gmail.kryngielto.games.fishing.utils.Parameters;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class FishActor extends MovingActor {

    private FishActor () {}

    private Color fishColor;
    private boolean caught = false;
    private float finUsageTime;
    private float finAcceleration;
    private float dragCoefficient;
    private float finFrequency;
    private float finFrequencyDeviation;

    private FishVelocityModifier velocityModifier;

    @Override
    public void draw (Batch batch, float parentAlpha) {
        batch.setColor(fishColor.r, fishColor.g, fishColor.b, fishColor.a);
        if (isVisible()) {
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

    @Override
    public void act (float delta) {
        velocityModifier.modify(this, delta);
        moveBy(getVelocityX() * delta, getVelocityY() * delta);
        flipSpeedIfMapEdgeAchieved();
    }

    protected void flipSpeedIfMapEdgeAchieved() {
        if (getX() + getWidth() >= Parameters.GAME_MAP_SIZE.X) {
            setVelocityX(-Math.abs(getVelocityX()));
            velocityModifier.event(FishEvent.FLIP);
        }
        if (getX() <=0) {
            setVelocityX(Math.abs(getVelocityX()));
            velocityModifier.event(FishEvent.FLIP);
        }
    }

    public static class Builder {
        private float scale = 1;
        private float velocityX;
        private float velocityY;
        private Color color;
        private float x;
        private float y;
        private float finUsageTime = Parameters.FIN_USAGE_TIME_MEAN;
        private float dragCoefficient = Parameters.FISH_DRAG_COEFFICIENT_MEAN;
        private float finAcceleration = Parameters.DEFAULT_FISH_ACCELERATION_MEAN;
        private float finFrequency;
        private float finFrequencyDeviation;
        private FishVelocityModifier velocityModifier = new NeutralVelocityModifier();
        public Builder() {

        }

        public Builder velocity(FloatPair velocity) {
            this.velocityX = velocity.X;
            this.velocityY = velocity.Y;
            return this;
        }

        public Builder color(Color color) {
            this.color = color;
            return this;

        }

        public Builder initialPosition(FloatPair position) {
            this.x = position.X;
            this.y = position.Y;
            return this;
        }

        public Builder velocityModifier(FishVelocityModifier fishVelocityModifier) {
            this.velocityModifier = fishVelocityModifier;
            return this;
        }

        public Builder scale(float scale) {
            this.scale = scale;
            return this;
        }

        public Builder dragCoefficient(float dragCoefficient) {
            this.dragCoefficient = dragCoefficient;
            return this;
        }

        public Builder finUsageTime(float finUsageTime) {
            this.finUsageTime = finUsageTime;
            return this;
        }

        public Builder finAcceleration(float finAcceleration) {
            this.finAcceleration = finAcceleration;
            return this;
        }

        public Builder generator(FishParametersGenerator generator) {
            initialPosition(generator.position());
            color(generator.color());
            velocity(generator.initialVelocity());
            finAcceleration(generator.acceleration());
            dragCoefficient(generator.dragCoefficient());
            finUsageTime(generator.finUsageTime());
            finFrequency(generator.finFrequency());
            finFrequencyDeviation(generator.finFrequencyDeviation());
            return this;
        }

        public Builder finFrequency(float finFrequency) {
            this.finFrequency = finFrequency;
            return this;
        }

        public Builder finFrequencyDeviation(float finFrequencyDeviation) {
            this.finFrequencyDeviation = finFrequencyDeviation;
            return this;
        }

        public FishActor build() {
            FishActor fish = new FishActor();
            Texture texture = new Texture((Gdx.files.internal("images/fish.png")));
            fish.setTexture(texture);
            fish.setVelocityX(velocityX);
            fish.setVelocityY(velocityY);
            if (fish.getVelocityX() > 0) {
                fish.getTextureRegion().flip(true, false);
            }
            fish.fishColor = color;
            fish.setX(x);
            fish.setY(y);
            fish.setScale(scale);
            fish.setFinUsageTime(finUsageTime);
            fish.setDragCoefficient(dragCoefficient);
            fish.setFinAcceleration(finAcceleration);
            fish.setFinFrequency(finFrequency);
            fish.setFinFrequencyDeviation(finFrequencyDeviation);
            fish.velocityModifier = velocityModifier;
            return fish;
        }

    }

    @Override
    public void setVelocityX(float velocityX) {
        if (getVelocityX() * velocityX < 0) {
            textureRegion.flip(true, false);
        }
        super.setVelocityX(velocityX);
    }

    @Override
    public void setVelocityY(float velocityY) {
        super.setVelocityY(velocityY);
    }

    public void caught() {
        if (!caught) {
            caught = true;
            setVelocityX(getVelocityX() * Parameters.CAUGHT_FISH_SPEED_MODIFIER);
            velocityModifier.event(FishEvent.CAUGHT);
        }
    }

    public void free() {
        if (caught) {
            caught = false;
            setVelocityX(getVelocityX() / Parameters.CAUGHT_FISH_SPEED_MODIFIER);
            velocityModifier.event(FishEvent.FREE);
        }
    }

    public float getFinUsageTime() {
        return finUsageTime;
    }

    public void setFinUsageTime(float finUsageTime) {
        this.finUsageTime = finUsageTime;
    }

    public float getDragCoefficient() {
        return dragCoefficient;
    }

    public void setDragCoefficient(float dragCoefficient) {
        this.dragCoefficient = dragCoefficient;
    }

    public float getFinAcceleration() {
        return finAcceleration;
    }

    public void setFinAcceleration(float finAcceleration) {
        this.finAcceleration = finAcceleration;
    }

    public FloatPair getMouthPosition() {
        float x = getVelocityX() < 0 ? getX() : getX() + getWidth();
        float y = getY() + getHeight() / 2;
        return new FloatPair(x, y);
    }

    public float getFinFrequency() {
        return finFrequency;
    }

    public void setFinFrequency(float finFrequency) {
        this.finFrequency = finFrequency;
    }

    public float getFinFrequencyDeviation() {
        return finFrequencyDeviation;
    }

    public void setFinFrequencyDeviation(float finFrequencyDeviation) {
        this.finFrequencyDeviation = finFrequencyDeviation;
    }
}
