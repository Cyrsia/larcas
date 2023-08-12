package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

public class StoneBlock extends Block {
    StoneBlock(int x, int y, IWorldChain worldChain) {
        super(x, y, worldChain);
        this.states.put("texture", "stone");
        this.updateState();
    }
    @Override
    public void updateState(){
        boolean Up = worldChain.getBlock(this.x, this.y - 1) instanceof StoneBlock;
        boolean Down = worldChain.getBlock(this.x, this.y + 1) instanceof StoneBlock;

        if (Up && Down){
            this.states.put("texture", "mid");
        } else if (Down){
            this.states.put("texture", "up");
        } else if (Up){
            this.states.put("texture", "down");
        } else {
            this.states.put("texture", "stone");
        }
    }
    public Texture getTexture(){
        return TexMaster.INSTANCE.StoneBlockTex;
    }
    @Override
    public void hit() {
        Block right = worldChain.getBlock(this.x + 1, this.y);
        Block left = worldChain.getBlock(this.x - 1, this.y);

        if (right instanceof StoneBlock && left instanceof StoneBlock) {
            if (right.states.get("texture").equals("mid") && left.states.get("texture").equals("mid")) {
                this.destroy();
                worldChain.setBlock(this.x, this.y, BreakableBlock.class);
            }
        }
    }
}
