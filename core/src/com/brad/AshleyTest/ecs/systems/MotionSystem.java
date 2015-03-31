package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.basesystems.IteratingLogicSystem;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.components.MotionComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;

/**
 * Created by brad on 3/26/15.
 */
public class MotionSystem extends IteratingLogicSystem
{
    Family cameraControlFamily = Family.all(TransformComponent.class, MotionComponent.class, CameraControlComponent.class).get();

    public MotionSystem(int tps) {
        super(Family.all(TransformComponent.class, MotionComponent.class).get(), 1.f / tps);
    }

    @Override
    protected void updateInterval() {
        super.updateInterval();
//        Gdx.app.log("MotionSystem", "Update");
    }

    @Override
    protected void processEntity(Entity entity) {
        TransformComponent transform = Mappers.transform.get(entity);
        MotionComponent motion = Mappers.motion.get(entity);
        transform.pos.x += motion.vel.x;
        transform.pos.y += motion.vel.y;
        motion.vel.x += motion.accel.x;
        motion.vel.y += motion.accel.y;
        if (cameraControlFamily.matches(entity)) {
            CameraControlComponent cameraControl = Mappers.cameraControl.get(entity);
            cameraControl.camera.position.set(transform.pos);
            cameraControl.camera.rotate(transform.rotation);
        }
    }
}
