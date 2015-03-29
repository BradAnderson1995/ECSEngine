package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/28/15.
 */
public class CameraControlComponent extends Component implements Pool.Poolable
{
    public OrthographicCamera camera;

    public CameraControlComponent() {
        camera = new OrthographicCamera();
    }

    @Override
    public void reset() {
        camera = null;
    }
}
