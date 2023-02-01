package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui An Ui which allows for interaction between Duke and user.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return ui.markTaskResponse(tasks.markTask(num, true));
        } catch (IndexOutOfBoundsException e1) {
            return ui.taskNotChosenErrorMessage();
        }
    }

}
