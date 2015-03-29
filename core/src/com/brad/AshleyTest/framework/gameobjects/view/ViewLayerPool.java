package com.brad.AshleyTest.framework.gameobjects.view;

import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/23/15.
 */
public class ViewLayerPool extends Pool<ViewLayer>
{
    public ViewLayer newObject() {
        return new ViewLayer();
    }
}
