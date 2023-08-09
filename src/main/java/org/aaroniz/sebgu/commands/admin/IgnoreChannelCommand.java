package org.aaroniz.sebgu.commands.admin;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.aaroniz.sebgu.commands.AbstractSlashCommand;

public class IgnoreChannelCommand extends AbstractSlashCommand {

    public IgnoreChannelCommand() {
        super("ignorechannel");

        setCommand(
                Commands.slash(getName(),
                        "Lets SEBGU know it should ignore this channel, resets on bot reboot. If already ignored it cancels.")
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
        );
    }

    /**
     * Executes the command's functionality.
     *
     * @param event the event that triggered the command.
     */
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.deferReply(true).queue();

        //TODO

        event.getHook().sendMessage("""
                SEBGU will ignore messages on this channel from now on!
                Notice: this feature is temporary and would be reversed once the bot restarts.
                """);
    }
}
