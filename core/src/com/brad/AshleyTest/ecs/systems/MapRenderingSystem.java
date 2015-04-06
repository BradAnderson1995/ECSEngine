package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brad.AshleyTest.ecs.Constants;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;

/**
 * Created by brad on 3/27/15.
 */
public class MapRenderingSystem extends EntitySystem
{
    public float width, height;
    SpriteBatch batch;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    FitViewport viewport;
    CameraControlComponent cameraControl;
    private boolean setup = false;

    public MapRenderingSystem(SpriteBatch batch, Entity control, float viewWidth, float viewHeight) {
        this.width = viewWidth;  // *Constants.SCALE;
        this.height = viewHeight;  // *Constants.SCALE;
        this.batch = batch;
        this.cameraControl = Mappers.cameraControl.get(control);
//        this.renderer = new OrthogonalTiledMapRenderer(this.map, 1/16f, batch);
        camera = new OrthographicCamera();
        viewport = new FitViewport(width, height, camera);
        viewport.apply();
//        renderer.setView(camera);
    }

    public void setupRenderer(TiledMap map) {
        renderer = new OrthogonalTiledMapRenderer(map, 1f / Constants.SCALE, batch);
//        camera.setToOrtho(false, width, height);
//        camera.position.x = 0;
//        camera.position.y = 0;
//        viewport = new FitViewport(, 480, camera);
//        viewport.setCamera(camera);
//        viewport.apply(true);
        setup = true;
    }

    @Override
    public void update(float delta) {
        if (setup) {
            camera.update();
//            viewport.apply(true);
//            Gdx.app.log("MapRenderer", Float.toString(camera.position.x) + " " + Float.toString(camera.position.y));
//            camera.position.set(cameraControl.camera.position.x*Constants.SCALE, cameraControl.camera.position.y*Constants.SCALE, 1);
            camera.position.set(cameraControl.camera.position);
//            camera.position.set(viewport.getWorldWidth() / 2, viewport.getWorldHeight() / 2, 0);
//            camera.position.set(320, 240, 1);
            // TODO: Figure out rotation
            renderer.setView(camera);
            // TODO: Handle blending
            renderer.render();
        }
    }
}
