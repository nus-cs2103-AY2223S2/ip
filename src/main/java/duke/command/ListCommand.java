package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Lists all the tasks when user input states list.
 */
public class ListCommand extends Command {
    /**
     * Lists all the task.
     *
     * @param tasks to be printed on user GUI.
     * @param storage object that handles all Storage actions.
     * @throws DukeException from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return Ui.getListOutput(tasks);
    }

    @Override
    public String toString() {
        return String.format("Lists all tasks");
    }
}
