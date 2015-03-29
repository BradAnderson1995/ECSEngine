package com.brad.AshleyTest.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.graphics.FPSLogger;
import com.brad.AshleyTest.AshleyTest;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.systems.AnimationSystem;
import com.brad.AshleyTest.ecs.systems.CameraControlSystem;
import com.brad.AshleyTest.ecs.systems.CameraInputControllerSystem;
import com.brad.AshleyTest.ecs.systems.CollisionSystem;
import com.brad.AshleyTest.ecs.systems.MapRenderingSystem;
import com.brad.AshleyTest.ecs.systems.MotionSystem;
import com.brad.AshleyTest.ecs.systems.RenderingSystem;
import com.brad.AshleyTest.framework.screen.AbstractScreen;

/**
 * Created by brad on 3/26/15.
 */
public class EntityScreen extends AbstractScreen
{
    private EntityFactory factory;
    private AnimationSystem animationSystem;
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

//    private float dt;
//    private int maxFps;

    public EntityScreen(AshleyTest game, int tps, int maxFps, int xWidth, int yHeight) {
        super(game);
        factory = new EntityFactory(engine);
        animationSystem = new AnimationSystem();
        cameraControlEntity = factory.createCameraControl();
        engine.addEntity(cameraControlEntity);
        renderingSystem = new RenderingSystem(game.batch, cameraControlEntity, xWidth, yHeight);
        mapEntity = factory.createMap("maps/map.tmx");
        game.assetSystem.addMap("maps/map.tmx");
        mapRenderingSystem = new MapRenderingSystem(game.batch, cameraControlEntity, 10, 8, 16);
        motionSystem = new MotionSystem(tps);
        collisionSystem = new CollisionSystem();
        cameraControlSystem = new CameraControlSystem();
        cameraInputControllerSystem = new CameraInputControllerSystem(Family.all(CameraControlComponent.class).get(), game.controls);
        logger = new FPSLogger();

        engine.addSystem(animationSystem);
        engine.addSystem(mapRenderingSystem);
        engine.addSystem(renderingSystem);
        engine.addSystem(motionSystem);
        engine.addSystem(collisionSystem);
        engine.addSystem(cameraControlSystem);
        engine.addSystem(cameraInputControllerSystem);
        game.input.addProcessor(cameraInputControllerSystem);
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
        engine.removeSystem(renderingSystem);
        engine.removeSystem(motionSystem);
        engine.removeSystem(collisionSystem);
        engine.removeSystem(animationSystem);
        engine.removeSystem(cameraControlSystem);
        engine.removeSystem(cameraInputControllerSystem);
        game.input.removeProcessor(cameraInputControllerSystem);
    }
}
