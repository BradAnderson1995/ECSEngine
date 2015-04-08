package com.brad.AshleyTest.ecs;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.brad.AshleyTest.ecs.components.AnimationComponent;
import com.brad.AshleyTest.ecs.components.AssetComponent;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.components.DynamicBodyComponent;
import com.brad.AshleyTest.ecs.components.ExpireComponent;
import com.brad.AshleyTest.ecs.components.KinematicBodyComponent;
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
    public World world;

    public EntityFactory(PooledEngine engine, World world) {
        this.engine = engine;
        this.world = world;
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
        camera.camera.position.set(2, 1.5f, 1);
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
//        MotionComponent motion = engine.createComponent(MotionComponent.class);
//        CollisionComponent collision = engine.createComponent(CollisionComponent.class);
        KinematicBodyComponent body = engine.createComponent(KinematicBodyComponent.class);

//        transform.pos.set(1.75f, .3f, 1);
        body.bodyDef.position.set(1.75f, .3f);
        body.makeBody(world);
        transform.pos.z = 1;
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
        entity.add(body);
//        entity.add(motion);
//        entity.add(collision);

        return entity;
    }

    public Entity createBullet() {
        Entity entity = engine.createEntity();
        TransformComponent transform = engine.createComponent(TransformComponent.class);
        TextureComponent texture = engine.createComponent(TextureComponent.class);
        AssetComponent assets = engine.createComponent(AssetComponent.class);
        DynamicBodyComponent body = engine.createComponent(DynamicBodyComponent.class);
//        MotionComponent motion = engine.createComponent(MotionComponent.class);
        ExpireComponent expire = engine.createComponent(ExpireComponent.class);
//        CollisionComponent collision = engine.createComponent(CollisionComponent.class);

        body.makeBody(world);
        assets.atlasName = "sprites/packed/game/game.atlas";
        assets.textureName.add("ink");
        texture.textures.put("ink", new TextureRegion());
        texture.frameString = "ink";
        transform.rotation = 180;
        expire.lifetime = 100;
//        motion.accel.y = -10f/60;

        entity.add(transform);
        entity.add(assets);
        entity.add(texture);
//        entity.add(motion);
        entity.add(expire);
//        entity.add(collision);
        entity.add(body);

        return entity;
    }

    public Entity createJelly() {
        Entity entity = engine.createEntity();

        TransformComponent transform = engine.createComponent(TransformComponent.class);
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
        transform.pos.set(3.f, 1.f, 1);
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
