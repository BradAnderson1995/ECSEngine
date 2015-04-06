package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/26/15.
 */
public class CollisionComponent extends Component implements Pool.Poolable
{
    public Rectangle rect = new Rectangle();
    // TODO: Solid collisions

    @Override
    public void reset() {
        rect = new Rectangle();
    }
}
