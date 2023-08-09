package org.aaroniz.sebgu.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.List;

public interface SlashCommand {

    /**
     * @return command's name.
     */
    public String getName();

    /**
     * @return the ids of the guilds this command should work at.
     */
    public List<Long> getGuilds();

    /**
     * @return the command for registering if required.
     */
    public SlashCommandData getSlashCommandData();

    /**
     * Adds the commands to the mapping of the bot which makes it callable and ready for execution.
     * @param addCommand AddCommand callback from CommandInitializer.class.
     * @return the command itself.
     */

    SlashCommand initCommand(AddCommand addCommand);

    /**
     * Executes the command's functionality.
     * @param event the event that triggered the command.
     */

    public void execute(SlashCommandInteractionEvent event);
}
