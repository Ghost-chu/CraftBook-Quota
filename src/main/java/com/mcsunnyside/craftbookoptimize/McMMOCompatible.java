package com.mcsunnyside.craftbookoptimize;

import com.gmail.nossr50.config.WorldBlacklist;
import com.gmail.nossr50.mcMMO;
import com.gmail.nossr50.util.BlockUtils;
import org.bukkit.Bukkit;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;

public class McMMOCompatible {
    static boolean loaded = false;
    static Plugin mcmmo = null;
    public static void setBlockPlace(Block data){
        loadMcMMO();
        if(mcmmo == null){
            return;
        }
        if(WorldBlacklist.isWorldBlacklisted(data.getWorld())){
            return;
        }
        if (BlockUtils.shouldBeWatched(data.getState()) && (!Tag.LOGS.isTagged(data.getType()) || !Tag.LOGS.isTagged(data.getType()))) {
            mcMMO.getPlaceStore().setTrue(data);
        }
    }

    private static void loadMcMMO(){
        if(loaded){
            return;
        }
        mcmmo = Bukkit.getPluginManager().getPlugin("mcMMO");
        loaded=true;
    }
}
