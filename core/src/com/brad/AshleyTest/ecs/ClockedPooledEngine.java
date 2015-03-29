package com.brad.AshleyTest.ecs;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.brad.AshleyTest.framework.helpers.GameClock;

import java.util.Comparator;

/**
 * Created by brad on 3/27/15.
 */
public class ClockedPooledEngine extends PooledEngine
{
    private static SystemComparator logicComparator = new SystemComparator();
    private static SystemComparator renderComparator = new SystemComparator();
    private boolean updating;
    private GameClock clock;
    private Array<EntitySystem> logicSystems;
    private ObjectMap<Class<?>, EntitySystem> logicSystemsByClass;
    private Array<EntitySystem> renderSystems;
    private ObjectMap<Class<?>, EntitySystem> renderSystemsByClass;


    public ClockedPooledEngine(int tps, int maxFPS) {
        super();
        updating = false;
        clock = new GameClock(tps, maxFPS);
        logicSystems = new Array<EntitySystem>(false, 16);
        logicSystemsByClass = new ObjectMap<Class<?>, EntitySystem>();
        renderSystems = new Array<EntitySystem>(false, 16);
        renderSystemsByClass = new ObjectMap<Class<?>, EntitySystem>();
    }

    public void addLogicSystem(EntitySystem system) {
        Class<? extends EntitySystem> systemType = system.getClass();

        if (!logicSystemsByClass.containsKey(systemType)) {
            logicSystems.add(system);
            logicSystemsByClass.put(systemType, system);
            system.addedToEngine(this);

            logicSystems.sort(logicComparator);
        }
    }

    public void addRenderSystem(EntitySystem system) {
        Class<? extends EntitySystem> systemType = system.getClass();

        if (!renderSystemsByClass.containsKey(systemType)) {
            renderSystems.add(system);
            renderSystemsByClass.put(systemType, system);
            system.addedToEngine(this);

            renderSystems.sort(renderComparator);
        }
    }


    public void removeLogicSystem(EntitySystem system) {
        if (logicSystems.removeValue(system, true)) {
            logicSystemsByClass.remove(system.getClass());
            system.removedFromEngine(this);
        }
    }


    public void removeRenderSystem(EntitySystem system) {
        if (renderSystems.removeValue(system, true)) {
            renderSystemsByClass.remove(system.getClass());
            system.removedFromEngine(this);
        }
    }

    @Override
    public void update(float delta) {
        updating = true;
        this.clock.update();
        while (clock.runLogic()) {
            for (int i = 0; i < logicSystems.size; i++) {
                EntitySystem system = logicSystems.get(i);
                if (system.checkProcessing()) {
                    system.update(delta);
                }

                // No way to process component operations or pending entity operations
            }
        }
        if (clock.drawReady) {
            for (int i = 0; i < renderSystems.size; i++) {
                EntitySystem system = renderSystems.get(i);
                if (system.checkProcessing()) {
                    system.update(delta);
                }
            }
        }
        super.update(delta);
    }

    private static class SystemComparator implements Comparator<EntitySystem>
    {
        @Override
        public int compare(EntitySystem a, EntitySystem b) {
            return a.priority > b.priority ? 1 : (a.priority == b.priority) ? 0 : -1;
        }
    }
}
