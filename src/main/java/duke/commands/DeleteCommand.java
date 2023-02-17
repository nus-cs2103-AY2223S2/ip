package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to be executed when a task is to be deleted.
 *
 * @author Cheam Jia Wei
 */
public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the DeleteCommand and deletes the specified task.
     *
     * @param taskList The TaskList that will be modified or accessed.
     * @param inter The Ui that will interact with the user.
     * @param store The storage that will help store the task into the data file if TaskList is modified.
     * @return The string Duke will respond with to the executed command.
     */
    public String execute(TaskList taskList, Ui inter, Storage store) {
        Task changed = taskList.delete(input);
        store.writeTasks(taskList);
        return inter.delete(changed, taskList.size());
    }

    public boolean isExit() {
        return false;
    }
}
