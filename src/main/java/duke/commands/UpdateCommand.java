package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Command to be executed when a Todos task is to be created.
 *
 * @author Cheam Jia Wei
 */
public class UpdateCommand extends Command {
    private String input;

    public UpdateCommand(String input) {
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
    public String execute(TaskList taskList, Ui inter, Storage store) throws DukeException {
        Task updated = taskList.update(input);
        store.writeTasks(taskList);
        return inter.update(updated);
    }

    public boolean isExit() {
        return false;
    }
}
