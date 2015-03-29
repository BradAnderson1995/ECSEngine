package com.brad.AshleyTest.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.brad.AshleyTest.ecs.components.AnimationComponent;
import com.brad.AshleyTest.ecs.components.AssetComponent;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.components.MapComponent;
import com.brad.AshleyTest.ecs.components.MotionComponent;
import com.brad.AshleyTest.ecs.components.PlayerControlComponent;
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
        transform.pos.set(10, 8, 0);
        camera.camera = new OrthographicCamera();
        entity.add(transform);
        entity.add(camera);
        entity.add(motion);
        return entity;
    }

    public Entity createMario(AssetManager manager) {
        Entity entity = engine.createEntity();
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        AssetComponent assets = engine.createComponent(AssetComponent.class);
        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        MotionComponent motion = engine.createComponent(MotionComponent.class);
        PlayerControlComponent control = engine.createComponent(PlayerControlComponent.class);
        transform.pos.set(16.f, 32.f, 1);
        assets.atlasName = "sprites/packed/game/game.atlas";
        assets.textureName.add("mario0");
        assets.textureName.add("mario-run0");
        assets.textureName.add("mario-run1");
        assets.textureName.add("mario-run2");
        texture.textures.put("mario0", new TextureRegion());
        texture.textures.put("mario-run0", new TextureRegion());
        texture.textures.put("mario-run1", new TextureRegion());
        texture.textures.put("mario-run2", new TextureRegion());
        Array<String> runTextures = new Array<String>();
        runTextures.add("mario-run0");
        runTextures.add("mario-run1");
        runTextures.add("mario-run2");
//        Animation runAnimation = new Animation(5, runTextures, Animation.PlayMode.LOOP_PINGPONG);
        animation.animations.put("mario-run", runTextures);
        texture.frameString = "mario0";
        entity.add(transform);
        entity.add(control);
        entity.add(animation);
        entity.add(assets);
        entity.add(texture);
        entity.add(motion);
        return entity;
    }
}
