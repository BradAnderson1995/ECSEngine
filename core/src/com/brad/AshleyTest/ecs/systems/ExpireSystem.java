package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.Gdx;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.basesystems.IteratingLogicSystem;
import com.brad.AshleyTest.ecs.components.ExpireComponent;

/**
 * TODO: Add a class header comment!
 */
public class ExpireSystem extends IteratingLogicSystem
{
    PooledEngine engine;

    public ExpireSystem(PooledEngine engine, int tps) {
        super(Family.all(ExpireComponent.class).get(), 1.f / tps);
        this.engine = engine;
    }

    @Override
    protected void processEntity(Entity entity) {
        Mappers.expire.get(entity).lifetime--;
        if (Mappers.expire.get(entity).lifetime == 0) {
            engine.removeEntity(entity);
            Gdx.app.log("ExpireSystem", "Removing object");
        }
    }
}
