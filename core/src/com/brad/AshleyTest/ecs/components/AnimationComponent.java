package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/25/15.
 */
public class AnimationComponent extends Component implements Pool.Poolable
{
    //    public ArrayMap<String, Animation> animations = new ArrayMap<String, Animation>();
    public ArrayMap<String, Animation> animations = new ArrayMap<String, Animation>();
    public Animation animation = null;
    public String currentAnimation;
    public boolean startNewAnimation = false;
    public boolean animationRunning = false;
    public boolean loop = false;
    public float stateTime = 0.f;

    public AnimationComponent() {
//        animations = new ArrayMap<String, Animation>();
        currentAnimation = "";
    }

    public void startAnimation(String animation, boolean loop) {
        currentAnimation = animation;
        startNewAnimation = true;
        this.loop = loop;
    }

    public void stopAnimation() {
        animationRunning = false;
    }

    @Override
    public void reset() {
        animations.clear();
        animation = null;
        currentAnimation = "";
        startNewAnimation = false;
        animationRunning = false;
        stateTime = 0.f;
        loop = false;
    }
}
