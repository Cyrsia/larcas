package com.grush.larcas.server;

import com.grush.larcas.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

class ClientHandler implements Runnable {
    private final Socket clientSocket;
    Player player;
    public PrintWriter writer;
    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        LogMaster.INSTANCE.log("ClientHandler: " + clientSocket.toString());
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            writer = new PrintWriter(clientSocket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = reader.readLine()) != null) {
                LogMaster.INSTANCE.log("Client: " + inputLine);
                if        (inputLine.equals("bye")) {
                    writer.println("bye");
                    break;
                } else if (inputLine.equals("ClientHello")) {
                    writer.println("ServerHello");
                    writer.println("response_getSize");
                    writer.println(Arrays.toString(ServerVarField.worldChain.getSize()));
                } else if (inputLine.startsWith("getChunk_request")){
                    String[] xy = inputLine.replaceAll("getChunk_request ", "").replaceAll("[\\[\\]]", "").split(", ");
                    int x = Integer.parseInt(xy[0]);
                    int y = Integer.parseInt(xy[1]);
                    Chunk chunk = ServerVarField.worldChain.getChunk(x, y);
                    writer.print("response_getChunk ");
                    writer.print(Arrays.toString(xy));
                    writer.print(" ");

                    for (Block[] blockLayer : chunk.getData()) {
                        for (Block block : blockLayer) {
                            if (block instanceof BreakableBlock){
                                writer.print(1);
                            } else {
                                writer.print(0);
                            }
                        }
                    }
                    writer.println();
                } else if (inputLine.equals("PlayerSpawn")) {
                    player = new Player(new Coordinate<>(50f, 50f), null, ServerVarField.worldChain);
                    player.spawn();
                    writer.println("PlayerSpawned [" + player.coordinate.x + ", " + player.coordinate.y + "]");
                }
            }

            LogMaster.INSTANCE.log("ClientHandler: " + clientSocket.toString() + " closed");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}