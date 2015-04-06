package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.physics.box2d.World;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.basesystems.IteratingLogicSystem;
import com.brad.AshleyTest.ecs.components.CollisionComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;

/**
 * Created by brad on 3/26/15.
 */
public class CollisionSystem extends IteratingLogicSystem
{
    World world;

    public CollisionSystem(int tps, World world) {
        super(Family.all(CollisionComponent.class, TransformComponent.class).get(), 1.f / tps);
        this.world = world;
    }

    public boolean colliding(Entity entity1, Entity entity2) {
        CollisionComponent collision1 = Mappers.collision.get(entity1);
        CollisionComponent collision2 = Mappers.collision.get(entity2);
        if (collision1.rect.overlaps(collision2.rect)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void processEntity(Entity entity) {
        TransformComponent transform = Mappers.transform.get(entity);
        CollisionComponent collision = Mappers.collision.get(entity);
        collision.rect.set(transform.pos.x, transform.pos.y, transform.size.x, transform.size.y);
    }
}
