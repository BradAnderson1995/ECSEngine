package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.basesystems.EntityControllerSystem;
import com.brad.AshleyTest.ecs.components.AnimationComponent;
import com.brad.AshleyTest.ecs.components.MotionComponent;
import com.brad.AshleyTest.ecs.components.TextureComponent;
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
        AnimationComponent animation = Mappers.animation.get(entity);
        TextureComponent texture = Mappers.texture.get(entity);
        MotionComponent motion = Mappers.motion.get(entity);
        boolean animating = false;
        for (String input : controlsHeld) {
            if (input.equals("Up")) {
//                transform.pos.y += .1;
            } else if (input.equals("Down")) {
//                transform.pos.y -= .1;
            } else if (input.equals("Left")) {
                motion.vel.x = -10;
                animating = true;
            } else if (input.equals("Right")) {
                motion.vel.x = 1000;
                animating = true;
            }
        }
        if (!animating) {
            animation.stopAnimation();
            texture.frameString = "mario0";
            motion.vel.x = 0;
        }
        for (String input : controlsPending) {
            if (input.equals("Left")) {
                animation.startAnimation("mario-run", true);
            } else if (input.equals("Right")) {
                animation.startAnimation("mario-run", true);
            }
        }
    }
}
