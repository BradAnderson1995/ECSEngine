package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.brad.AshleyTest.ecs.Constants;
import com.brad.AshleyTest.ecs.basesystems.IteratingLogicSystem;

/**
 * TODO: Add a class header comment!
 */
public class TimelineSystem extends IteratingLogicSystem
{
    public TimelineSystem(Family family) {
        super(family, 1.f / Constants.TPS);
    }

    @Override
    protected void processEntity(Entity entity) {

    }
}
