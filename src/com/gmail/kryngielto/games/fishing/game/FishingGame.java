package com.gmail.kryngielto.games.fishing.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gmail.kryngielto.games.fishing.actors.*;
import com.gmail.kryngielto.games.fishing.utils.Parameters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class FishingGame extends Game {

    private Stage mainStage;
    private BoatActor boat;
    private BasicActor lake;
    private List<FishActor> fishes = new ArrayList<>();
    private LineActor lineActor;

    @Override
    public void create() {
        mainStage = new Stage();
        lake = new BasicActor();
        lake.setTexture(new Texture(Gdx.files.internal(Parameters.LAKE_IMAGE)));
        boat = new BoatActor();

        fishes.addAll(FishBuildDirector.buildTestFishList(20));
        lineActor = new LineActor(boat, fishes);
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
