package com.grush.larcas;

public class VoidBlock extends Block {
    public VoidBlock(int x, int y, IWorldChain worldChain) {
        super(x, y, worldChain);
        this.visible = false;
        this.isSolid = false;
    }

    @Override
    public void hit(){
        worldChain.setBlock(this.x, this.y, StoneBlock.class);
    }
}
