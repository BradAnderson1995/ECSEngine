package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by brad on 3/29/15.
 */
public class RenderCloseSystem extends EntitySystem
{
    private SpriteBatch batch;

    public RenderCloseSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        batch.end();
    }
}
