package com.brad.AshleyTest.ecs.basesystems;

import com.badlogic.ashley.core.EntitySystem;

/**
 * Created by brad on 3/28/15.
 */
public abstract class LogicSystem extends EntitySystem
{
    protected float interval;
    protected float accumulator;

    public LogicSystem(float interval) {
        this(interval, 0);
    }

    public LogicSystem(float interval, int priority) {
        super(priority);
        this.interval = interval;
        this.accumulator = 0;
    }

    @Override
    public void update(float deltaTime) {
        accumulator += deltaTime;

        while (accumulator >= interval) {
            accumulator -= interval;
            updateInterval();
        }
    }

    protected abstract void updateInterval();
}
