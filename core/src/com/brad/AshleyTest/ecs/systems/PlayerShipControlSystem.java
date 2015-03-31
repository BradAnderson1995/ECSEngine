package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.brad.AshleyTest.ecs.EntityFactory;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.basesystems.EntityControllerSystem;
import com.brad.AshleyTest.ecs.components.AnimationComponent;
import com.brad.AshleyTest.ecs.components.MotionComponent;
import com.brad.AshleyTest.ecs.components.TextureComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;
import com.brad.AshleyTest.framework.config.ControlSettings;

/**
 * Created by brad on 3/30/15.
 */
public class PlayerShipControlSystem extends EntityControllerSystem
{
    private EntityFactory factory;

    public PlayerShipControlSystem(Family family, ControlSettings controls, int tps, EntityFactory factory) {
        super(family, controls, tps);
        this.factory = factory;
    }

    @Override
    protected void processEntity(Entity entity) {
        AnimationComponent animation = Mappers.animation.get(entity);
        MotionComponent motion = Mappers.motion.get(entity);
        TransformComponent transform = Mappers.transform.get(entity);
        TextureComponent texture = Mappers.texture.get(entity);

        boolean moving = false;
        for (String input : controlsHeld) {
            if (input.equals("Left")) {
                motion.vel.x = -5;
                moving = true;
            } else if (input.equals("Right")) {
                motion.vel.x = 5;
                moving = true;
            }
        }
        if (!moving) {
            motion.vel.x = 0;
        }
        for (String input : controlsPending) {
            if (input.equals("Shoot")) {
                Entity bullet = factory.createBullet();
                // TODO: Add transform logic to determine size
                Mappers.transform.get(bullet).pos.set(transform.pos);
                Mappers.transform.get(bullet).pos.add(texture.region.getRegionWidth() / 2f - 7.5f, texture.region.getRegionHeight(), 1);
                Mappers.motion.get(bullet).vel.y = 5.f;
                factory.engine.addEntity(bullet);
            }
        }
        if (!animation.animationRunning) {
            if (Math.random() * 100 > 99.6) {
                animation.startAnimation("blink", false);
            }
        }
    }
}
