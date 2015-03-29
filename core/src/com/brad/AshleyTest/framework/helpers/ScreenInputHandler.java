package com.brad.AshleyTest.framework.helpers;

import com.brad.AshleyTest.framework.config.ControlSettings;

/**
 * Created by brad on 3/22/15.
 */
public class ScreenInputHandler extends AbstractInputHandler
{
    private ControlSettings controls;

    public ScreenInputHandler(ControlSettings controls) {
        this.controls = controls;
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (controls.getControl("Quit").mappedTo(keyCode)) {
            return true;
        }
        return false;
    }
}
