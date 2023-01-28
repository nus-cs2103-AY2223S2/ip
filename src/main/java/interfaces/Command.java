package interfaces;

import command.exceptions.CommandExecutionError;

public interface Command {
    void execute() throws CommandExecutionError;
}
