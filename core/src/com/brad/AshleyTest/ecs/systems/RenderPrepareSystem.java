package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by brad on 3/29/15.
 */
public class RenderPrepareSystem extends EntitySystem
{
    private SpriteBatch batch;

    public RenderPrepareSystem(SpriteBatch batch) {
        this.batch = batch;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        batch.begin();
    }
}
