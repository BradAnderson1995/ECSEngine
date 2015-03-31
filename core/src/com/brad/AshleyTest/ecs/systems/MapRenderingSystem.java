package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.brad.AshleyTest.ecs.Mappers;
import com.brad.AshleyTest.ecs.components.CameraControlComponent;

/**
 * Created by brad on 3/27/15.
 */
public class MapRenderingSystem extends EntitySystem
{
    public int width, height;
    SpriteBatch batch;
    TiledMap map;
    OrthogonalTiledMapRenderer renderer;
    OrthographicCamera camera;
    FitViewport viewport;
    CameraControlComponent cameraControl;
    private boolean setup = false;

    public MapRenderingSystem(SpriteBatch batch, Entity control, int tileWidth, int tileHeight, int tileSize) {
        this.width = tileWidth;
        this.height = tileHeight;
        this.batch = batch;
        this.cameraControl = Mappers.cameraControl.get(control);
//        this.renderer = new OrthogonalTiledMapRenderer(this.map, 1/16f, batch);
//        viewport = new FitViewport(viewWidth, viewHeight, camera);
//        viewport = new FitViewport(15, 20)
//        viewport.apply();
//        renderer.setView(camera);
    }

    public void setupRenderer(TiledMap map) {
        renderer = new OrthogonalTiledMapRenderer(map, batch);
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
//        camera.position.x = 0;
//        camera.position.y = 0;
//        viewport = new FitViewport(640, 480, camera);
//        viewport.apply();
        setup = true;
    }

    @Override
    public void update(float delta) {
        if (setup) {
            camera.update();
            Gdx.app.log("MapRenderer", Float.toString(camera.position.x) + " " + Float.toString(camera.position.y));
            camera.position.set(cameraControl.camera.position);
//            camera.position.set(320, 240, 1);
            // TODO: Figure out rotation
            renderer.setView(camera);
            // TODO: Handle blending
            renderer.render();
        }
    }
}
