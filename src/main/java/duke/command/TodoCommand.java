package duke.command;
import duke.action.Task;
import duke.action.Todo;
import duke.data.TaskList;
import duke.data.TypeOfTask;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Todo command when the user specifies "todo" at the beginning of the task. The user creates the most basic task.
 *
 * @author Haiqel Bin Hanaffi
 */
public class TodoCommand extends Command {
    /**
     * Default constructor
     *
     * @param contents Inputs from the user
     */
    public TodoCommand(String[] contents) {
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
        String userInput = super.parser.convertToUserInput(super.contents, TypeOfTask.todo, "");
        Task newTask = new Todo(userInput);
        taskList.addTask(newTask);
        String result = ui.getResult(TypeOfTask.todo, newTask, taskList);
        return result;
    }
}
