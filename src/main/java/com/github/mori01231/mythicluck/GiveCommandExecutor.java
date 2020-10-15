package com.github.mori01231.mythicluck;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Random;

import static org.bukkit.Bukkit.getPlayer;
import static org.bukkit.Bukkit.getServer;

public class GiveCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // declare those variables!
        String playerName = args[0];
        String mmItemName = args[1];
        String giveNumber = "0";
        Double mmItemChance = 0.0;
        Integer mmItemNumber = 0;
        Player player = getPlayer(playerName);

        // Acquire the luck value of the player
        Double luckAttribute = player.getAttribute(Attribute.GENERIC_LUCK).getValue();

        // Only for debug to determine if the correct luck value is read by plugin
        //sender.sendMessage(ChatColor.translateAlternateColorCodes('&', String.valueOf(luckNumber)));

        // Set mmItemChance to the third argument if it is a positive double
        try{
            mmItemChance = Double.valueOf(args[2]);
            if (mmItemChance < 0){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"渡す個数は正の整数または少数で指定してください。"));
                return true;
            }
        }catch(Exception e){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"渡す個数は正の整数または少数で指定してください。"));
            return true;
        }

        // Set mmItemNumber to the fourth argument if it is a positive integer
        try{
            mmItemNumber = Integer.valueOf(args[3]);
            if(mmItemNumber < 0){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"渡す個数は正の整数で指定してください。"));
                return true;
            }
        }catch(Exception e){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&',"渡す個数は正の整数で指定してください。"));
            return true;
        }


        // Calculate the odds the player will be getting the item
        Integer giveMultiplier = 100 + (int)Math.round(luckAttribute * 10);
        Integer giveOdds = (int)Math.round(giveMultiplier * mmItemChance);


        // Generate random number
        Random rand = new Random();
        int rand_int1 = rand.nextInt(10000);

        // If the random number is lower than than the chance of getting item, give item.
        if (rand_int1 <= giveOdds){
            sendCommand("mm i give " + playerName + " " + mmItemName + " " + giveNumber);
        }

        return true;
    }

    // functions to make sending commands a lot shorter
    public void sendCommand(String command){
        getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }
}
