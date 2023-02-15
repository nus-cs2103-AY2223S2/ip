package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The ListCommand class implements the action of listing all saved tasks.
 *
 * @author Chia Jeremy
 */
public class ListCommand extends Command {

    /**
     * Executes the list command.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        if (tasks.isEmpty()) {
            ui.setResponse("Task list is currently empty!\n");
        } else {
            ui.setResponse("Here are the tasks in your list (on the right >>>)\n");
            ui.setTasksToDisplay(tasks.get());
        }
    }
}
