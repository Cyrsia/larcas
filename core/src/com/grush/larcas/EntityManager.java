package com.grush.larcas;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {
    final public List<Entity> entities = new ArrayList<>();

    public static EntityManager INSTANCE = new EntityManager();

    public void addEntity(Entity entity) {
        entities.add(entity);
    }
}
