package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/25/15.
 */
public class AnimationComponent extends Component implements Pool.Poolable
{
    public IntMap<Animation> animations = new IntMap<Animation>();
    public int frame;

    @Override
    public void reset() {
        animations.clear();
    }
}
