package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

public class StoneBlock extends Block {
    Texture texture = TexMaster.INSTANCE.StoneTex;

    StoneBlock(int x, int y) {
        super(x, y);
        this.updateState();
    }
    @Override
    public void updateState(){
        boolean Up = World.INSTANCE.getBlock(this.x, this.y - 1) instanceof StoneBlock;
        boolean Down = World.INSTANCE.getBlock(this.x, this.y + 1) instanceof StoneBlock;

        if (Up && Down){
            this.texture = TexMaster.INSTANCE.StoneMidTex;
        } else if (Down){
            this.texture = TexMaster.INSTANCE.StoneUpTex;
        } else if (Up){
            this.texture = TexMaster.INSTANCE.StoneDownTex;
        } else {
            this.texture = TexMaster.INSTANCE.StoneTex;
        }
    }
    public Texture getTexture(){
        return this.texture;
    }
}
