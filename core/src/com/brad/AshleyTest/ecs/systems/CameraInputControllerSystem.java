package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.basesystems.EntityControllerSystem;
import com.brad.AshleyTest.ecs.components.TransformComponent;
import com.brad.AshleyTest.framework.config.ControlSettings;

/**
 * Created by brad on 3/28/15.
 */
public class CameraInputControllerSystem extends EntityControllerSystem
{
    public CameraInputControllerSystem(Family family, ControlSettings controls, int tps) {
        super(family, controls, tps);
    }

    @Override
    protected void processEntity(Entity entity) {
        TransformComponent transform = Mappers.transform.get(entity);
        for (String input : controlsHeld) {
            if (input.equals("Up")) {
                transform.pos.y += .1;
            } else if (input.equals("Down")) {
                transform.pos.y -= .1;
            } else if (input.equals("Left")) {
                transform.pos.x -= .1;
            } else if (input.equals("Right")) {
                transform.pos.x += .1;
            }
        }
    }
}
