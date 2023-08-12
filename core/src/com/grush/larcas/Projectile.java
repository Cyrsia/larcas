package com.grush.larcas;

import java.util.Map;

public abstract class Projectile extends Entity {
    public Projectile(Coordinate<Float> coordinate, Map<?, ?> states, IWorldChain worldChain) {
        super(coordinate, states, worldChain);
    }

    abstract Damage getDamage();

    public void hit(Entity entity){
        entity.reduceHp(getDamage());
    }

    @Override
    public void entityCollision(Entity entity){
        this.hit(entity);
    }

    @Override
    public void checkHp(){}
}
