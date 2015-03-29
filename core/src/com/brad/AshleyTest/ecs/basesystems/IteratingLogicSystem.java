package com.brad.AshleyTest.ecs.basesystems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;

/**
 * Created by brad on 3/28/15.
 */
public abstract class IteratingLogicSystem extends LogicSystem
{
    private Family family;
    private ImmutableArray<Entity> entities;

    public IteratingLogicSystem(Family family, float interval) {
        this(family, interval, 0);
    }

    public IteratingLogicSystem(Family family, float interval, int priority) {
        super(interval, priority);
        this.family = family;
    }

    @Override
    public void addedToEngine(Engine engine) {
        entities = engine.getEntitiesFor(family);
    }

    public ImmutableArray<Entity> getEntities() {
        return entities;
    }

    protected abstract void processEntity(Entity entity);

    @Override
    protected void updateInterval() {
        for (int i = 0; i < entities.size(); ++i) {
            processEntity(entities.get(i));
        }
    }
}
