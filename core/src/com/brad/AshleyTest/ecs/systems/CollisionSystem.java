package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.brad.AshleyTest.ecs.components.CollisionComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;

/**
 * Created by brad on 3/26/15.
 */
public class CollisionSystem extends IteratingSystem
{
    public CollisionSystem() {
        super(Family.all(CollisionComponent.class, TransformComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {

    }
}
