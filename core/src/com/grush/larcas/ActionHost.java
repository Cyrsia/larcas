package com.grush.larcas;

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
                        jump(action.entity);
                        break;
                    }
                    case SPELL:{
                        spell(action.entity, action.actionNum);
                        break;
                    }
                    case HIT:{
                        hit(action.entity);
                        break;
                    }
                    case INTERACT:{
                        interact(action.entity);
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
