package org.aaroniz.sebgu;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.aaroniz.sebgu.commands.CommandInitializer;
import org.aaroniz.sebgu.listeners.CommandListener;

import javax.security.auth.login.LoginException;
import java.util.List;

public class SEBGUBot {
    private final Dotenv DOTENV;
    private final JDA BOT;

    private SEBGUBot() throws LoginException, InterruptedException {
        //Loading up the environment
        DOTENV = Dotenv.configure()
                .ignoreIfMalformed()
                .ignoreIfMissing()
                .load();

        //Token retrieval
        String token = DOTENV.get("TOKEN");

        //Building the bot.
        //DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        BOT = JDABuilder.createDefault(token)
                .setActivity(Activity.listening("Boker Tov Mike"))
                .setStatus(OnlineStatus.ONLINE)
                .build()
                .awaitReady();

        CommandInitializer initCommands = new CommandInitializer(BOT);
        CommandListener listener = new CommandListener(initCommands);
        BOT.addEventListener(listener);
    }

    /**
     * @return the bot instance.
     */
    public JDA getJDA(){
        return BOT;
    }

    /**
     * @return DOTENV instance
     */
    public Dotenv getDotenv() {
        return DOTENV;
    }

    public static void main(String[] args) {
        try {
            SEBGUBot segbu = new SEBGUBot();
        } catch(LoginException | InterruptedException e) {
            System.out.println("""
                    Token is either invalid or incorrect - login failed!
                    Please make sure that .env file is placed in resources directory
                    Make sure the TOKEN variable is correct within the environment.
                    """);
        }
    }


    private void removeAllCommands() {
        List<Guild> guilds = getJDA().getGuilds();
        for(Guild g : guilds) {
            List<Command> commands = g.retrieveCommands().complete();
            for(Command command : commands) {
                g.deleteCommandById(command.getId()).queue();
            }
        }

        for(Guild g : guilds) {
            System.out.println(g.retrieveCommands()
                    .complete()
                    .stream()
                    .map(command -> command.getName())
                    .toList());
        }
    }
}
