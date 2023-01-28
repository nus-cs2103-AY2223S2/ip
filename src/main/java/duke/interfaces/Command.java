package duke.interfaces;

import duke.command.exceptions.CommandExecutionError;

public interface Command {
    void execute() throws CommandExecutionError;
}
