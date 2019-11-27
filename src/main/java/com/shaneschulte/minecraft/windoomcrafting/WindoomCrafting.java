package com.shaneschulte.minecraft.windoomcrafting;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

/**
 *
 * @author CoolGamrSms
 */
public class WindoomCrafting extends JavaPlugin {
    
    public WorldGuardPlugin worldguard;
    
    @Override
    public void onEnable() {
        System.out.println("WindoomCrafting 1.0-SNAPSHOT enabled!");
        worldguard = getWorldGuard();
        
        getServer().getPluginManager().registerEvents(new CraftListener(this), this);
        getServer().getPluginManager().registerEvents(new ClanListener(), this);
        
        // disable fucking phantoms
        getServer().getScheduler().scheduleSyncRepeatingTask(this, () -> {
            for(Player p : getServer().getOnlinePlayers())
                p.setStatistic(Statistic.TIME_SINCE_REST, 0);
        }, 0L, 800L);
    }
    
    @Override
    public void onDisable() {
        System.out.println("WindoomCrafting 1.0-SNAPSHOT disabled.");
    }
 
    private WorldGuardPlugin getWorldGuard() {
        Plugin plugin = getServer().getPluginManager().getPlugin("WorldGuard");

        // WorldGuard may not be loaded
        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null; // Maybe you want throw an exception instead
        }

        return (WorldGuardPlugin) plugin;
    }
}
