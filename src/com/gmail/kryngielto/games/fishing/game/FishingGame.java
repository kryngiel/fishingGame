package com.gmail.kryngielto.games.fishing.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gmail.kryngielto.games.fishing.actors.*;

import java.util.Collection;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class FishingGame extends Game {

    private Stage mainStage;
    private BoatActor boat;
    private BasicActor lake;
    private Collection<FishActor> fishes;
    private LineActor lineActor;

    @Override
    public void create() {
        mainStage = new Stage();
        lake = new BasicActor();
        lake.setTexture(new Texture(Gdx.files.internal("images/lake.png")));
        boat = new BoatActor();
        boat.setTexture(new Texture(Gdx.files.internal("images/boat.png")));
        fishes = FishBuildDirector.buildTestFishList(20);
        lineActor = new LineActor(boat);
        mainStage.addActor(lake);
        mainStage.addActor(boat);
        for(FishActor fish : fishes) {
            mainStage.addActor(fish);
        }
        mainStage.addActor(lineActor);
    }

    @Override
    public void render () {
        mainStage.act();
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();

    }
}
