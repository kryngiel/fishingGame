package com.gmail.kryngielto.games.fishing.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.gmail.kryngielto.games.fishing.actors.behaviours.FishVelocityModifier;
import com.gmail.kryngielto.games.fishing.actors.behaviours.NeutralVelocityModifier;
import com.gmail.kryngielto.games.fishing.utils.FloatPair;

/**
 * Created by Marcin on 14-Dec-17.
 */
public class FishActor extends MovingActor {

    private FishActor () {}

    private Color fishColor;

    FishVelocityModifier velocityModifier;

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
        super.act(delta);
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

    public static class Builder {

        private float scale = 1;
        private float velocityX;
        private float velocityY;
        private Color color;
        private float x;
        private float y;
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
            fish.velocityModifier = velocityModifier;
            return fish;
        }
    }
}
