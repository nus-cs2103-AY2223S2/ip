package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists all the tasks when user input indicates list.
 */
public class ListCommand extends Command {
    /**
     * Lists all the task.
     *
     * @param tasks List of tasks.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return Ui.getListOutput(tasks);
    }
}
