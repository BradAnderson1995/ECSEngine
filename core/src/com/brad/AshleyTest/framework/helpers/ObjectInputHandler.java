package com.brad.AshleyTest.framework.helpers;

import com.brad.AshleyTest.framework.config.ControlSettings;
import com.brad.AshleyTest.framework.gameobjects.ObjectNode;

/**
 * Created by brad on 3/24/15.
 */
public class ObjectInputHandler extends AbstractInputHandler
{
    public ObjectNode node;
    public ControlSettings controls;

    public ObjectInputHandler() {
    }

    public ObjectInputHandler(ControlSettings controls, ObjectNode node) {
        this.controls = controls;
        this.node = node;
    }
}
