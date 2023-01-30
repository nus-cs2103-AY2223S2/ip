package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Encapsulates bye command
 */
public class Bye implements Command {
    public Bye() {

    }

    /**
     * Executes the task when user inputs "bye"
     * 
     * @param list    the list in duke to store the user's Task
     * @param ui      the user interface that is present in duke
     * @param storage the storage to store the task in the TaskList list
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        ui.bye();
    }

    /**
     * Checks whether the task exits the programme
     * 
     * @return true if this command exits the programme, false otherwise.
     */
    public boolean isExit() {
        return true;
    }
}
