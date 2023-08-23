package com.grush.larcas;

import com.grush.larcas.server.ClientWorldChain;

public class VarField {
    static IWorldChain worldChain = new ClientWorldChain("213.178.53.80", 3491);
    static ActionHost actionHost;
}
