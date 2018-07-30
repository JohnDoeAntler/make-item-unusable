package com.c010ur1355.unusable;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;

public class EventListener implements Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(!e.isCancelled()){
            if(checkUnusableAttribute(e.getItemInHand())){
                e.setCancelled(true);
                alertMessage(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onUse(PlayerInteractEvent e){
        if(!e.isCancelled()){
            if(checkUnusableAttribute(e.getItem())){
                e.setCancelled(true);
                alertMessage(e.getPlayer());
            }
        }
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        if(!e.isCancelled()){
            if(checkUnusableAttribute(e.getCurrentItem())){
                e.setCancelled(true);
                e.setResult(Event.Result.DENY);
                alertMessage((Player) e.getWhoClicked());
            }
        }
    }

    @EventHandler
    public void onCraft(CraftItemEvent e){
        if(!e.isCancelled()){
            for(int i = 0; i < e.getInventory().getMatrix().length; i++){
                if(checkUnusableAttribute(e.getInventory().getMatrix()[i])){
                    e.setCancelled(true);
                    e.setResult(Event.Result.DENY);
                    alertMessage((Player) e.getWhoClicked());
                    break;
                }
            }
        }
    }

    @EventHandler
    public void onConsume(PlayerItemConsumeEvent e){
        if(!e.isCancelled()){
            if(checkUnusableAttribute(e.getItem())){
                e.setCancelled(true);
                alertMessage(e.getPlayer());
            }
        }
    }

    private boolean checkUnusableAttribute(ItemStack item){
        if(item != null){
            if(item.getItemMeta() != null){
                if(item.getItemMeta().getLore() != null){
                    for(int i = 0; i < item.getItemMeta().getLore().size(); i++){
                        if(item.getItemMeta().getLore().get(i).contains("§u§n§u§s§a§b§l§e")){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void alertMessage(Player player){
        player.sendMessage("You are not permitted to modify/use this item.");
    }
}
