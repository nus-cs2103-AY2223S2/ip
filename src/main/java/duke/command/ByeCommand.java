package duke.command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Bye command class when user types "bye" or "quit"
 *
 * @author Haiqel Bin Hanaffi (Acerizm)
 */
public class ByeCommand extends Command {

    /**
     * Default constructor
     */
    public ByeCommand() {
        super(null, true);
    }

    /**
     * Saves the task to the memory and displays the result
     *
     * @param taskList List of tasks
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException When saving of task is not possible due to unforseen errors
     */
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        String result = ui.getResult(TypeOfTask.bye, null, null);
        storage.saveTasks(taskList.getTasks());
        return result;
    }
}
