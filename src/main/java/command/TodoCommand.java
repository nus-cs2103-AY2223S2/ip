package command;

import main.Storage;
import main.Ui;
import task.Task;
import main.TaskList;

/**
 * Encapsulates Todo command
 */
public class TodoCommand implements Command {
    private Task newTask;

    public TodoCommand(Task newTask) {
        this.newTask = newTask;
    }

    /**
     * Executes the task when user inputs "Todo"
     * 
     * @param list    the list in duke to store the user's Task
     * @param ui      the user interface that is present in duke
     * @param storage the storage to store the task in the TaskList list
     * @throws DukeException if there's an error
     */
    public void execute(TaskList list, Ui ui, Storage storage) {
        list.add(newTask);
        ui.printAddedTask(newTask);
        storage.save(list);
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
