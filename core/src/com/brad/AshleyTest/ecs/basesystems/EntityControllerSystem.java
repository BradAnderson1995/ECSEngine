package com.brad.AshleyTest.ecs.basesystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.brad.AshleyTest.framework.config.ControlSettings;

import java.util.Map;

/**
 * Created by brad on 3/26/15.
 */
public abstract class EntityControllerSystem extends IteratingSystem implements InputProcessor
{
    protected ControlSettings controls;
    protected Array<String> inputsPending;

    public EntityControllerSystem(Family family, ControlSettings controls) {
        super(family);
        this.controls = controls;
    }

    @Override
    protected abstract void processEntity(Entity entity, float deltaTime);

    @Override
    public boolean keyDown(int keycode) {
        while (controls.getIterator().hasNext()) {
            Map.Entry<String, ControlSettings.Control> control = controls.getIterator().next();
            if (control.getValue().mappedTo(keycode)) {
                inputsPending.add(control.getKey());
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
