package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui An Ui which allows for interaction between Duke and user.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return ui.unmarkTaskResponse(tasks.markTask(num, false));
        } catch (IndexOutOfBoundsException e1) {
            return ui.taskNotChosenErrorMessage();
        }
    }
}
