package com.grush.larcas.server;

import com.grush.larcas.BreakableBlock;
import com.grush.larcas.Chunk;
import com.grush.larcas.LogMaster;
import com.grush.larcas.VarField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;

public class ServerHandler {
    String host;
    int port;
    Socket socket;
    BufferedReader reader;
    PrintWriter writer;
    Thread Receiver;
    ClientWorldChain world;
    boolean wait = false;
    public ServerHandler(String host, int port, ClientWorldChain world) {
        this.host = host;
        this.port = port;
        this.world = world;
        LogMaster.INSTANCE.log("ServerHandler");
    }

    public void start() {
        try {
            socket = new Socket(host, port);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            LogMaster.INSTANCE.error(e);
        }
        Receiver = new Thread(() -> receiver(reader));
        Receiver.start();

        writer.println("ClientHello");
    }

    public void receiver(BufferedReader reader) {
        try {
            String inputLine;
            String previousLine = "";
            while ((inputLine = reader.readLine()) != null) {
                LogMaster.INSTANCE.log("Server: " + inputLine);
                if (previousLine.equals("response_getSize")) {
                    String[] values = inputLine.replaceAll("[\\[\\]]", "").split(", ");
                    world.size = new int[2];
                    world.size[0] = Integer.parseInt(values[0]);
                    world.size[1] = Integer.parseInt(values[1]);
                } else if (inputLine.startsWith("response_getChunk")) {
                    String chunkStr = inputLine.split(" ", 2)[1].split("] ")[0] + "]";
                    String[] values = chunkStr.replaceAll("[\\[\\]]", "").split(", ");
                    Chunk chunk = new Chunk(
                                    Integer.parseInt(values[0]),
                                    Integer.parseInt(values[1]),
                                    world
                            );
                    world.chunks.put(chunkStr,
                            chunk
                    );
                    int i = 0;
                    for (char c : inputLine.split(" ")[3].toCharArray()) {
                        int x = i % Chunk.sizeX;
                        int y = i / Chunk.sizeX;
                        if (c == '1') {
                            chunk.setBlock(
                                    x,
                                    y,
                                    new BreakableBlock(chunk.x*Chunk.sizeX + x, chunk.y*Chunk.sizeY + y, world)
                            );
                        }
                        i++;
                    }
                }
                previousLine = inputLine;
            }
        } catch (IOException e) {
            LogMaster.INSTANCE.error(e);
        }
    }

    public void dispose(){
        try {
            reader.close();
            writer.close();
            socket.close();
            Receiver.interrupt();
        } catch (IOException e) {
            LogMaster.INSTANCE.error(e);
        }

    }
}
