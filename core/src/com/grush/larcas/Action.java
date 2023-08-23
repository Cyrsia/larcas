package com.grush.larcas;

public class Action {
    public EAction type;
    public EAction.EEntityAction entityActionType;
    public Entity entity;
    public int actionNum;
    public Action(EAction action, EAction.EEntityAction entityAction, Entity entity) {
        this.type = action;
        this.entityActionType = entityAction;
        this.entity = entity;
    }
    public Action(EAction action, EAction.EEntityAction entityAction, Entity entity, int actionNum) {
        this.type = action;
        this.entityActionType = entityAction;
        this.entity = entity;
        this.actionNum = actionNum;
    }
    public void execute(){
        VarField.actionHost.execute(this);
    }
}
