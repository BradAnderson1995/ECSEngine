package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Pool;

/**
 * Created by brad on 3/25/15.
 */
public class TransformComponent extends Component implements Pool.Poolable
{
    public Vector3 pos = new Vector3(0.f, 0.f, 0.f);
    public Vector2 scale = new Vector2(1.f, 1.f);
    public Vector2 size = new Vector2(16.f, 16.f);
    public Vector2 origin = new Vector2(0.f, 0.f);
    public float rotation = 0.f;
    public boolean autosize = true;
    public boolean autoCenterOrigin = true;

    // Origin is not used for drawing, only scaling and rotation
    public void setCenterOrigin() {
        this.origin.set(this.size.x / 2f, this.size.y / 2f);
    }

    @Override
    public void reset() {
        pos.set(0.f, 0.f, 0.f);
        scale.set(1.f, 1.f);
        rotation = 0.f;
        size.set(16.f, 16.f);
        origin.set(0.f, 0.f);
        autosize = true;
        autoCenterOrigin = true;
    }
}
