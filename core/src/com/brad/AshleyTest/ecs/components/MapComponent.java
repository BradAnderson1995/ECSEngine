package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/26/15.
 */
public class MapComponent extends Component implements Pool.Poolable
{
    public TiledMap map;
    public String mapName;

    public MapComponent() {
        map = null;
        mapName = null;
    }

    @Override
    public void reset() {
        map = null;
        mapName = null;
    }
}
