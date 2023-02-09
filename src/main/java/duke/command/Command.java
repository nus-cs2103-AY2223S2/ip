package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;
import duke.task.TaskList;

public abstract class Command {

    /**
     * Execute the command given by the user.
     * @param taskList The ArrayList of tasks to modify.
     * @param ui The UI to show different messages to the user.
     * @param storage To access to storage file to save or load tasks.
     * @throws DukeException If execution of command is not successful.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    /**
     * Determines whether the program should exit after executing a command.
     * @return A boolean indicating whether to exit or not.
     */
    public abstract boolean isExit();
}
