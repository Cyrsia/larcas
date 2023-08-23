package com.grush.larcas;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class EntityManager {
    final public List<Entity> entities = new CopyOnWriteArrayList<>();

    public EntityManager() {
        LogMaster.INSTANCE.log("EntityManager");
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.spawned = true;
    }

    public void removeEntity(Entity entity) {
        entity.spawned = false;
    }
    public void updateAll() {
        CopyOnWriteArrayList<Entity> deleted = new CopyOnWriteArrayList<>();
        for (Entity entity : entities) {
            entity.update();
            entity.entityCollisionCheck();
            if (!entity.spawned){
                deleted.add(entity);
            }
        }
        entities.removeAll(deleted);
    }
}
