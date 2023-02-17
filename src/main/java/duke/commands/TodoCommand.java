package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to be executed when a Todos task is to be created.
 *
 * @author Cheam Jia Wei
 */
public class TodoCommand extends Command {
    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the TodoCommand and makes a new Todos task.
     *
     * @param taskList The TaskList that will be modified or accessed.
     * @param inter The Ui that will interact with the user.
     * @param store The storage that will help store the task into the data file if TaskList is modified.
     * @return The string Duke will respond with to the executed command.
     */
    public String execute(TaskList taskList, Ui inter, Storage store) {
        Task added = taskList.todo(input);
        store.writeTasks(taskList);
        return inter.taskAdded(added, taskList.size());
    }

    public boolean isExit() {
        return false;
    }
}
