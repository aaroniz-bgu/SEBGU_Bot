package org.aaroniz.sebgu.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.aaroniz.sebgu.commands.admin.PurgeCommand;
import org.aaroniz.sebgu.commands.info.TestCommand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandInitializer implements AddCommand{

    private final Map<String, SlashCommand> COMMANDS;
    private final List<String> PERSISTENT_COMMANDS;
    private final JDA BOT;

    public CommandInitializer(JDA bot) {
        this.BOT = bot;
        this.COMMANDS = new HashMap<>();
        this.PERSISTENT_COMMANDS = new ArrayList<>();

        //Getting all the persisted commands
        List<Guild> guilds = bot.getGuilds();
        for(Guild g : guilds) {
            PERSISTENT_COMMANDS.addAll(g.retrieveCommands()
                    .complete()
                    .stream()
                    .map(Command::getName)
                    .toList()
            );
        }
        System.out.println(PERSISTENT_COMMANDS.toString());
    }

    /**
     * Feeds commands into the HashMap, loads the functions to the map.
     * @return This instance of the initializer.
     */
    public CommandInitializer loadCommands() {
        //Info
        addCommand(new TestCommand());

        //Admin commands
        addCommand(new PurgeCommand());
        return this;
    }

    /**
     * Adds a command and maps it to make it executable.
     * @param command the command.
     */
    @Override
    public void addCommand(SlashCommand command) {
        //Creating command in the api.
        if(!PERSISTENT_COMMANDS.contains(command.getName())) {
            for(Long id : command.getGuilds()) {
                Guild g = BOT.getGuildById(id);
                if(g != null) {
                    //No need to catch exceptions just be cautious when creating commands.
                    g.upsertCommand(command.getSlashCommandData()).queue();
                }
            }
        }
        //Adding the command into the executable map.
        COMMANDS.put(command.getName(), command);
    }

    /**
     * @return A getter for the command map.
     */
    public Map<String, SlashCommand> requestCommands() {
        return this.COMMANDS;
    }
}
