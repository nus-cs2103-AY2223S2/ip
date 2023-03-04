package duke.command;
import duke.action.Task;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Delete command when user specifies "delete" to delete task
 *
 * @author Haiqel Bin Hanaffi
 */
public class DeleteCommand extends Command {
    /**
     * Default constructor
     *
     * @param contents
     */
    public DeleteCommand(String[] contents) {
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
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NumberFormatException, DukeException {
        int taskIndexDelete = Integer.parseInt(super.parser.convertToUserInput(contents, TypeOfTask.delete, "")) - 1;
        Task taskToBeDeleted = taskList.getTaskByIndex(taskIndexDelete);
        taskList.removeTask(taskIndexDelete);
        String result = "Noted! I've removed this task: \n"
                + String.format("%d. %s", taskIndexDelete + 1, taskToBeDeleted.toString())
                + "\n"
                + ui.getResult(TypeOfTask.delete, null, taskList);
        return result;
    }
}
