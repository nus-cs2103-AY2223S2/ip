package duke.command;

import duke.DukeExceptions;

/**
 * Represents a command.
 */
public abstract class Command {
    public abstract String execute() throws DukeExceptions;
}