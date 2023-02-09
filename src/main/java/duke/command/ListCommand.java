package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of the command to list out all tasks.
 */
public class ListCommand extends Command {
    /**
     * Prints out all tasks in the list.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @return A string representation of the list of all the tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.print();
    }
}
