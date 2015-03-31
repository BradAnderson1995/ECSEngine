package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * TODO: Add a class header comment!
 */
public class ExpireComponent extends Component implements Pool.Poolable
{
    public int lifetime = 0;

    @Override
    public void reset() {

    }
}
