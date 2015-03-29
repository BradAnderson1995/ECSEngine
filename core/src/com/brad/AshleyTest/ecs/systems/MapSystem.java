package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.maps.tiled.TiledMap;

/**
 * Created by brad on 3/26/15.
 */
public class MapSystem extends EntitySystem
{
    TiledMap map;

    public MapSystem() {
        map = new TiledMap();
    }
}
