package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Executes the current command.
 */
public class Command {

    /**
     * Execute the current command.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }

    /**
     * Determines whether the program should be terminated.
     *
     * @return Boolean value if true then exit, if false then not exit.
     */
    public boolean isExit() {
        return false;
    }
}