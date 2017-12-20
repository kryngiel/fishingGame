package com.gmail.kryngielto.games.fishing.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.gmail.kryngielto.games.fishing.behaviour.Behaviour;
import com.gmail.kryngielto.games.fishing.behaviour.FishEvent;
import com.gmail.kryngielto.games.fishing.behaviour.Modifier;
import com.gmail.kryngielto.games.fishing.file.TextureRepository;
import com.gmail.kryngielto.games.fishing.generator.FishParametersGenerator;
import com.gmail.kryngielto.games.fishing.util.FloatPair;
import com.gmail.kryngielto.games.fishing.util.Parameters;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class FishActor extends MovingActor {

    private FishActor () {}

    private LineActor line;

    private Color fishColor;
    private boolean caught = false;
    private float finUsageTime;
    private float finAcceleration;
    private float dragCoefficient;
    private float finFrequency;
    private float finFrequencyDeviation;

    private Behaviour behaviour;

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
        if (caught) {
            setPositionByMouth(line.getX(), line.getY());
        } else {
            behaviour.modify(this, delta);
            moveBy(getVelocityX() * delta, getVelocityY() * delta);
        }
        flipSpeedIfMapEdgeAchieved();
    }

    protected void flipSpeedIfMapEdgeAchieved() {
        if (getX() + getWidth() >= Parameters.GAME_MAP_SIZE.X) {
            setVelocityX(-Math.abs(getVelocityX()));
            behaviour.event(FishEvent.FLIP);
        }
        if (getX() <=0) {
            setVelocityX(Math.abs(getVelocityX()));
            behaviour.event(FishEvent.FLIP);
        }
    }

    public boolean isFacingRight() {
        return getVelocityX() > 0;
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

    public void caught(LineActor line) {
        this.line = line;
        if (!caught) {
            caught = true;
            setOrigin(isFacingRight() ? getWidth() : 0, getHeight()/2);
            setRotation(isFacingRight() ? 90 : -90);
            setVelocityX(getVelocityX() * Parameters.CAUGHT_FISH_SPEED_MODIFIER);
            behaviour.event(FishEvent.CAUGHT);
        }
    }

    public void free() {
        if (caught) {
            caught = false;
            setRotation(0);
            setVelocityX(getVelocityX() / Parameters.CAUGHT_FISH_SPEED_MODIFIER);
            behaviour.event(FishEvent.FREE);
        }
    }

    public void setPositionByMouth(float x, float y) {
        if (isFacingRight()) {
            setX(x - getWidth());
            setY(y - getHeight()/2);
        } else {
            setX(x);
            setY(y - getHeight()/2);
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
        float x = isFacingRight() ? getX() + getWidth() : getX();
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
        private Behaviour behaviour = new Behaviour();
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

        public Builder modifiers(Modifier... modifiers) {
            for (Modifier modifier : modifiers) {
                behaviour.addModifier(modifier);
            }
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
            Texture texture = TextureRepository.getTexture(Parameters.FISH_IMAGE_NAME);
            fish.setTexture(texture);
            fish.setVelocityX(velocityX);
            fish.setVelocityY(velocityY);
            if (fish.isFacingRight()) {
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
            fish.behaviour = behaviour;
            return fish;
        }

    }


}
