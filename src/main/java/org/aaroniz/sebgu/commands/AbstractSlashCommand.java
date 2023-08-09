package org.aaroniz.sebgu.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;

import java.util.List;

/**
 * SlashCommand abstract class, every slash command of the bot inherits from this class.
 */
public abstract class AbstractSlashCommand implements SlashCommand {

    private final String NAME;
    private SlashCommandData slashCommandData;
    /**
     * Ids of the guilds this command is works in.
     */
    protected List<Long> guilds;

    protected AbstractSlashCommand(String name) {
        this.NAME = name;
    }

    /**
     * @return command's name.
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * @return the ids of the guilds this command should work at.
     */
    @Override
    public List<Long> getGuilds() {
        return guilds;
    }

    /**
     * @param ids List of ids that this command should operate in.
     */
    protected void setGuilds(List<Long> ids){
        this.guilds = ids;
    }

    /**
     * @return the command for registering if required.
     */
    @Override
    public SlashCommandData getSlashCommandData() {
        return slashCommandData;
    }

    /**
     * Used to set the configuration of the slash command.
     * @param slashCommandData Command.slash(...)... with all the config
     */
    protected void setCommand(SlashCommandData slashCommandData) {
        this.slashCommandData = slashCommandData;
    }

    /**
     * Adds the commands to the mapping of the bot which makes it callable and ready for execution.
     *
     * @param addCommand AddCommand callback from CommandInitializer.class.
     * @return the command itself.
     */
    @Override
    public SlashCommand initCommand(AddCommand addCommand) {
        addCommand.addCommand(this);
        return this;
    }

    /**
     * Executes the command's functionality.
     *
     * @param event the event that triggered the command.
     */
    @Override
    public abstract void execute(SlashCommandInteractionEvent event);
}
