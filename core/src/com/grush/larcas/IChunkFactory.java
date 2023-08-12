package com.grush.larcas;

public interface IChunkFactory {
     default Chunk generateChunk(int x, int y, IWorldChain worldChain){
         return new Chunk(x, y, worldChain);
     }
}
