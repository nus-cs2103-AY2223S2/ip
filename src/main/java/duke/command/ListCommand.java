package duke.command;

import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * List command when user specifies "List" that list all tracked task currently
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class ListCommand extends Command {

    /**
     * Default constructor
     * @param contents Inputs of user
     */
    public ListCommand(String[] contents) {
        super(contents, false);
    }

    /**
     * Saves the task to the memory and displays the result
     * @param taskList List of tasks
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException When saving of task is not possible due to unforseen errors
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.displayResult(TypeOfTask.list, null, taskList);
        for(int i=0; i < taskList.getSize();i++){
            Task currentTask = taskList.getTaskByIndex(i);
            System.out.println(String.format("%d. %s", i+1, currentTask.toString()));
        }
    }
}
