package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/25/15.
 */
public class MotionComponent extends Component implements Pool.Poolable
{
    public final Vector2 vel = new Vector2();
    public final Vector2 accel = new Vector2();

    public MotionComponent() {
        vel.set(0.f, 0.f);
        accel.set(0.f, 0.f);
    }

    @Override
    public void reset() {
        vel.set(0.f, 0.f);
        accel.set(0.f, 0.f);
    }
}
