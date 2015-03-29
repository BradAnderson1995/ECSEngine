package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.EntitySystem;
import com.brad.AshleyTest.framework.config.ControlSettings;
import com.brad.AshleyTest.framework.helpers.ScreenInputHandler;

/**
 * Created by brad on 3/26/15.
 */
public class ScreenInputSystem extends EntitySystem
{
    ScreenInputHandler handler;
    ControlSettings controls;

    public ScreenInputSystem(ControlSettings controls) {
        this.controls = controls;
        handler = new ScreenInputHandler(this.controls);
    }

    public ScreenInputHandler getHandler() {
        return handler;
    }
}
