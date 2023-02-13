package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to be executed when a new Deadlines task is to be added.
 *
 * @author Cheam Jia Wei
 */
public class DeadlineCommand extends Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the DeadlineCommand and makes a new Deadlines task.
     *
     * @param taskList The TaskList that will be modified or accessed.
     * @param inter The Ui that will interact with the user.
     * @param store The storage that will help store the task into the data file if TaskList is modified.
     * @return The string Duke will respond with to the executed command.
     */
    public String execute(TaskList taskList, Ui inter, Storage store) {
        Task added = taskList.deadline(input);
        store.writeTasks(taskList);
        return inter.taskAdded(added, taskList.size());
    }

    public boolean isExit() {
        return false;
    }
}
