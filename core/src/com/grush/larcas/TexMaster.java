package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TexMaster {
    public static final TexMaster INSTANCE = new TexMaster();

    public final Texture TestTex = new Texture("blocks/test.png");

    public final Texture StoneTex = new Texture("blocks/stone.png");
    public final Texture StoneUpTex = new Texture("blocks/stone_up.png");
    public final Texture StoneMidTex = new Texture("blocks/stone_mid.png");
    public final Texture StoneDownTex = new Texture("blocks/stone_down.png");

    public final Texture BrokenBlockTex = new Texture("blocks/broken.png");
    public final Texture BreakableBlockTex = new Texture("blocks/breakable.png");

    private TexMaster(){}
    public void disposeAll(){
        StoneTex.dispose();
        StoneUpTex.dispose();
        StoneMidTex.dispose();
        StoneDownTex.dispose();
        TestTex.dispose();
        BrokenBlockTex.dispose();
        BreakableBlockTex.dispose();
    }
}
