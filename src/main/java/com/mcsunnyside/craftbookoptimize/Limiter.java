package com.mcsunnyside.craftbookoptimize;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.MapMaker;
import org.bukkit.Location;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Limiter {
    static public Cache<Location,Integer> cache = CacheBuilder.newBuilder().maximumSize(2000)
                                                .expireAfterWrite(60, TimeUnit.SECONDS)
                                                .build();
    static public int limitPerMintues = 40;

    public static boolean ping(Location location, Class<?> type){
        Integer times = cache.getIfPresent(location);
        if(times == null){
            times = 0;
        }
        times ++;
        cache.put(location, times);
        return times <= limitPerMintues;
    }

}
