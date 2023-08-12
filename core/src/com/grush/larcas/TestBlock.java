package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

public class TestBlock extends Block {
    public TestBlock(int x, int y, IWorldChain worldChain) {
        super(x, y, worldChain);
    }
    public Texture getTexture(){
        return TexMaster.INSTANCE.TestTex;
    }
}
