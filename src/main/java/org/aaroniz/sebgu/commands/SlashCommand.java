package org.aaroniz.sebgu.commands;

import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.aaroniz.sebgu.SEBGUBot;

import java.util.List;

public interface SlashCommand {
    public String getName();
    public String getDescription();
    public List<OptionData> getOptions();
    public String execute(SlashCommandInteractionEvent event);
    public void rebuild(SEBGUBot bot);
}
