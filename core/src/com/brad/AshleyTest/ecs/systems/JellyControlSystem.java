package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.brad.AshleyTest.ecs.Constants;
import com.brad.AshleyTest.ecs.basesystems.IteratingLogicSystem;
import com.brad.AshleyTest.ecs.components.JellyComponent;

/**
 * TODO: Add a class header comment!
 */
public class JellyControlSystem extends IteratingLogicSystem

{
    public JellyControlSystem() {
        super(Family.all(JellyComponent.class).get(), 1.f / Constants.TPS);
    }

    @Override
    protected void processEntity(Entity entity) {

    }
}
