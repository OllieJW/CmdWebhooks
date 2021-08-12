# CmdWebhooks
CmdWebhooks Logs all Joins, Leaves, Commands, Chat and more to your Discord server using webhooks!

# API Usage
pom.xml:
```
<dependencies>
    <dependency>
        <groupId>me.olliejw</groupId>
        <artifactId>CmdWebhooks</artifactId>
        <version>LATEST</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
Usage:
```
import me.olliejw.cmdwebhooks.SendWebhook;

public class PlayerJoin implements Listener {
    @EventHandler
    public void playerJoins(PlayerJoinEvent event) {
        String playerName = event.getPlayer().getDisplayName();
        
        SendWebhook webhook = new SendWebhook("URL");
        
        String content = ("[player] has joined!").replace("[player]", playerName);
        
        webhook.setContent(content); // Sets the message content

        try {
            webhook.execute(); // Sends message to Discord
        } catch (MalformedURLException var5) {
            System.out.println("[CmdWebhook] Error sending Webhook!");
        } catch (IOException var6) {
            var6.printStackTrace();
        }
    }
}
```




