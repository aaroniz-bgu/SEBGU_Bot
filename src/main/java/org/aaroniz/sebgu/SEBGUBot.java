package org.aaroniz.sebgu;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class SEBGUBot {
    private final ShardManager shardManager;

    public SEBGUBot() throws LoginException {
        Dotenv env = Dotenv.configure().load();
        String token = env.get("TOKEN");

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setActivity(Activity.listening("Boker Tov Mike"));
        builder.setStatus(OnlineStatus.ONLINE);
        shardManager = builder.build();
    }

    public ShardManager getShardManager(){
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            SEBGUBot bot = new SEBGUBot();
        } catch(LoginException e) {
            System.out.println("Token is either invalid or incorrect - login failed!");
        }
    }
}
