package com.brad.AshleyTest.framework.screen;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Screen;
import com.brad.AshleyTest.AshleyTest;
import com.brad.AshleyTest.ecs.systems.ScreenInputSystem;

/**
 * Created by brad on 3/16/15.
 */
public abstract class AbstractScreen implements Screen
{
    protected AshleyTest game;
    protected PooledEngine engine;
    protected ScreenInputSystem inputSystem;
    protected boolean loadedAssets = false;
    protected boolean retrievedAssets = false;

    public AbstractScreen(AshleyTest game) {
        this.game = game;
        this.engine = game.engine;
        inputSystem = new ScreenInputSystem(game.controls, engine);
        engine.addSystem(inputSystem);
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int x, int y) {
    }

    @Override
    public void show() {
//        game.input.addProcessor(inputSystem.getHandler());
        game.input.addProcessor(inputSystem);
        if (!loadedAssets) {
            LoadingScreen loadingScreen = new LoadingScreen(game, this);
            game.assetSystem.loadAssets(loadingScreen);
            loadedAssets = true;
            game.setScreen(loadingScreen);
        } else {
            game.assetSystem.retrieveAssets();
            retrievedAssets = true;
        }
    }

    @Override
    public void hide() {
        game.input.removeProcessor(inputSystem);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        engine.removeSystem(inputSystem);
    }
}
