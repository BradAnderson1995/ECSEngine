package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.brad.AshleyTest.ecs.basesystems.EntityControllerSystem;
import com.brad.AshleyTest.framework.config.ControlSettings;

/**
 * Created by brad on 3/30/15.
 */
public class PlayerShipControlSystem extends EntityControllerSystem
{
    public PlayerShipControlSystem(Family family, ControlSettings controls, int tps) {
        super(family, controls, tps);
    }

    @Override
    protected void processEntity(Entity entity) {

    }
}
