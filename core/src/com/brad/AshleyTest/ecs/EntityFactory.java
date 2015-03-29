package com.brad.AshleyTest.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.brad.AshleyTest.ecs.components.AssetComponent;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.components.MapComponent;
import com.brad.AshleyTest.ecs.components.MotionComponent;
import com.brad.AshleyTest.ecs.components.TextureComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;

/**
 * Created by brad on 3/26/15.
 */
public class EntityFactory
{
    private PooledEngine engine;

    public EntityFactory(PooledEngine engine) {
        this.engine = engine;
    }

    public Entity createMap(String mapName) {
        Entity entity = engine.createEntity();
        AssetComponent assets = engine.createComponent(AssetComponent.class);
        assets.mapName = mapName;
        MapComponent map = engine.createComponent(MapComponent.class);
        map.mapName = mapName;
        entity.add(assets);
        entity.add(map);
        return entity;
    }

    public Entity createCameraControl() {
        Entity entity = engine.createEntity();
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        CameraControlComponent camera = engine.createComponent(CameraControlComponent.class);
        MotionComponent motion = engine.createComponent(MotionComponent.class);
        transform.pos.set(5, 4, 0);
        camera.camera = new OrthographicCamera();
        entity.add(transform);
        entity.add(camera);
        entity.add(motion);
        return entity;
    }

    public Entity createSnake(AssetManager manager) {
        Entity entity = engine.createEntity();
        entity.add(engine.createComponent(TransformComponent.class));
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        AssetComponent assets = engine.createComponent(AssetComponent.class);
        assets.atlasName = "sprites/packed/game/game.atlas";
        assets.textureName.add("SnakeNode");
        entity.add(assets);
        entity.add(texture);
        return entity;
    }
}
