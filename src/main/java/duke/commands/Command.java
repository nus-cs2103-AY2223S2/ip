package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command that can be executed by Duke.
 */
public abstract class Command {
    public boolean isExit() {
        return false;
    }

    /**
     * Execute the command.
     * @param taskList The task list to be used.
     * @param ui The ui to be used.
     * @param storage The storage to be used.
     * @return The string to be printed.
     * @throws DukeException If the command cannot be executed correctly.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;
}
