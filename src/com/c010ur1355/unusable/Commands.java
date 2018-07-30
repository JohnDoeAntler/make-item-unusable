package com.c010ur1355.unusable;

import net.minecraft.server.v1_12_R1.CommandExecute;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands extends CommandExecute implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
        if(args.length >= 1){
            if(sender instanceof Player){
                Player player = (Player)sender;
                if(player.getInventory().getItemInMainHand() != null && player.hasPermission("unusable.commands")){
                    ItemStack item = player.getInventory().getItemInMainHand();
                    ItemMeta meta = item.getItemMeta();
                    ArrayList<String> arr = new ArrayList<>();
                    if(meta.getLore() != null) arr.addAll(meta.getLore());
                    List<String> list = new ArrayList<String>(Arrays.asList(args));
                    list.remove(0);
                    String line = String.join(" ", String.join(" ", list.toArray(new String[0])));
                    arr.add(line + "§u§n§u§s§a§b§l§e");
                    meta.setLore(arr);
                    item.setItemMeta(meta);
                    player.getInventory().setItemInMainHand(item);
                    return true;
                }
            }
        }
        return false;
    }
}
