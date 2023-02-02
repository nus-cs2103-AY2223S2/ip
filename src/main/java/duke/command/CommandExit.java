package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents an Exit command.
 */
public class CommandExit extends Command {

    public CommandExit() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    @Override
    public boolean isExit() {
        return true;
    }

}
