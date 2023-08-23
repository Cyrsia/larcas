package com.grush.larcas;

public enum EAction {
    EntityAction,
    WorldAction;
    public enum EEntityAction {
        SPAWN,
        KILL,
        JUMP,
        SPELL,
        HIT,
        INTERACT
    }
    public enum EWorldAction {
        SETBLOCK,
        DESTROYBLOCK
    }
}
