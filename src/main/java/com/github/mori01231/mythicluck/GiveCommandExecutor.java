package com.github.mori01231.mythicluck;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class GiveCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        String playerName = args[0];
        String mmItemName = args[1];
        Integer giveNumber = 0;
        Double luckNumber = 0.0;
        Player player = getPlayer(playerName);

        luckNumber = player.getAttribute(Attribute.GENERIC_LUCK).getValue();

        try{
            Integer mmItemNumber = Integer.valueOf(args[2]);
            if(mmItemNumber < 0){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"渡す個数は正の整数で指定してください。"));
                return true;
            }
        }catch(Exception e){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"渡す個数は正の整数で指定してください。"));
            return true;
        }

        try{
            Double mmItemChance = Double.valueOf(args[2]);
            if (mmItemChance < 0){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"渡す個数は正の整数または少数で指定してください。"));
                return true;
            }
        }catch(Exception e){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"渡す個数は正の整数または少数で指定してください。"));
            return true;
        }

        sendCommand("mm i give " + playerName + " " + mmItemName + " " + giveNumber);

        return true;
    }

    public void sendCommand(String command){
        getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }
}
