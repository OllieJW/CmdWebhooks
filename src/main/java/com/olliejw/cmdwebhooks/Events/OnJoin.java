package me.olliejw.cmdwebhooks.Events;

import me.olliejw.cmdwebhooks.SendWebhook;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.net.MalformedURLException;

public class OnJoin implements Listener {
    final String url;
    final String message;

    public OnJoin(String url, String message) {
        this.url = url;
        this.message = message;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        String pl = event.getPlayer().getDisplayName();
        SendWebhook webhook = new SendWebhook(this.url);
        String toSend = String.format(this.message, event.getPlayer().getDisplayName())
                .replace("[player]", ChatColor.stripColor(pl));
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