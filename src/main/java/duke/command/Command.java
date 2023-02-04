package duke.command;

import duke.DukeExceptions;

public abstract class Command {
    public abstract void execute() throws DukeExceptions;
}