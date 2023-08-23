package com.grush.larcas;

public class ActionHost {
    private ActionHost(){

    }
    public static final ActionHost INSTANCE = new ActionHost();

    public void execute(Action action){
        switch (action.type){
            case WorldAction:{
                break;
            }
            case EntityAction:{
                switch (action.entityActionType){
                    case JUMP:{
                        action.entity.jump();
                        break;
                    }
                    case SPELL:{
                        action.entity.spell(action.actionNum);
                        break;
                    }
                    case HIT:{
                        action.entity.hit();
                        break;
                    }
                    case INTERACT:{
                        action.entity.interact();
                        break;
                    }
                    case SPAWN:{
                        action.entity.spawn();
                        break;
                    }
                    case KILL:{
                        action.entity.kill();
                        break;
                    }
                }
                break;
            }
        }
    }
}
