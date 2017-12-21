package com.gmail.kryngielto.games.fishing.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.gmail.kryngielto.games.fishing.actor.*;
import com.gmail.kryngielto.games.fishing.util.Parameters;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marcin on 13-Dec-17.
 */
public class FishingGame extends Game {

    private Stage mainStage;
    private Stage uiStage;
    private Label numberOfCaughtFishesLabel;
    private BoatActor boat;
    private BasicActor lake;
    private List<FishActor> fishes = new ArrayList<>();
    private LineActor lineActor;

    @Override
    public void create() {
        initMainStage();
        uiStage = new Stage();
        BitmapFont font = new BitmapFont();
        Label.LabelStyle style = new Label.LabelStyle(font, Color.BLACK);
        numberOfCaughtFishesLabel = new Label("0", style);
        numberOfCaughtFishesLabel.setPosition(Parameters.GAME_MAP_SIZE.X - 25, Parameters.GAME_MAP_SIZE.Y - 25);
        uiStage.addActor(numberOfCaughtFishesLabel);

    }

    private void initMainStage() {
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
        numberOfCaughtFishesLabel.setText("" + lineActor.getNumberOfCaughtFishes());
        uiStage.act();
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClearColor(0.8f, 0.8f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        mainStage.draw();
        uiStage.draw();
    }
}
