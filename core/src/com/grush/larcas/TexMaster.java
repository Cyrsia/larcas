package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TexMaster {
    public static final TexMaster INSTANCE = new TexMaster();
    public final Texture StoneTex = new Texture("blocks/stone.png");
    public final Texture TestTex = new Texture("blocks/test.png");
    private TexMaster(){}
    public void disposeAll(){
        StoneTex.dispose();
        TestTex.dispose();
    }
}
