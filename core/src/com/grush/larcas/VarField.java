package com.grush.larcas;

import com.grush.larcas.server.ClientWorldChain;

public class VarField {
    static IWorldChain worldChain = new ClientWorldChain("37.9.146.84", 3491);
    static ActionHost actionHost;
}
