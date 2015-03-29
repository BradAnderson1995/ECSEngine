package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/25/15.
 */
public class TextureComponent extends Component implements Pool.Poolable
{
    public TextureRegion region = null;
    public ObjectMap<String, TextureRegion> textures = null;
    public String frameString = null;

    @Override
    public void reset() {
        region = null;
        frameString = null;
    }
}