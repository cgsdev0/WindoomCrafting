/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaneschulte.minecraft.windoomcrafting;

import java.util.logging.Level;
import me.lucko.luckperms.LuckPerms;
import me.lucko.luckperms.api.Group;
import me.lucko.luckperms.api.LuckPermsApi;
import me.lucko.luckperms.api.manager.GroupManager;
import net.sacredlabyrinth.phaed.simpleclans.SimpleClans;
import net.sacredlabyrinth.phaed.simpleclans.events.CreateClanEvent;
import net.sacredlabyrinth.phaed.simpleclans.events.DisbandClanEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

/**
 *
 * @author schul
 */
public class ClanListener implements Listener {
    
    @EventHandler
    public void onClanCreate(CreateClanEvent event) {
        LuckPermsApi api = LuckPerms.getApi();
        GroupManager gm = api.getGroupManager();
        gm.createAndLoadGroup("clan." + event.getClan().getTag());
        Bukkit.getLogger().log(Level.WARNING, "CREATING GROUP CALLED "+"group.clan." + event.getClan().getTag());
        SimpleClans.getInstance().reloadConfig();
    }
    
    public void onClanDisband(DisbandClanEvent event) {
        LuckPermsApi api = LuckPerms.getApi();
        GroupManager gm = api.getGroupManager();
        Group g = gm.getGroup("clan." + event.getClan().getTag());
        if(g != null) gm.deleteGroup(g);
        Bukkit.getLogger().log(Level.WARNING, "DELETING GROUP CALLED "+"group.clan." + event.getClan().getTag());
    }
}
