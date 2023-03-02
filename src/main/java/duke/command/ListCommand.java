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
     * @param ui the user interface to interact with the user
     * @param storage used to save the TaskList to be retrieved in the future
     * @throws DukeException if the String index is not an integer OR if index is not in range of size of TaskList
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String str = "List:";
        for (int i = 1; i <= tasks.size(); i++) {
            str += String.format("\n\t%d. %s", i, tasks.get(i));
        }
        ui.print(str);
    }
}
