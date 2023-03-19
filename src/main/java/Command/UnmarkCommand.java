package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command from the user to unmark a task as undone.
 */
public class UnmarkCommand extends Command {

    private int num;

    /**
     * Initialises new instance of UnmarkCommand.
     *
     * @param num The number of the task in the list to be unmarked.
     */
    public UnmarkCommand(int num) {
        this.num = num;
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false Unmark task command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Unmarks the selected task in the list as undone. Prints a message indicating to user that task successfully
     * unmarked.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.markTask(num, false);
        return Ui.unmarkTaskResponse(task);
    }
}
