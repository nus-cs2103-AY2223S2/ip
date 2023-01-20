package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to be executed when the user wants to unmark a task as completed.
 *
 * @author Cheam Jia Wei
 */
public class UnmarkCommand extends Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes Unmark command.
     *
     * @param taskList The TaskList that will be modified or accessed.
     * @param inter The Ui that will interact with the user.
     * @param store The storage that will help store the task into the data file if TaskList is modified.
     */
    public void execute(TaskList taskList, Ui inter, Storage store) {
        Task changed = taskList.unmark(input);
        inter.unmark(changed);
        store.writeTasks(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
