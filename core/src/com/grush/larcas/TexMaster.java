package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

public class TexMaster {
    public static final TexMaster INSTANCE = new TexMaster();
    public final Texture TestTex = new Texture("entities/player.png");
    public final Texture BulletTex = new Texture("entities/bullet.png");
    public final Texture DummyTex = new Texture("entities/dummy.png");
    public final Texture DummyHurtTex = new Texture("entities/dummy_hurt.png");

    public final Texture StoneBlockTex = new Texture("blocks/stone.png");
    public final Texture BrokenBlockTex = new Texture("blocks/broken.png");
    public final Texture BreakableBlockTex = new Texture("blocks/breakable.png");

    private TexMaster(){}
    public void disposeAll(){
        TestTex.dispose();
        BulletTex.dispose();
        DummyTex.dispose();
        DummyHurtTex.dispose();

        BrokenBlockTex.dispose();
        BreakableBlockTex.dispose();
        StoneBlockTex.dispose();
    }
}
