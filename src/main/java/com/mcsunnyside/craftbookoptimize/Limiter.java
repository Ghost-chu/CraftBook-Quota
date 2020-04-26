package com.mcsunnyside.craftbookoptimize;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.concurrent.TimeUnit;

public class Limiter {
    static public Cache<Chunk,ContainerInteger> chunkCache = CacheBuilder.newBuilder()
                                                .expireAfterWrite(1, TimeUnit.MINUTES)
                                                .build();
    static public int limitPerMintues = 500;
    public static boolean ping(Location location, Class<?> type){

        Chunk chunk = location.getChunk();
        ContainerInteger times = chunkCache.getIfPresent(chunk);
        if(times == null){
            times = new ContainerInteger();
            times.update();
            chunkCache.put(chunk, times);
            return false;
        }
        if(times.getNum() > limitPerMintues){
            return false;
        }
        times.update();
        return times.getNum() <= limitPerMintues;
    }

}
class ContainerInteger{
    int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
    public void update(){
        this.num ++;
    }
}
