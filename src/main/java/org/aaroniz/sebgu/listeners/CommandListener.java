package org.aaroniz.sebgu.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.aaroniz.sebgu.commands.CommandInitializer;
import org.aaroniz.sebgu.commands.SlashCommand;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommandListener extends ListenerAdapter {

    //private static final Logger log = LoggerFactory.getLogger("BOT_LOGGER");
    private final Map<String, SlashCommand> COMMANDS;
    private final List<String> IGNORED_CHANNELS;

    public CommandListener(CommandInitializer commandInitializer) {
        this.COMMANDS = commandInitializer.loadCommands().requestCommands();
        IGNORED_CHANNELS = new ArrayList<>();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if(filter(event)) {
            return;
        }
        String commandName = event.getName();
        if(COMMANDS.containsKey(commandName)) {
            COMMANDS.get(commandName).execute(event);
        } else {
            //log.info("Command doesnt exist");
        }
    }

    /**
     * <p>
     *     Filters unnecessary events that are invalid.
     * </p>
     * @param event SlashCommandInteractionEvent
     * @return Boolean determining whether it should be filtered or not.
     */
    public boolean filter(SlashCommandInteractionEvent event) {
        if(IGNORED_CHANNELS.contains(event.getChannel().getId())) {
            return true;
        }
        //No need to ignore self since if it's us actor is a bot.
        return event.getUser().isBot() || !event.getChannel().canTalk() || !event.isFromGuild();
    }

    /**
     * @return ignored channels collection for updates.
     */
    public List<String> getIgnoredChannels() {
        return IGNORED_CHANNELS;
    }
}
