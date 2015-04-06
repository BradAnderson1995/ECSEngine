package com.brad.AshleyTest.framework.config;

import com.badlogic.gdx.Input;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by brad on 3/22/15.
 */
public class ControlSettings
{
    private Controls controls;

    public ControlSettings() {

        controls = new Controls();

        setControl("Quit", Input.Keys.ESCAPE);
        setControl("Up", Input.Keys.W);
        setControl("Up", Input.Keys.UP);
        setControl("Down", Input.Keys.S);
        setControl("Down", Input.Keys.DOWN);
        setControl("Left", Input.Keys.A);
        setControl("Left", Input.Keys.LEFT);
        setControl("Right", Input.Keys.D);
        setControl("Right", Input.Keys.RIGHT);
        setControl("Shoot", Input.Keys.SPACE);
        setControl("PhysicsDebug", Input.Keys.B);
    }

    public void setControl(String key, int newKey) {
        if (controls.containsKey(key)) {
            if (!controls.get(key).contains(newKey)) {
                controls.get(key).add(newKey);
            }
//            Gdx.app.log(key, Integer.toString(newKey));
        } else {
            controls.put(key, new Control());
            setControl(key, newKey);
        }
    }

    public void removeControl(String key, int removeKey) {
        if (controls.containsKey(key)) {
            if (controls.get(key).contains(removeKey)) {
                controls.get(key).remove(controls.get(key).indexOf(removeKey));
            }
        }
    }

    public void remapControl(String key, String replaceKey, int remapKey) {
        removeControl(key, remapKey);
        setControl(replaceKey, remapKey);
    }

    public Control getControl(String key) {
        return controls.get(key);
    }

    public Iterator<Map.Entry<String, Control>> getIterator() {
        return getControls().entrySet().iterator();
    }

    public HashMap<String, Control> getControls() {
        return controls;
    }

    public class Control extends LinkedList<Integer>
    {
        public boolean mappedTo(Integer keyCode) {
            for (Integer key : this) {
                if (keyCode.equals(key)) {
                    return true;
                }
            }
            return false;
        }
    }

    class Controls extends HashMap<String, Control>
    {
    }
}
