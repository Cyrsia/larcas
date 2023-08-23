package com.grush.larcas.server;

import com.grush.larcas.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWorldChain extends LocalWorldChain {
    int port;
    World world;
    public ServerWorldChain(World world, int port) {
        super(world);
        this.world = world;
        this.port = port;
        ServerVarField.worldChain = this;
        world.setWorldChain(this);
    }

    public void start(){
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(port)){
                LogMaster.INSTANCE.log("Server started");
                while (true) {
                    Socket clientSocket = serverSocket.accept();

                    Thread clientThread = new Thread(new ClientHandler(clientSocket));
                    clientThread.start();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
