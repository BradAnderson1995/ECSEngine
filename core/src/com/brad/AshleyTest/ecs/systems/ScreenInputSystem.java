package com.brad.AshleyTest.ecs.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.brad.AshleyTest.ecs.Constants;
import com.brad.AshleyTest.ecs.basesystems.EntityControllerSystem;
import com.brad.AshleyTest.ecs.components.PlayerControlComponent;
import com.brad.AshleyTest.framework.config.ControlSettings;

/**
 * Created by brad on 3/26/15.
 */
public class ScreenInputSystem extends EntityControllerSystem
{
    //    ScreenInputHandler handler;
    Engine engine;

    public ScreenInputSystem(ControlSettings controls, Engine engine) {
        super(Family.all(PlayerControlComponent.class).get(), controls, Constants.TPS);
//        handler = new ScreenInputHandler(this.controls);
        this.engine = engine;
    }

//    public ScreenInputHandler getHandler() {
//        return handler;
//    }

    @Override
    protected void processEntity(Entity entity) {

    }

    @Override
    protected void updateInterval() {
        super.updateInterval();
//        for (String input : controlsPending) {
//            Gdx.app.log("ScreenInputSystem", input);
//            if (input.equals("Quit")) {
//                Gdx.app.exit();
//            }
//        }
    }

    @Override
    public boolean keyDown(int keyCode) {
        if (controls.getControl("Quit").mappedTo(keyCode)) {
            Gdx.app.exit();
            return true;
        }
        if (controls.getControl("PhysicsDebug").mappedTo(keyCode)) {
            PhysicsDebugRenderingSystem debug = engine.getSystem(PhysicsDebugRenderingSystem.class);
            RenderingSystem renderingSystem = engine.getSystem(RenderingSystem.class);
            MapRenderingSystem mapRenderingSystem = engine.getSystem(MapRenderingSystem.class);
            debug.setProcessing(!debug.checkProcessing());
            renderingSystem.setProcessing(!renderingSystem.checkProcessing());
            mapRenderingSystem.setProcessing(!mapRenderingSystem.checkProcessing());
            return true;
        }
        return false;
    }
}