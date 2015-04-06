package com.brad.AshleyTest;

import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.brad.AshleyTest.ecs.components.AssetComponent;
import com.brad.AshleyTest.ecs.systems.AssetSystem;
import com.brad.AshleyTest.framework.config.ControlSettings;

import aurelienribon.tweenengine.TweenManager;

public class AshleyTest extends Game
{
    public static final String TITLE = "Space Invaders";
    public static final int WIDTH = 640, HEIGHT = 480;
    public ControlSettings controls;
    public AssetManager manager;
    public InputMultiplexer input;
    public SpriteBatch batch;
    public PooledEngine engine;
    public AssetSystem assetSystem;
    public TweenManager tweenManager;

    @Override
    public void create() {
        Box2D.init();
        engine = new PooledEngine();
        controls = new ControlSettings();
        manager = new AssetManager();
        input = new InputMultiplexer();
        batch = new SpriteBatch();
        assetSystem = new AssetSystem(engine, manager, 150.f);
        tweenManager = new TweenManager();
        engine.addSystem(assetSystem);
        engine.addEntityListener(Family.all(AssetComponent.class).get(), assetSystem);
        Gdx.input.setInputProcessor(input);
        setScreen(new SpaceInvadersScreen(this, 60, 60, WIDTH, HEIGHT));
    }

    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        batch.dispose();
        assetSystem.dispose();
        manager.dispose();
    }
}
