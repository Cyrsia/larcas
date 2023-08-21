package com.grush.larcas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

public class JumpingDummy extends Dummy{
    float jumpPrev = 0;

    public JumpingDummy(Coordinate<Float> coordinate, Map<?, ?> states, IWorldChain worldChain) {
        super(coordinate, states, worldChain);
    }

    @Override
    public void update(){
        super.update();
        timer += Gdx.graphics.getDeltaTime();
        if (timer - jumpPrev > 2f){
            jump();
            jumpPrev = timer;
        }
    }

    @Override
    public void reduceHp(Damage damage){
        super.reduceHp(damage);
        jumpPrev = 0;
    }

    @Override
    public Texture getTexture(){
        if (damagedOnce & timer < 0.25f){
            return TexMaster.INSTANCE.DummyHurtTex;
        } else {
            return TexMaster.INSTANCE.JumpingDummyTex;
        }
    }
}
