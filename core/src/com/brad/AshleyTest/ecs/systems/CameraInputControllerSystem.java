package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.brad.AshleyTest.ecs.basesystems.EntityControllerSystem;
import com.brad.AshleyTest.framework.config.ControlSettings;

/**
 * Created by brad on 3/28/15.
 */
public class CameraInputControllerSystem extends EntityControllerSystem
{
    public CameraInputControllerSystem(Family family, ControlSettings controls) {
        super(family, controls);
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        for (String input : inputsPending) {

        }
    }
}
