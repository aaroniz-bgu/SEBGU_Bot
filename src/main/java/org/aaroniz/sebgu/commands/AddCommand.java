package org.aaroniz.sebgu.commands;

import org.aaroniz.sebgu.commands.SlashCommand;

/**
 * Add command callback used to pass access to addCommand in CommandInitializer as a callback.
 */
@FunctionalInterface
public interface AddCommand {
    /**
     * Adds a command and maps it to make it executable.
     * @param command the command.
     */
    public void addCommand(SlashCommand command);
}
