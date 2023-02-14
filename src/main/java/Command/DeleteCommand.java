package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command from the user to delete a task from list.
 */
public class DeleteCommand extends Command {

    private int num;

    /**
     * Initialises new instance of DeleteCommand.
     *
     * @param num The number of the task in the list to be deleted.
     */
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false. Delete task command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Deletes the selected task from the list. Prints message indicating to user that task was successfully deleted.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     */
    public String execute(TaskList tasks, Storage storage) {
        return Ui.deleteTaskResponse(tasks.deleteTask(num - 1) , tasks);
    }

}
