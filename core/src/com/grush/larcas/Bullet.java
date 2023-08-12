package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

public class Bullet extends Projectile{
    static Damage damage = new Damage(50, EDamageType.BULLET);
    public Bullet(Coordinate<Float> coordinate, Map<?, ?> states) {
        super(coordinate, states);
        this.size = new float[]{0.5f, 0.5f};
        this.groupCollision = false;
    }

    @Override
    Damage getDamage() {
        return damage;
    }

    @Override
    public Texture getTexture() {
        return TexMaster.INSTANCE.BulletTex;
    }

    @Override
    public void collision(){
        this.kill();
    }

    @Override
    public void entityCollision(Entity entity){
        super.entityCollision(entity);
        float max = entity.vector.max;
        entity.vector = this.vector.clone();
        entity.vector.max = max;
        entity.vector.transform(0.2f);
    }
}
