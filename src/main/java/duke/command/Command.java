package duke.command;

import duke.DukeExceptions;

/**
 * Represents a command.
 */
public abstract class Command {
    public abstract void execute() throws DukeExceptions;
}