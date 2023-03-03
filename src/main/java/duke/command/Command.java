package duke.command;

import java.io.IOException;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command.
 * Can be implemented by classes representing
 * various types of commands.
 */
public abstract class Command {
    public Command() {}

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException;

    public boolean isExit() {
        return false;
    }
}
