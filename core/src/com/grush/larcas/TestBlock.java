package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

public class TestBlock extends Block {
    public TestBlock(int x, int y){
        super(x, y);
    }
    public Texture getTexture(){
        return TexMaster.INSTANCE.TestTex;
    }
}
