package org.aaroniz.sebgu.commands.admin;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.aaroniz.sebgu.commands.AbstractSlashCommand;
import org.aaroniz.sebgu.data.GuildId;

import java.util.ArrayList;
import java.util.List;

/**
 * Deletes the latest x messages from the current channel.
 */
public class PurgeCommand extends AbstractSlashCommand {

    public PurgeCommand() {
        super("purge");

        setCommand(
                Commands.slash(getName(), "Deletes the latest x messages from the current channel.")
                        .addOption(OptionType.INTEGER, "amount", "The number of messages to delete", true)
                        .setDefaultPermissions(DefaultMemberPermissions.DISABLED)
        );


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
        try {
            event.deferReply(true).queue();

            //Gets how many messages it should purge.
            int numDelete = event.getOption("amount").getAsInt();

            //if the user is an idiot:
            if(numDelete <= 0) {
                event.getHook().sendMessage("Never heard of someone trying to delete negative amount of messages -_-").queue();
            }

            //Gets the messages to purge
            List<Message> toDelete = event.getChannel()
                    .getHistory()
                    .retrievePast(numDelete)
                    .complete();
            //Deletes them.
            event.getChannel().purgeMessages(toDelete);
            event.getHook().sendMessage("SEBGU just purged "+numDelete+" messages!").queue();
        }
        catch (ArithmeticException e) {
            event.getHook().sendMessage(event.getUser().getName() + " Are you sane? try smaller numbers.").queue();
        }
        catch (Exception e) {
            event.getHook().sendMessage(event.getUser().getName() + " Are you sane? I T  S A Y S  A  N U M B E R, ok?").queue();
        }
    }
}
