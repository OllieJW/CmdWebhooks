package com.olliejw.cmdwebhooks.Commands;

import com.olliejw.cmdwebhooks.CmdWebhooks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Reload implements CommandExecutor {
    public CmdWebhooks pl;

    public Reload(final CmdWebhooks main) {
        this.pl = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cmdw-reload")) {
            if (sender.hasPermission("cmdw.reload")) {
                pl.reloadConfig();
                sender.sendMessage(ChatColor.GREEN + "Reloaded");
            } else {
                sender.sendMessage(ChatColor.RED + "You are not permitted to run this command");
            }
        }
        return true;
    }
}
