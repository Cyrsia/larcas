package com.grush.larcas;

public interface IChunkFactory {
     default Chunk generateChunk(int x, int y){
         return new Chunk(x, y);
     }
}
