package com.gmail.kryngielto.games.fishing.actors;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class BasicActor extends Actor {

    protected TextureRegion textureRegion;
    private float velocityX = 0;
    private float velocityY = 0;

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public float getVelocityX() {
        return velocityX;
    }

    public float getVelocityY() {
        return velocityY;
    }

    public void setVelocityX(float velocityX) {
        this.velocityX = velocityX;
    }

    public void setVelocityY(float velocityY) {
        this.velocityY = velocityY;
    }

    public BasicActor() {
        textureRegion = new TextureRegion();
    }

    public void setTexture(Texture texture) {
        setWidth(texture.getWidth());
        setHeight(texture.getHeight());
        textureRegion.setRegion(texture);
    }

    @Override
    public void act (float delta) {
        super.act(delta);
    }

    @Override
    public void draw (Batch batch, float parentAlpha) {
        Color c = getColor();
        batch.setColor(c.r, c.g, c.b, c.a);
        if (isVisible()) {
            batch.draw(textureRegion, getX(), getY(), getOriginX(), getOriginY(),
                    getWidth(), getHeight(), getScaleX(), getScaleY(), getRotation());
        }
    }

}
