package duke.commands;

import duke.DukeException;
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
     * @return The string Duke will respond with to the executed command.
     */
    public String execute(TaskList taskList, Ui inter, Storage store) throws DukeException {
        if (taskList.size() < Integer.parseInt(input) || Integer.parseInt(input) < 1) {
            throw new DukeException("Number out of bounds");
        }
        Task changed = taskList.unmark(input);
        store.writeTasks(taskList);
        return inter.unmark(changed);
    }

    public boolean isExit() {
        return false;
    }
}
