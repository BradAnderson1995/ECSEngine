package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.SortedIteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;
import com.brad.AshleyTest.ecs.components.TextureComponent;
import com.brad.AshleyTest.ecs.components.TransformComponent;

import java.util.Comparator;

/**
 * Created by brad on 3/25/15.
 */
public class RenderingSystem extends SortedIteratingSystem
{
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private FitViewport viewport;
    private CameraControlComponent cameraControl;

    public RenderingSystem(SpriteBatch batch, Entity control, int viewWidth, int viewHeight) {
        super(Family.all(TextureComponent.class, TransformComponent.class).get(), new Comparator<Entity>()
        {
            @Override
            public int compare(Entity e1, Entity e2) {
                return (int) Math.signum(Mappers.transform.get(e1).pos.z - Mappers.transform.get(e2).pos.z);
            }
        });
        this.batch = batch;
        this.cameraControl = Mappers.cameraControl.get(control);
        camera = new OrthographicCamera();
        viewport = new FitViewport(viewWidth, viewHeight, camera);
        viewport.apply(true);
    }

    @Override
    public void update(float delta) {
        // Clear the screen (necessary for performance)
        camera.update();
        camera.position.set(cameraControl.camera.position);
//        Gdx.gl.glClearColor(0, 0, 0, 1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw the enitities
        batch.setProjectionMatrix(camera.combined);
        batch.begin();

        // Runs processEntity for each entity in family sorted by Z coordinate
        super.update(delta);
        batch.end();
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        TransformComponent transform = Mappers.transform.get(entity);

        // Set blending
        if (Mappers.noBlend.has(entity)) {
            batch.disableBlending();
        } else {
            batch.enableBlending();
        }

        // Draw the entity
        batch.draw(Mappers.texture.get(entity).region, transform.pos.x, transform.pos.y,
                transform.origin.x, transform.origin.y, transform.size.x, transform.size.y,
                transform.scale.x, transform.scale.y, transform.rotation, transform.clockwise);
    }
}


