package com.brad.AshleyTest;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.FPSLogger;
import com.brad.AshleyTest.ecs.EntityFactory;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.components.PlayerControlComponent;
import com.brad.AshleyTest.ecs.systems.AnimationSystem;
import com.brad.AshleyTest.ecs.systems.CameraControlSystem;
import com.brad.AshleyTest.ecs.systems.CameraInputControllerSystem;
import com.brad.AshleyTest.ecs.systems.CollisionSystem;
import com.brad.AshleyTest.ecs.systems.MapRenderingSystem;
import com.brad.AshleyTest.ecs.systems.MotionSystem;
import com.brad.AshleyTest.ecs.systems.RenderPrepareSystem;
import com.brad.AshleyTest.ecs.systems.RenderingSystem;
import com.brad.AshleyTest.framework.screen.AbstractScreen;

/**
 * Created by brad on 3/30/15.
 */
public class SpaceInvadersScreen extends AbstractScreen
{
    private EntityFactory factory;
    private AnimationSystem animationSystem;
    private RenderPrepareSystem renderPrepareSystem;
    private RenderingSystem renderingSystem;
    private MotionSystem motionSystem;
    private CollisionSystem collisionSystem;
    private CameraControlSystem cameraControlSystem;
    private MapRenderingSystem mapRenderingSystem;
    private CameraInputControllerSystem cameraInputControllerSystem;
    private Entity mapEntity;
    private Entity cameraControlEntity;
    private boolean addedMap = false;
    private FPSLogger logger;

    public SpaceInvadersScreen(AshleyTest game, int tps, int maxFPS, int xWidth, int yHeight) {
        super(game);
        factory = new EntityFactory(engine);
        animationSystem = new AnimationSystem();
        cameraControlEntity = factory.createCameraControl();
        Entity playerShip = factory.createPlayerShip(game.manager);
        engine.addEntity(cameraControlEntity);
        engine.addEntity(playerShip);
        renderPrepareSystem = new RenderPrepareSystem(game.batch);
        renderingSystem = new RenderingSystem(game.batch, cameraControlEntity, xWidth, yHeight);
        mapEntity = factory.createMap("maps/mario.tmx");
        game.assetSystem.addMap("maps/mario.tmx");
        mapRenderingSystem = new MapRenderingSystem(game.batch, cameraControlEntity, 20, 15, 16);
        motionSystem = new MotionSystem(tps);
        collisionSystem = new CollisionSystem();
        cameraControlSystem = new CameraControlSystem();
        cameraInputControllerSystem = new CameraInputControllerSystem(Family.all(PlayerControlComponent.class).get(), game.controls, tps);
        logger = new FPSLogger();

        engine.addSystem(animationSystem);
        engine.addSystem(renderPrepareSystem);
        engine.addSystem(mapRenderingSystem);
        engine.addSystem(renderingSystem);
        engine.addSystem(motionSystem);
        engine.addSystem(collisionSystem);
        engine.addSystem(cameraControlSystem);
        engine.addSystem(cameraInputControllerSystem);
        game.input.addProcessor(cameraInputControllerSystem);

        game.assetSystem.addAtlas("sprites/packed/game/game.atlas");
        game.assetSystem.addAtlas("sprites/packed/env/env.atlas");
    }

    @Override
    public void render(float delta) {
        logger.log();
        if (retrievedAssets) {
            if (!addedMap) {
                engine.addEntity(mapEntity);
                addedMap = true;
                mapRenderingSystem.setupRenderer(Mappers.map.get(mapEntity).map);
            }
            engine.update(delta);
        }
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        super.dispose();
        engine.removeAllEntities();
        engine.removeSystem(mapRenderingSystem);
        engine.removeSystem(renderPrepareSystem);
        engine.removeSystem(renderingSystem);
        engine.removeSystem(motionSystem);
        engine.removeSystem(collisionSystem);
        engine.removeSystem(animationSystem);
        engine.removeSystem(cameraControlSystem);
        engine.removeSystem(cameraInputControllerSystem);
        game.input.removeProcessor(cameraInputControllerSystem);
    }
}
