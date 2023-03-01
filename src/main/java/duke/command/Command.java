package duke.command;

import duke.DukeResponse;

/**
 * An abstract class representing a command that is a result of parsing input into Duke.
 */
public abstract class Command {

    public abstract DukeResponse execute();
}
