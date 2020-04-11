package com.mcsunnyside.craftbookoptimize;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.bukkit.Chunk;
import org.bukkit.Location;

import java.util.concurrent.TimeUnit;

public class Limiter {
    static public Cache<Chunk,Integer> chunkCache = CacheBuilder.newBuilder()
                                                .expireAfterWrite(60, TimeUnit.SECONDS)
                                                .build();
    static public int limitPerMintues = 400;
    public static boolean ping(Location location, Class<?> type){
        Chunk chunk = location.getChunk();
        Integer times = chunkCache.getIfPresent(chunk);
        if(times == null){
            times = 0;
        }
        times ++;
        chunkCache.put(chunk, times);
        return times <= limitPerMintues;
    }

}
