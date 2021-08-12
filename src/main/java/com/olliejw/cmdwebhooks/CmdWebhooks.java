package me.olliejw.cmdwebhooks;

import me.olliejw.cmdwebhooks.Commands.Reload;
import me.olliejw.cmdwebhooks.Commands.Send;
import me.olliejw.cmdwebhooks.Events.OnCmd;
import me.olliejw.cmdwebhooks.Events.OnJoin;
import me.olliejw.cmdwebhooks.Events.OnLeave;
import me.olliejw.cmdwebhooks.Events.OnMsg;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class CmdWebhooks extends JavaPlugin implements Listener {

    private String webhook;
    private static CmdWebhooks instance;

    public static CmdWebhooks instance() {
        return instance;
    }

    public void onEnable() {
        this.saveDefaultConfig();

        //============== COMMANDS ==============//
        this.getCommand("cmdw-reload").setExecutor(new Reload(this));
        this.getCommand("cmdw-send").setExecutor(new Send(this));

        //============== STARTUP MESSAGE ==============//
        this.webhook = this.getConfig().getString("WebhookURL");
        String message;
        if (this.getConfig().getBoolean("Start.Enabled")) {
            this.sendDiscordMessage(this.getConfig().getString("Start.Message"));
        }

        //============== REGISTER EVENTS ==============//
        if (this.getConfig().getBoolean("Join.Enabled")) {
            message = this.getConfig().getString("Join.Message");
            if (!this.getConfig().getString("Join.Webhook").equals("DEFAULT")) {
                webhook = (this.getConfig().getString("Join.Webhook"));
            } else {
                webhook = (this.getConfig().getString("DefaultURL"));
            }
            this.getServer().getPluginManager().registerEvents(new OnJoin(webhook, message), this);
        }
        if (this.getConfig().getBoolean("Leave.Enabled")) {
            message = this.getConfig().getString("Leave.Message");
            if (!this.getConfig().getString("Leave.Webhook").equals("DEFAULT")) {
                webhook = (this.getConfig().getString("Leave.Webhook"));
            } else {
                webhook = (this.getConfig().getString("DefaultURL"));
            }
            this.getServer().getPluginManager().registerEvents(new OnLeave(webhook, message), this);
        }
        if (this.getConfig().getBoolean("Msg.Enabled")) {
            message = this.getConfig().getString("Msg.Message");
            if (!this.getConfig().getString("Msg.Webhook").equals("DEFAULT")) {
                webhook = (this.getConfig().getString("Msg.Webhook"));
            } else {
                webhook = (this.getConfig().getString("DefaultURL"));
            }
            this.getServer().getPluginManager().registerEvents(new OnMsg(webhook, message), this);
        }
        if (this.getConfig().getBoolean("Cmd.Enabled")) {
            message = this.getConfig().getString("Cmd.Message");
            if (!this.getConfig().getString("Cmd.Webhook").equals("DEFAULT")) {
                webhook = (this.getConfig().getString("Cmd.Webhook"));
            } else {
                webhook = (this.getConfig().getString("DefaultURL"));
            }
            this.getServer().getPluginManager().registerEvents(new OnCmd(webhook, message), this);
        }
        //================= END =================//
    }

    private void sendDiscordMessage(String message) {
        SendWebhook webhook = new SendWebhook(this.webhook);
        webhook.setContent(message);

        try {
            webhook.execute();
        } catch (MalformedURLException var4) {
            System.out.println("[CmdWebhook] Invalid webhook");
        } catch (IOException var5) {
            var5.printStackTrace();
        }

    }

    public void onDisable() {
        File config = new File(getDataFolder(), "config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(config);

        if (cfg.getBoolean("Stop.Enabled")) {
            this.sendDiscordMessage(cfg.getString("Stop.Message"));
        }
    }
}
