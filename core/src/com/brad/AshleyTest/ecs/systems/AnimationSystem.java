package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.components.AnimationComponent;
import com.brad.AshleyTest.ecs.components.TextureComponent;

/**
 * Created by brad on 3/26/15.
 */
public class AnimationSystem extends IteratingSystem
{
    public AnimationSystem() {
        super(Family.all(AnimationComponent.class, TextureComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // Should set TextureComponent region to next frame when it needs to
        AnimationComponent animation = Mappers.animation.get(entity);
        TextureComponent texture = Mappers.texture.get(entity);
        if (animation.startNewAnimation) {
            animation.stateTime = 0.f;
            animation.animationRunning = true;
            animation.startNewAnimation = false;
//            Array<TextureRegion> textures = new Array<TextureRegion>();
//            for (String region : animation.animations.get(animation.currentAnimation)) {
//                textures.add(texture.textures.get(region));
//            }
//            animation.animation = new Animation(5f / 60, textures, Animation.PlayMode.LOOP_PINGPONG);
            animation.animation = animation.animations.get(animation.currentAnimation);
        } else if (animation.animationRunning) {
            animation.stateTime += deltaTime;
        }
        if (animation.animationRunning) {
            Animation currentAnimation = animation.animation;
            if (currentAnimation.isAnimationFinished(animation.stateTime) && !animation.loop) {
                animation.stopAnimation();
            } else {
                texture.region = currentAnimation.getKeyFrame(animation.stateTime, animation.loop);
            }
        }
    }

    // Some way to set an entities animation
    // Some way to set an animation frame
}
