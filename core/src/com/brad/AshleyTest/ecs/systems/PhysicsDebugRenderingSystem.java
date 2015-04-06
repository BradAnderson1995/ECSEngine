package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;

/**
 * TODO: Add a class header comment!
 */
public class PhysicsDebugRenderingSystem extends EntitySystem
{
    World world;
    Box2DDebugRenderer renderer;
    OrthographicCamera camera;
    FitViewport viewport;
    CameraControlComponent cameraControl;

    public PhysicsDebugRenderingSystem(Entity control, World world, float viewWidth, float viewHeight) {
        this.world = world;
        renderer = new Box2DDebugRenderer();
        camera = new OrthographicCamera();
        viewport = new FitViewport(viewWidth, viewHeight, camera);
        viewport.apply();
        cameraControl = Mappers.cameraControl.get(control);
    }

    @Override
    public void update(float delta) {
        camera.update();
        camera.position.set(cameraControl.camera.position);
        renderer.render(world, camera.combined);
    }
}
