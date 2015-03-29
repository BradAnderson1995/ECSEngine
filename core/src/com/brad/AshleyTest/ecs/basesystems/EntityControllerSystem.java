package com.brad.AshleyTest.ecs.basesystems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.utils.Array;
import com.brad.AshleyTest.framework.config.ControlSettings;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by brad on 3/26/15.
 */
public abstract class EntityControllerSystem extends IteratingLogicSystem implements InputProcessor
{
    protected ControlSettings controls;
    protected Array<String> controlsPending;
    protected Array<String> controlsHeld;

    public EntityControllerSystem(Family family, ControlSettings controls, int tps) {
        super(family, 1.f / tps);
        this.controls = controls;
        this.controlsPending = new Array<String>();
        this.controlsHeld = new Array<String>();
    }

    @Override
    protected abstract void processEntity(Entity entity);

    @Override
    protected void updateInterval() {
        pollKeyboard();
        super.updateInterval();
        controlsPending.clear();
        controlsHeld.clear();
    }

    protected void pollKeyboard() {
        Iterator<Map.Entry<String, ControlSettings.Control>> controlIterator = controls.getIterator();
        while (controlIterator.hasNext()) {
            Map.Entry<String, ControlSettings.Control> control = controlIterator.next();
            for (Integer key : control.getValue()) {
                if (Gdx.input.isKeyPressed(key)) {
                    if (!controlsHeld.contains(control.getKey(), false)) {
                        controlsHeld.add(control.getKey());
//                        Gdx.app.log("PollKeyboard", control.getKey());
                    }
                }
            }
        }
    }

    @Override
    public boolean keyDown(int keycode) {
        Iterator<Map.Entry<String, ControlSettings.Control>> controlIterator = controls.getIterator();
        while (controlIterator.hasNext()) {
            Map.Entry<String, ControlSettings.Control> control = controlIterator.next();
            if (control.getValue().mappedTo(keycode)) {
                if (!controlsPending.contains(control.getKey(), false)) {
                    controlsPending.add(control.getKey());
                    Gdx.app.log("KeyDown", control.getKey());
                }
                return true;
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
