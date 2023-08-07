package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

public class BreakableBlock extends Block {
    private boolean isBroken = false;

    public BreakableBlock(int x, int y) {
        super(x, y);
    }

    @Override
    public Texture getTexture() {
        return isBroken ? TexMaster.INSTANCE.BrokenBlockTex : TexMaster.INSTANCE.BreakableBlockTex;
    }

    @Override
    public void interact() {
        if (!isBroken) {
            isBroken = true;
        }
    }
}
