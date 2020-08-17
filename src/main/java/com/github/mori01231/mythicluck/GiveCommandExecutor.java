package com.github.mori01231.mythicluck;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static org.bukkit.Bukkit.getServer;

public class GiveCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    public void sendCommand(String command){
        getServer().dispatchCommand(getServer().getConsoleSender(), command);
    }
}
