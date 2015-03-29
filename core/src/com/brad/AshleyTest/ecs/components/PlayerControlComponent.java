package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/29/15.
 */
public class PlayerControlComponent extends Component implements Pool.Poolable
{
    public PlayerControlComponent() {
    }

    @Override
    public void reset() {

    }
}
