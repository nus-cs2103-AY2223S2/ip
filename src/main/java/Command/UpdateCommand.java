package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command from the user to update a previously added Task.
 */
public class UpdateCommand extends Command {
    private int num;
    private String item;
    private String newInformation;

    /**
     * Initialises new instance of UpdateCommand.
     *
     * @param num The number of the task in the list to be updated.
     * @param item The String input containing item to be edited.
     * @param newInformation The String input containing new information.
     */
    public UpdateCommand(int num, String item, String newInformation) {
        this.num = num;
        this.item = item;
        this.newInformation = newInformation;
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false Update task command is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Updates the selected task in the list.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        Task task = tasks.editTask(this.num, this.item, this.newInformation);
        return Ui.updateTaskResponse(task);
    }
}
