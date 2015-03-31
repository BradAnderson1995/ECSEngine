package com.brad.AshleyTest.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.brad.AshleyTest.ecs.components.AnimationComponent;
import com.brad.AshleyTest.ecs.components.AssetComponent;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.components.MapComponent;
import com.brad.AshleyTest.ecs.components.MotionComponent;
import com.brad.AshleyTest.ecs.components.PlayerControlComponent;
import com.brad.AshleyTest.ecs.components.PlayerShipControlComponent;
import com.brad.AshleyTest.ecs.components.TextureComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;

/**
 * Created by brad on 3/26/15.
 */
public class EntityFactory
{
    public PooledEngine engine;

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
//        transform.pos.set(10, 8, 0);
        camera.camera.position.set(320, 240, 1);
        entity.add(transform);
        entity.add(camera);
        entity.add(motion);
        return entity;
    }

    public Entity createPlayerShip() {
        Entity entity = engine.createEntity();
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        AssetComponent assets = engine.createComponent(AssetComponent.class);
        AnimationComponent animation = engine.createComponent(AnimationComponent.class);
        PlayerShipControlComponent control = engine.createComponent(PlayerShipControlComponent.class);
        MotionComponent motion = engine.createComponent(MotionComponent.class);

        transform.pos.set(255.5f, 35.f, 1);
        Gdx.app.log("Ship", Float.toString(transform.pos.x) + " " + Float.toString(transform.pos.y));
        assets.atlasName = "sprites/packed/game/game.atlas";
        assets.textureName.add("Octopus-01");
        assets.textureName.add("Octopus-02");
        assets.textureName.add("Octopus-03");
        texture.textures.put("Octopus-01", new TextureRegion());
        texture.textures.put("Octopus-02", new TextureRegion());
        texture.textures.put("Octopus-03", new TextureRegion());
        texture.frameString = "Octopus-01";
        Array<TextureRegion> blinkFrames = new Array<TextureRegion>();
        blinkFrames.add(texture.textures.get("Octopus-02"));
        blinkFrames.add(texture.textures.get("Octopus-03"));
        blinkFrames.add(texture.textures.get("Octopus-02"));
        Animation blink = new Animation(.1f, blinkFrames, Animation.PlayMode.NORMAL);
        animation.animations.put("blink", blink);

        entity.add(transform);
        entity.add(control);
        entity.add(animation);
        entity.add(assets);
        entity.add(texture);
        entity.add(motion);

        return entity;
    }

    public Entity createBullet() {
        Entity entity = engine.createEntity();
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        AssetComponent assets = engine.createComponent(AssetComponent.class);
        MotionComponent motion = engine.createComponent(MotionComponent.class);
        assets.atlasName = "sprites/packed/game/game.atlas";
        assets.textureName.add("ink");
        texture.textures.put("ink", new TextureRegion());
        texture.frameString = "ink";

        entity.add(transform);
        entity.add(assets);
        entity.add(texture);
        entity.add(motion);

        return entity;
    }

    public Entity createMario() {
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
        Array<TextureRegion> runTextures = new Array<TextureRegion>();
        runTextures.add(texture.textures.get("mario-run0"));
        runTextures.add(texture.textures.get("mario-run1"));
        runTextures.add(texture.textures.get("mario-run2"));
        Animation runAnimation = new Animation(5f / 60, runTextures, Animation.PlayMode.LOOP_PINGPONG);
        animation.animations.put("mario-run", runAnimation);
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
