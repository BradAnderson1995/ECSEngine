package com.brad.AshleyTest.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Pool;

/**
 * TODO: Add a class header comment!
 */
public class StaticBodyComponent extends Component implements Pool.Poolable
{
    public BodyDef bodyDef = new BodyDef();
    public FixtureDef fixtureDef = new FixtureDef();
    public Body body;
    public Fixture fixture;
    public Entity entity;
    public float density = 0f;
    public float friction = 0f;
    public float restitution = 0f;
    public boolean autoCollisionBox = true;
    public boolean updateCollisions = true;

    public StaticBodyComponent() {
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(0, 0);
    }

    public void makeBody(World world) {
        body = world.createBody(bodyDef);
    }

    public void setCollisionCircle(float radius, float density, float friction, float restitution) {
        CircleShape circle = new CircleShape();
        circle.setRadius(radius);
        fixtureDef.shape = circle;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixture = body.createFixture(fixtureDef);
        circle.dispose();
    }

    public void setCollisionBox(float width, float height, float density, float friction, float restitution) {
        PolygonShape box = new PolygonShape();
        box.setAsBox(width, height);
        fixtureDef.shape = box;
        fixtureDef.density = density;
        fixtureDef.friction = friction;
        fixtureDef.restitution = restitution;
        fixture = body.createFixture(fixtureDef);
        box.dispose();
    }

    @Override
    public void reset() {
        bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        fixtureDef = new FixtureDef();
        body = null;
        fixture = null;
        density = 0f;
        friction = 0f;
        restitution = 0f;
        autoCollisionBox = true;
        updateCollisions = true;
    }
}
