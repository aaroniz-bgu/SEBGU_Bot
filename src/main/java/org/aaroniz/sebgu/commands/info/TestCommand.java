package org.aaroniz.sebgu.commands.info;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.aaroniz.sebgu.commands.AbstractSlashCommand;
import org.aaroniz.sebgu.data.GuildId;

import java.util.ArrayList;
import java.util.List;

/**
 * Test command to make sanity checks in production.
 */
public class TestCommand extends AbstractSlashCommand {

    public TestCommand() {
        super("test");

        setCommand(Commands.slash(getName(), "jus a test bro"));

        List<Long> guilds = new ArrayList<>();
        guilds.add(GuildId.TESTING.getId());
        setGuilds(guilds);
    }

    /**
     * Executes the command's functionality.
     *
     * @param event the event that triggered the command.
     */
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("you're good").queue();
    }
}
