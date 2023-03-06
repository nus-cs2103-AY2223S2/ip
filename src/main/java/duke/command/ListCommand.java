package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.task.TaskList;

/**
 * Represents the command to list out all the tasks
 */
public class ListCommand extends Command {
    /**
     * Lists the tasks in TaskList
     * Display the output via Ui showing the old task that was deleted
     * Saves the file via Storage
     *
     * @param tasks TaskList of all the tasks
     * @param ui Ui the user interface to interact with the user
     * @param storage Storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if the String index is not an integer OR if index is not in range of size of TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.print(listString(tasks));
    }
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return listString(tasks);
    }
}
