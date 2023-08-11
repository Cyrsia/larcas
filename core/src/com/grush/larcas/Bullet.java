package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

public class Bullet extends Entity{
    public Bullet(Coordinate<Float> coordinate, Map<?, ?> states) {
        super(coordinate, states);
        this.size = new float[]{0.5f, 0.5f};
    }

    @Override
    public Texture getTexture() {
        return TexMaster.INSTANCE.BulletTex;
    }

    @Override
    public void collision(){
        this.kill();
    }
}
