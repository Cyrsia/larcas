package com.grush.larcas;

import com.grush.larcas.server.ClientWorldChain;

public class ActionHost {
    public void jump(Entity entity){
        entity.jump();
    }
    public void spell(Entity entity, int ID){
        entity.spell(ID);
    }
    public void hit(Entity entity){
        entity.hit();
    }
    public void interact(Entity entity){
        entity.interact();
    }
    public void spawn(Entity entity){
        entity.spawn();
    }
    public void kill(Entity entity){
        entity.kill();
    }
    public final void execute(Action action){
        LogMaster.INSTANCE.log(action.entityActionType);
        switch (action.type){
            case WorldAction:{
                break;
            }
            case EntityAction:{
                switch (action.entityActionType){
                    case JUMP:{
                        ((ClientWorldChain) VarField.worldChain).serverHandler.writer.println("PlayerAction jump");
                        break;
                    }
                    case SPELL:{
                        ((ClientWorldChain) VarField.worldChain).serverHandler.writer.println("PlayerAction spell " + action.actionNum);
                        break;
                    }
                    case HIT:{
                        ((ClientWorldChain) VarField.worldChain).serverHandler.writer.println("PlayerAction hit");
                        break;
                    }
                    case INTERACT:{
                        ((ClientWorldChain) VarField.worldChain).serverHandler.writer.println("PlayerAction interact");
                        break;
                    }
                    case SPAWN:{
                        spawn(action.entity);
                        break;
                    }
                    case KILL:{
                        kill(action.entity);
                        break;
                    }
                }
                break;
            }
        }
    }
}
