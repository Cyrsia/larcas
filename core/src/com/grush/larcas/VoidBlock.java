package com.grush.larcas;

public class VoidBlock extends Block {
    public VoidBlock(int x, int y){
        super(x, y);
        this.visible = false;
        this.isSolid = false;
    }

    @Override
    public void hit(){
        World.INSTANCE.setBlock(this.x, this.y, StoneBlock.class);
    }
}
