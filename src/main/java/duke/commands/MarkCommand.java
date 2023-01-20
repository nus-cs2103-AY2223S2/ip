package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to be executed when user wants to mark a task as completed.
 *
 * @author Cheam Jia Wei
 */
public class MarkCommand extends Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the MarkCommand and marks the task as completed.
     *
     * @param taskList The TaskList that will be modified or accessed.
     * @param inter The Ui that will interact with the user.
     * @param store The storage that will help store the task into the data file if TaskList is modified.
     */
    public void execute(TaskList taskList, Ui inter, Storage store) {
        Task changed = taskList.mark(input);
        inter.mark(changed);
        store.writeTasks(taskList);
    }

    public boolean isExit() {
        return false;
    }
}
