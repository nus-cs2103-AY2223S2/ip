package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command from the user to mark a task as done.
 */
public class MarkCommand extends Command {

    private int num;

    /**
     * Initialises new instance of MarkCommand.
     *
     * @param num The number of the task in the list to be marked.
     */
    public MarkCommand(int num) {
        this.num = num;
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false Mark task command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Marks the selected task in the list as done. Prints a message indicating to user that task successfully marked.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.markTask(num, true);
        return Ui.markTaskResponse(task);
    }

}
