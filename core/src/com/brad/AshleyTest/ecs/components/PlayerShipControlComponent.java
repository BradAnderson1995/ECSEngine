package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/30/15.
 */
public class PlayerShipControlComponent extends Component implements Pool.Poolable
{
    public PlayerShipControlComponent() {
    }

    @Override
    public void reset() {

    }
}
