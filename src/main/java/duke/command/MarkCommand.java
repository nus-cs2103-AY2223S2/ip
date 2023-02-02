package duke.command;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.action.Task;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Mark command when user specifies "Mark" at the beginning of the task. This command marks the task.
 * @author Haiqel Bin Hanaffi
 */
public class MarkCommand extends Command {
    /**
     * Default constructor
     * @param contents Inputs from user
     */
    public MarkCommand(String[] contents) {
        super(contents,false);
    }

    /**
     * Saves the task to the memory and displays the result
     * @param taskList List of tasks
     * @param ui Ui object
     * @param storage Storage object
     * @throws DukeException When saving of task is not possible due to unforseen errors
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            int userMarkIndex = Integer.parseInt((super.contents)[0]) - 1;
            Task currentTask = taskList.getTasks().get(userMarkIndex);
            currentTask.markAsDone();
            ui.displayResult(TypeOfTask.mark, currentTask, taskList);
        } catch (Exception e) {
            throw new DukeException(TypeOfTask.mark,1);
        }
    }
}
