package command;

import exception.DukeException;
import main.Storage;
import main.Ui;
import main.TaskList;

/**
 * Encapsulates delete command
 */
public class DeleteCommand implements Command {
    private int inputIndex;

    /**
     * Creates a delete command
     * 
     * @param inputIndex the task's index to be deleted
     */
    public DeleteCommand(int inputIndex) {
        this.inputIndex = inputIndex;
    }

    /**
     * Executes the task when user inputs "Delete"
     * 
     * @param list    the list in duke to store the user's Task
     * @param ui      the user interface that is present in duke
     * @param storage the storage to store the task in the TaskList list
     * @throws DukeException if there's an error
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        if (inputIndex <= 0 || inputIndex > list.size()) {
            throw new DukeException("OOPS!!! The index to remove cannot be less than 0 or "
                    + "greater than the length of the list.");
        }
        list.delete(this.inputIndex);
        ui.printDeletedTask(list.get(inputIndex), list);
    }

    /**
     * Checks whether the task exits the programme
     * 
     * @return true if this command exits the programme, false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
