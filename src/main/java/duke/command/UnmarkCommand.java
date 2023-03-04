package duke.command;
import duke.action.Task;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Unmark command when the user specifies "unmark" at the beginning of the task. Unmarks the task.
 *
 * @author Haiqel Bin Hanaffi
 */
public class UnmarkCommand extends Command {
    /**
     * Default constructor
     *
     * @param contents
     */
    public UnmarkCommand(String[] contents) {
        super(contents, false);
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
        try {
            int userMarkIndex = Integer.parseInt((super.contents)[0]) - 1;
            Task currentTask = taskList.getTasks().get(userMarkIndex);
            currentTask.unmarkAsDone();
            String result = ui.getResult(TypeOfTask.unmark, currentTask, taskList);
            return result;
        } catch (Exception e) {
            throw new DukeException(TypeOfTask.unmark, 1);
        }
    }
}
