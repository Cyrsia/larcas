package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.HashMap;

public class TexMaster {
    public static final TexMaster INSTANCE = new TexMaster();

    public final Texture TestTex = new Texture("blocks/test.png");

    public final Texture StoneBlockTex = new Texture("blocks/stone.png");
    public final Texture StoneBlockUpTex = new Texture("blocks/stone_up.png");
    public final Texture StoneBlockMidTex = new Texture("blocks/stone_mid.png");
    public final Texture StoneBlockDownTex = new Texture("blocks/stone_down.png");

    public final Texture BrokenBlockTex = new Texture("blocks/broken.png");
    public final Texture BreakableBlockTex = new Texture("blocks/breakable.png");

    private TexMaster(){}
    public void disposeAll(){
        TestTex.dispose();
        BrokenBlockTex.dispose();
        BreakableBlockTex.dispose();
        StoneBlockTex.dispose();
        StoneBlockUpTex.dispose();
        StoneBlockMidTex.dispose();
        StoneBlockDownTex.dispose();
    }
}
