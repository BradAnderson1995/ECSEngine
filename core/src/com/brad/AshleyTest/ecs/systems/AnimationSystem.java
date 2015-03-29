package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.brad.AshleyTest.ecs.components.AnimationComponent;

/**
 * Created by brad on 3/26/15.
 */
public class AnimationSystem extends IteratingSystem
{
    public AnimationSystem() {
        super(Family.all(AnimationComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // Should set TextureComponent region to next frame when it needs to
    }

    // Some way to set an entities animation
    // Some way to set an animation frame
}
