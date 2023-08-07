package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

public class StoneBlock extends Block {
    StoneBlock(int x, int y) {
        super(x, y);
    }
    public Texture getTexture(){
        return TexMaster.INSTANCE.StoneTex;
    }
}
