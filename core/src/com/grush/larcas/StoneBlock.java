package com.grush.larcas;

import com.badlogic.gdx.graphics.Texture;

import java.util.Map;

public class StoneBlock extends Block {
    StoneBlock(int x, int y) {
        super(x, y);
        this.states.put("texture", "stone");
        this.updateState();
    }
    @Override
    public void updateState(){
        boolean Up = World.INSTANCE.getBlock(this.x, this.y - 1) instanceof StoneBlock;
        boolean Down = World.INSTANCE.getBlock(this.x, this.y + 1) instanceof StoneBlock;

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
        Block right = World.INSTANCE.getBlock(this.x + 1, this.y);
        Block left = World.INSTANCE.getBlock(this.x - 1, this.y);

        if (right instanceof StoneBlock && left instanceof StoneBlock) {
            if (right.states.get("texture").equals("mid") && left.states.get("texture").equals("mid")) {
                this.destroy();
                World.INSTANCE.setBlock(this.x, this.y, BreakableBlock.class);
            }
        }
    }
}
