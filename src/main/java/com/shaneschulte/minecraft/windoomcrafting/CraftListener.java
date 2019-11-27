/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shaneschulte.minecraft.windoomcrafting;

import com.sk89q.worldguard.bukkit.ProtectionQuery;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import java.util.ArrayList;
import me.blubdalegend.piggyback.events.PiggybackPickupEntityEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author schul
 */
public class CraftListener implements Listener {
    
    private final WindoomCrafting pl;
    
    CraftListener(WindoomCrafting pl) {
        this.pl = pl;
    }
    
    @EventHandler
    public void onArmorStandPlace(EntitySpawnEvent e) {
        if(e.getEntity() instanceof ArmorStand) {
            ArmorStand as = (ArmorStand)e.getEntity();
            as.setGravity(false);
        }
    }
    
    @EventHandler
    public void onPickupPlayer(PiggybackPickupEntityEvent e) {
        ProtectionQuery pq = new ProtectionQuery();
        if(!pq.testEntityDamage(e.getPlayer(), e.getEntity()))
            e.setCancelled(true);
    }
    
    @EventHandler
    public void onPigmanDeath(EntityDeathEvent e) {
        e.getDrops().removeIf(item -> {
            return item.getType() == Material.GOLD_NUGGET
                    || item.getType() == Material.GOLD_INGOT
                    || item.getType() == Material.GOLDEN_SWORD;
        });
    }
    
    @EventHandler
    public void onCraft(CraftItemEvent e) {
        ItemStack is = e.getCurrentItem();
        ItemMeta meta = is.getItemMeta();
        ArrayList<String> lore = new ArrayList<>();
        Player who = (Player)e.getWhoClicked();
        String r = ChatColor.RESET+""+ChatColor.BOLD;
        switch(is.getType()) {
            case DIAMOND_AXE:
            case DIAMOND_HOE:
            case DIAMOND_PICKAXE:
            case DIAMOND_SHOVEL:
            case DIAMOND_CHESTPLATE:
            case DIAMOND_LEGGINGS:
            case DIAMOND_HELMET:
            case DIAMOND_BOOTS:
            case DIAMOND_SWORD:
                lore.add(r+ChatColor.AQUA+"Crafted by "+who.getName());
                break;
            case GOLDEN_AXE:
            case GOLDEN_HOE:
            case GOLDEN_PICKAXE:
            case GOLDEN_SHOVEL:
            case GOLDEN_CHESTPLATE:
            case GOLDEN_LEGGINGS:
            case GOLDEN_HELMET:
            case GOLDEN_BOOTS:
            case GOLDEN_SWORD:
                lore.add(r+ChatColor.GOLD+"Made by "+who.getName());
                break;
            case IRON_AXE:
            case IRON_HOE:
            case IRON_PICKAXE:
            case IRON_SHOVEL:
            case IRON_CHESTPLATE:
            case IRON_LEGGINGS:
            case IRON_HELMET:
            case IRON_BOOTS:
            case IRON_SWORD:
                lore.add(r+ChatColor.GRAY+"Forged by "+who.getName());
                break;
            case STONE_AXE:
            case STONE_HOE:
            case STONE_PICKAXE:
            case STONE_SHOVEL:
            case STONE_SWORD:
                lore.add(r+ChatColor.DARK_GRAY+"Built by "+who.getName());
                break;
            case LEATHER_CHESTPLATE:
            case LEATHER_LEGGINGS:
            case LEATHER_HELMET:
            case LEATHER_BOOTS:
                lore.add(r+ChatColor.DARK_GREEN+"Stitched by "+who.getName());
                break;
            case WOODEN_AXE:
            case WOODEN_HOE:
            case WOODEN_PICKAXE:
            case WOODEN_SHOVEL:
            case WOODEN_SWORD:
            case BOW:
                lore.add(r+ChatColor.DARK_GREEN+"Carved by "+who.getName());
                break;
            default:
                return;
        }
        meta.setLore(lore);
        is.setItemMeta(meta);
        e.setCurrentItem(is);
    }
}
