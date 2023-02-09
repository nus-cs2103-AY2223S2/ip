package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Encapsulates the related fields and behavior of a command to add a task.
 */
public class AddCommand extends Command {
    private Task task;
    /**
     * Instantiates AddCommand.
     *
     * @param task The task to be added into the list.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the given task to the taskList.
     *
     * @param tasks The ArrayList of tasks.
     * @param storage The class that reads and write program data to hard drive.
     * @return A string message to signify the success or failure of task executed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        return tasks.add(this.task);
    }
}
