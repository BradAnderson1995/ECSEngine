package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/27/15.
 */
public class AssetComponent extends Component implements Pool.Poolable
{
    public Array<String> textureName;
    public String atlasName;
    public String mapName;
    public boolean handledAssets = false;

    public AssetComponent() {
        textureName = new Array<String>();
        atlasName = null;
        mapName = null;
    }

    @Override
    public void reset() {
        textureName.clear();
        atlasName = null;
        mapName = null;
    }
}
