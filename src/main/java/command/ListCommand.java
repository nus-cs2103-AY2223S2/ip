package command;

import main.DukeException;
import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Lists all task.
 */
public class ListCommand extends Command {

    /**
     * Output details of all task to user.
     *
     * @param taskList List of task.
     * @param ui Ui.
     * @param storage Storage.
     * @throws DukeException Throws exception if index is invalid.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.outputAllTask(taskList);
    }
}
