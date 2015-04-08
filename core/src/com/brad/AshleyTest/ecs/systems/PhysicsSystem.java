package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.physics.box2d.World;
import com.brad.AshleyTest.ecs.Constants;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.basesystems.IteratingLogicSystem;
import com.brad.AshleyTest.ecs.components.DynamicBodyComponent;
import com.brad.AshleyTest.ecs.components.KinematicBodyComponent;
import com.brad.AshleyTest.ecs.components.StaticBodyComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;

/**
 * TODO: Add a class header comment!
 */
public class PhysicsSystem extends IteratingLogicSystem
{
    World world;
    Family dynamicFamily = Family.all(TransformComponent.class, DynamicBodyComponent.class).get();
    Family staticFamily = Family.all(TransformComponent.class, StaticBodyComponent.class).get();
    Family kinematicFamily = Family.all(TransformComponent.class, KinematicBodyComponent.class).get();

    public PhysicsSystem(World world) {
        super(Family.all(TransformComponent.class).one(DynamicBodyComponent.class, StaticBodyComponent.class, KinematicBodyComponent.class).get(), 1.f / Constants.TPS);
        this.world = world;
    }

    @Override
    protected void updateInterval() {
        world.step(1.f / Constants.TPS, 8, 3);
        super.updateInterval();
    }

    @Override
    protected void processEntity(Entity entity) {
        TransformComponent transform = Mappers.transform.get(entity);
//        Gdx.app.log("PhysicsSystem", "Processing");
        if (dynamicFamily.matches(entity)) {
            DynamicBodyComponent body = Mappers.dynamicBody.get(entity);
            transform.pos.set(body.body.getPosition(), transform.pos.z);
            // TODO: Convert angle to degrees
            transform.rotation = body.body.getAngle();
//            Gdx.app.log("PhysicsSystem", "DynamicBody");
            if (body.autoCollisionBox && body.updateCollisions) {
                body.updateCollisions = false;
                body.setCollisionBox(transform.size.x, transform.size.y, body.density, body.friction, body.restitution);
            }
        } else if (staticFamily.matches(entity)) {
            StaticBodyComponent body = Mappers.staticBody.get(entity);
            transform.pos.set(body.body.getPosition(), transform.pos.z);
            transform.rotation = body.body.getAngle();
//            Gdx.app.log("PhysicsSystem", "StaticBody");
            if (body.autoCollisionBox && body.updateCollisions) {
                body.updateCollisions = false;
                body.setCollisionBox(transform.size.x, transform.size.y, body.density, body.friction, body.restitution);
            }
        } else if (kinematicFamily.matches(entity)) {
            KinematicBodyComponent body = Mappers.kinematicBody.get(entity);
            transform.pos.set(body.body.getPosition(), transform.pos.z);
            transform.rotation = body.body.getAngle();
//            Gdx.app.log("PhysicsSystem", Float.toString(transform.pos.x));
//            Gdx.app.log("PhysicsSystem2", Float.toString(body.body.getLinearVelocity().x));
//            Gdx.app.log("PhysicsSystem3", Float.toString(body.body.getPosition().x));
            if (body.autoCollisionBox && body.updateCollisions) {
                body.updateCollisions = false;
                body.setCollisionBox(transform.size.x, transform.size.y, body.density, body.friction, body.restitution);
            }
        }

    }
}
