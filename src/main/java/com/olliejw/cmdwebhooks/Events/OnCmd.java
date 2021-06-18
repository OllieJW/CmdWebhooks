package com.olliejw.cmdwebhooks.Events;

import com.olliejw.cmdwebhooks.SendWebhook;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.io.IOException;
import java.net.MalformedURLException;

public class OnCmd implements Listener {
    final String url;
    final String message;

    public OnCmd(String url, String message) {
        this.url = url;
        this.message = message;
    }

    @EventHandler
    public void onCmd(PlayerCommandPreprocessEvent event) {
        String pl = event.getPlayer().getDisplayName();
        SendWebhook webhook = new SendWebhook(this.url);
        String toSend = String.format(this.message, event.getPlayer().getDisplayName())
                .replace("[player]", ChatColor.stripColor(pl))
                .replace("[cmd]", event.getMessage());
        webhook.setContent(toSend);

        try {
            webhook.execute();
        } catch (MalformedURLException var5) {
            System.out.println("[CmdWebhook] Error sending Webhook!");
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }
}
