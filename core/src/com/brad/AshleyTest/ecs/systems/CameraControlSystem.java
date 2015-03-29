package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.components.MotionComponent;

/**
 * Created by brad on 3/28/15.
 */
public class CameraControlSystem extends IteratingSystem
{
    public CameraControlSystem() {
        super(Family.all(CameraControlComponent.class, MotionComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
//        Mappers.motion.get(entity).vel.y = .01f;
    }
}
