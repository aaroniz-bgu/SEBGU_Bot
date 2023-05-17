package org.aaroniz.sebgu.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


import java.util.HashMap;
import java.util.Map;

public class CommandHandler extends ListenerAdapter {
    private final Map<String, SlashCommand> COMMANDS = new HashMap<>();

    /**
     * Loading up all the commands.
     */
    @Override
    public void onReady(ReadyEvent event) {
        for(Guild guild : event.getJDA().getGuilds()){
            for(Map.Entry<String, SlashCommand> entry : COMMANDS.entrySet()){
                guild.upsertCommand(
                        entry.getValue().getName(),
                        entry.getValue().getDescription())
                        .addOptions(entry.getValue().getOptions())
                        .queue();
            }
        }
    }


    /**
     * Command execution, pretty simple.
     */
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(checkIrrelevant(event))
            return;

        if(COMMANDS.containsKey(event.getName())) {
            COMMANDS.get(event.getName()).execute(event);
        }
    }

    /**
     * Makes sure SlashCommand is valid, if there's a reason to bother with it.
     * @param event - SlashCommandInteractionEvent
     * @return true if this interaction is irrelevant
     */
    private boolean checkIrrelevant(SlashCommandInteractionEvent event) {
        if(event.getUser().isBot())
            return true;
        if(event.getChannel().getType() != ChannelType.TEXT)
            return true;

        Member self = event.getGuild().getSelfMember();
        return !self.hasPermission(event.getChannel().asTextChannel(), Permission.MESSAGE_SEND);
    }


    protected void addCommand(SlashCommand command) {
        COMMANDS.put(command.getName(), command);
    }

}
