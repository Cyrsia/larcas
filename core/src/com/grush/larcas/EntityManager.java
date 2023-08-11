package com.grush.larcas;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    final public List<Entity> entities = new ArrayList<>();
    public static EntityManager INSTANCE = new EntityManager();

    public void addEntity(Entity entity) {
        entities.add(entity);
        entity.spawned = true;
    }

    public void removeEntity(Entity entity) {
        entity.spawned = false;
    }
    public void updateAll() {
        ArrayList<Entity> deleted = new ArrayList<>();
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
