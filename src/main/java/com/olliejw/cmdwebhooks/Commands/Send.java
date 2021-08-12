package me.olliejw.cmdwebhooks.Commands;

import me.olliejw.cmdwebhooks.CmdWebhooks;
import me.olliejw.cmdwebhooks.SendWebhook;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Send implements CommandExecutor {
    public CmdWebhooks pl;

    public Send(final CmdWebhooks main) {
        this.pl = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("cmdw-send")) {
            if (sender.hasPermission("cmdw.send")) {
                String url = pl.getConfig().getString("SendURL");
                SendWebhook webhook = new SendWebhook(url);
                StringBuilder sb = new StringBuilder();
                for (String arg : args) {
                    sb.append(arg).append(" ");
                }
                String toSend = sb.toString();
                webhook.setContent(toSend);

                try {
                    webhook.execute();
                    sender.sendMessage(ChatColor.GREEN + "Sent message!");
                } catch (Exception e) {
                    System.out.println("ERROR SENDING VOTE WEBHOOK!");
                }

            } else {
                sender.sendMessage(ChatColor.RED + "You are not permitted to run this command");
            }
        }
        return true;
    }
}

