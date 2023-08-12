package com.grush.larcas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

public class Dummy extends Entity{
    float timer = 0;
    boolean damagedOnce;

    public Dummy(Coordinate<Float> coordinate, Map<?, ?> states, IWorldChain worldChain){
        super(coordinate, states, worldChain);
        this.hp = 500;
        this.size = new float[]{1, 2};
    }

    @Override
    public Texture getTexture(){
        if (damagedOnce & timer < 0.25f){
            return TexMaster.INSTANCE.DummyHurtTex;
        } else {
            return TexMaster.INSTANCE.DummyTex;
        }
    }

    @Override
    public void update(){
        super.update();
        timer += Gdx.graphics.getDeltaTime();
    }
    @Override
    public void reduceHp(Damage damage){
        super.reduceHp(damage);
        timer = 0;
        damagedOnce = true;
    }
}
