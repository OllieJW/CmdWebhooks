# CmdWebhooks
CmdWebhooks Logs all Joins, Leaves, Commands, Chat and more to your Discord server using webhooks!

# API
**Make the event: (If you arent using events, skip this)**
In this example we will make a Votifier Logger!
```
import com.vexsoftware.votifier.model.VotifierEvent;

@EventHandler
public void onVote(VotifierEvent event) {

}
```

**Use CmdWebhooks:**
```
// Get our webhook link ready
SendWebhook webhook = new SendWebhook("WEBHOOK_LINK");

// Get our Content Ready
webhook.setContent("Message to send to Discord");

// Sending our Message (Embed it in a try/catch)
try {
    webhook.execute();
} catch(Exception e){
    System.out.println("ERROR SENDING VOTE WEBHOOK!!!");
}
```

**Register our event: (Main Class)**
```
@Override
public void onEnable() {
    this.getServer().getPluginManager().registerEvents(new VoteEvent(), this);
}
```

