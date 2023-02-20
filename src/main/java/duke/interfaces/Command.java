package duke.interfaces;

import duke.command.exceptions.CommandExecutionError;

/**
 * Command is an interface for representing user-issued commands in the task management application.
 */
public interface Command {
    /**
     * Executes the command.
     *
     * @throws CommandException if the execution of the command fails due to some error
     */
    void execute() throws CommandExecutionError;
}
